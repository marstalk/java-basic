package cor;

public class HandlerString extends Handler{
    @Override
    public void doHandle(Request request){
        System.out.println("cor.StringHandler");
        if (request.getName().contains("string")){
            request.setName(request.getName() + " append String");
        }
    }
}
