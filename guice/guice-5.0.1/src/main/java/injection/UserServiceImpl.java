package injection;

import com.google.inject.Inject;

public class UserServiceImpl implements UserService{
    @Inject
    private MessageService messageService;
    @Override
    public String sendMessageTo(String name, String message) {
        return messageService.hello(name + " with message: " + message);
    }
}
