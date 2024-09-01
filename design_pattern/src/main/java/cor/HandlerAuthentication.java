package cor;

public class HandlerAuthentication extends Handler{
    @Override
    public void doHandle(Request request){
        System.out.println("cor.AuthenticationHandler");
        request.setName(request.getName() + " authentication");
    }
}
