package cor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Chain {
    private List<Handler> handlers = new ArrayList<>();
    private final AtomicInteger idx = new AtomicInteger(0);

    public Chain(List<Handler> handlers) {
        this.handlers = handlers;
    }

    public void proceed(Request request){
        if (idx.get() < handlers.size()){
            handlers.get(idx.getAndIncrement()).handleRequest(request, this);
        }
    }
}
