package injection;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceApp {
    public static void main(String[] args) {
        boolean useEmailService = false;
        Injector injector = Guice.createInjector(new UserModule(useEmailService));
        UserService messageService = injector.getInstance(UserService.class);
        System.out.println(messageService.sendMessageTo("Louis", "hello"));
    }
}
