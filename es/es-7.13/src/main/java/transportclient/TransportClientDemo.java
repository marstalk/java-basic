package transportclient;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class TransportClientDemo {
    public static void main(String[] args) {
        Settings settings = Settings.builder()
                .put("cluster.name", "local-es-7.13")
                .build();

        try (TransportClient client = new PreBuiltTransportClient(settings)) {
            client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 7300));

//            IndexResponse indexResponse = client.prepareIndex("ljc", "_doc", "1")
//                    .setSource("name", "ljc",
//                    "age", "18").get();
//            System.out.println(indexResponse.status());

            GetRequest getRequest = new GetRequest("ljc", "1");
            GetResponse getResponse = client.get(getRequest).actionGet(1000*60*10);
            Map<String, Object> source = getResponse.getSource();
            for (Map.Entry<String, Object> entry : source.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
