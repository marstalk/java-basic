package com.ljc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;
import org.joda.time.LocalDateTime;

public class HelloHandler extends BaseRestHandler {

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest restRequest, NodeClient nodeClient) throws IOException {
        System.out.println("params=" + restRequest.params());
        String name = restRequest.param("name");
        return channel -> {
            XContentBuilder builder = channel.newBuilder();
            builder.startObject();
            builder.field("slogan", "welcome to G7-ES powered by Elastic");
            builder.field("name", name);
            builder.field("time", LocalDateTime.now().toString());
            builder.endObject();
            channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));
        };
    }

    @Override
    public List<Route> routes() {
        return Collections.unmodifiableList(Arrays.asList(new Route(RestRequest.Method.GET, "/_hello")));
    }

}
