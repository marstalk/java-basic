package webflux.mango;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * functional way to create MVC with reactive mongodb,
 * handler is equivalent to Service in MVC framework, which is the place to put business logic.
 *
 * @see org.springframework.web.reactive.function.server.HandlerFunction
 */
@Component
public class MongoHandler {

    private final MongoRepository mongoRepository;

    public MongoHandler(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    /**
     * insert,
     * make sure the user.id is empty.
     * return user
     */
    public Mono<ServerResponse> postUserHandler(ServerRequest request) {
        Mono<User> userMono = request
                .bodyToMono(User.class)
                .map(user -> {
                    user.setId(null);
                    return user;
                });

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mongoRepository.saveAll(userMono), User.class);
    }

    /**
     * delete,
     * return 200 if exist else 404
     */
    public Mono<ServerResponse> deleteUserHandler(ServerRequest request) {
        String id = request.pathVariable("id");
        return mongoRepository
                .findById(id)
                .flatMap(user -> mongoRepository
                        .deleteById(user.getId())
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getUserHandler(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mongoRepository.findAll(), User.class);
    }


}
