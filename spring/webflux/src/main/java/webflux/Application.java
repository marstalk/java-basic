package webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 2. functional way to build MVC
    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .GET("/func/hello",
                        serverRequest -> ServerResponse.ok().bodyValue("hello from functional"))
                .build();
    }
}
