package injection;

import com.google.inject.AbstractModule;

public class UserModule extends AbstractModule {

    private final boolean useEmailService;

    public UserModule(boolean useEmailService) {
        this.useEmailService = useEmailService;
    }

    @Override
    protected void configure() {
        //UserService所依赖的MessageService会通过@Inject自动注入
        bind(UserService.class).to(UserServiceImpl.class);

        if(useEmailService){
            bind(MessageService.class).to(MessageEmailServiceImpl.class);
        }else{
            bind(MessageService.class).to(MessageSmsServiceImpl.class);
        }
    }
}
