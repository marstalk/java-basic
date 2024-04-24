package webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

// 1. annotated way to build MVC
@RestController
@RequestMapping("/annotated")
@Configuration
public class WebFluxController {
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello web flux by annotated");
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .GET("/func/hello",
                        serverRequest -> ServerResponse.ok().bodyValue("hello from functional"))
                .build();
    }
}
