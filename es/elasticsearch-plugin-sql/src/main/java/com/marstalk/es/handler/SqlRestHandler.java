package com.marstalk.es.handler;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SqlRestHandler extends BaseRestHandler {
    @Override
    public String getName() {
        return "ljc_sql_action";
    }

    @Override
    public List<Route> routes() {
        return Collections.unmodifiableList(Arrays.asList(
                new Route(RestRequest.Method.GET, "_ljc/sql"),
                new Route(RestRequest.Method.POST, "_ljc/sql"),
                new Route(RestRequest.Method.POST, "_ljc/sql/explain"),
                new Route(RestRequest.Method.GET, "_ljc/sql/explain")
        ));
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        return null;
    }
}
