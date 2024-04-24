package webflux.mango;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MongoRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findByAgeBetween(int start, int end);
}
