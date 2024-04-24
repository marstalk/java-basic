package injection;

public class MessageSmsServiceImpl implements MessageService{
    @Override
    public String hello(String name) {
        return "send Message by SMS to " + name;
    }
}
