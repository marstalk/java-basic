package cor;

public class HandlerInteger extends Handler{
    @Override
    public void doHandle(Request request){
        System.out.println("cor.IntegerHandler");
        if (request.getName().contains("integer")){
            request.setName(request.getName() + "integer append");
        }
    }
}
