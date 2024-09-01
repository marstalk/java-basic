package cor;

import java.util.Arrays;

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        HandlerAuthentication authenticationHandler = new HandlerAuthentication();
        HandlerInteger handlerInteger = new HandlerInteger();
        HandlerString handlerString = new HandlerString();

        // use the chain to organize the handlers: combination, sorting.
        Chain chain = new Chain(Arrays.asList(authenticationHandler, handlerInteger, handlerString));
        Request request = new Request();
        request.setName("string");
        chain.proceed(request);
        System.out.println(request);

        chain = new Chain(Arrays.asList(authenticationHandler, handlerString, handlerInteger));
        request = new Request();
        request.setName("integer, string");
        chain.proceed(request);
        System.out.println(request);
    }
}
