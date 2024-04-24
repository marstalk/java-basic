package changestream;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ChangeStreamsExample {

    public static void main(String[] args) throws IOException {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost[]{new HttpHost("paas-test-node1.es.test.chinawayltd.com", 7200, "https")});
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "lsEVGHa6zy2rhqR3IQEu"));

        restClientBuilder.setHttpClientConfigCallback(
                httpClientBuilder -> {
                    httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
                    httpClientBuilder.setKeepAliveStrategy((response, context) -> 60000);
                    httpClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setSoKeepAlive(true).build());

                    try {
                        final SSLContext ssl = new SSLContextBuilder().loadTrustMaterial(null, (unused1, unused2) -> true).build();
                        httpClientBuilder.setSSLContext(ssl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return httpClientBuilder;
                }
        );
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);

        // Open a Change Stream on indexA
        ChangeStreamHandler handler = new ChangeStreamHandler(restHighLevelClient, "ljc_change_stream");
        handler.start();

        // Let the Change Stream run for a while
        try {
            TimeUnit.SECONDS.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the Change Stream
        handler.stop();
    }
}

class ChangeStreamHandler {
    private final RestHighLevelClient client;
    private final String index;
    private String scrollId;

    private volatile boolean running;

    public ChangeStreamHandler(RestHighLevelClient client, String index) {
        this.client = client;
        this.index = index;
    }

    public void start() {
        running = true;
        Scroll scroll = new Scroll(TimeValue.timeValueMinutes(10L));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .sort("_doc", SortOrder.ASC)
                .size(100);

        SearchRequest searchRequest = new SearchRequest(index)
                .scroll(scroll)
                .source(sourceBuilder);

        ActionListener<SearchResponse> scrollListener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                // Handle change notifications in searchResponse

                for (SearchHit hit : searchResponse.getHits().getHits()) {
                    System.out.println(hit.getSourceAsMap());
                }

                // Continue scrolling
                scrollId = searchResponse.getScrollId();
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);

                client.scrollAsync(scrollRequest, RequestOptions.DEFAULT, this);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                // Handle failure
            }
        };

        // Start the initial search request
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, scrollListener);
    }

    public void stop() throws IOException {
        running = false;
        // Clear the scroll context
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
    }
}
