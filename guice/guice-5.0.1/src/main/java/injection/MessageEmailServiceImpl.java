package injection;

public class MessageEmailServiceImpl implements MessageService{
    @Override
    public String hello(String name) {
        return "send Message by Email to " + name;
    }
}
