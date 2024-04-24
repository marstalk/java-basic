package webflux.mango;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AllRoutes {

    private MongoHandler mongoHandler;

    public AllRoutes(MongoHandler mongoHandler) {
        this.mongoHandler = mongoHandler;
    }

    /**
     * RouterFunction is equivalent to Controller in MVC framework
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return RouterFunctions
                // class level @RequestMapping
                .nest(RequestPredicates.path("/func/user"),
                        // method level @RequestMapping
                        RouterFunctions.route(
                                        RequestPredicates.method(HttpMethod.POST),
                                        mongoHandler::postUserHandler)
                                // method level @RequestMapping
                                .andRoute(
                                        RequestPredicates.method(HttpMethod.DELETE),
                                        mongoHandler::deleteUserHandler)
                                .andRoute(
                                        RequestPredicates.method(HttpMethod.GET),
                                        mongoHandler::getUserHandler)
                                .andRoute(
                                        RequestPredicates.method(HttpMethod.GET)
                                                .and(RequestPredicates.path("/stream")),
                                        mongoHandler::getUserHandler));
    }

}
