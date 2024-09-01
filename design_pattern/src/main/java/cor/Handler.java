package cor;

public abstract class Handler {
    public void handleRequest(Request request, Chain chain){
        doHandle(request);
        chain.proceed(request);
    }

    public abstract void doHandle(Request request);
}
