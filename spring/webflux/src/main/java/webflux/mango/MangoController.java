package webflux.mango;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
@RequestMapping("/user")
public class MangoController {
    private MongoRepository mongoRepository;

    public MangoController(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    private static String[] INVALID_NAMES = new String[]{"admin", "administrator", "guanliyuan"};
    /**
     * Annotation Valid tell the framework to validate the binding first.
     *
     * @param user
     * @return
     */
    @PostMapping()
    public Mono<User> createUser(@Valid @RequestBody User user) {

        Arrays.stream(INVALID_NAMES).filter(s -> s.equalsIgnoreCase(user.getName())).findAny().ifPresent(s -> {
            throw new CheckException("name", s);
        });

        // in spring data jpa repository, save can be insertion when id is null or update when id is not null;
        // so here set null to id to make sure it's insertion always.
        user.setId(null);
        return mongoRepository.save(user);
    }

    /**
     * delete user by id,
     * if exists return 200
     * if not exists return 404
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        // in web flux, the controller always return Mono or Flux.

        // the reactive mongodb repository, the CRUD always return Mono or Flux.

        // but the repository.delete return Mono<Void>, which contains no http code.

        // so we should try to query then delete.
        return mongoRepository
                .findById(id)
                .flatMap(user -> mongoRepository
                        .deleteById(user.getId())
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping()
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User user) {
        return mongoRepository
                .findById(user.getId())
                .flatMap(u -> mongoRepository
                        .save(user)
                        .flatMap(u2 -> Mono.just(new ResponseEntity<>(u2, HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public Flux<User> getAll() {
        return mongoRepository.findAll();
    }

    @GetMapping("/2")
    public Mono<ResponseEntity<List<User>>> getAll2() {
        return mongoRepository.findAll().collect(Collectors.toList()).map(l -> new ResponseEntity<>(l, HttpStatus.OK));
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllByStream() {
        return mongoRepository.findAll();
    }

    @GetMapping("/{start}/{end}")
    public Flux<User> getByAges(@PathVariable("start") int start, @PathVariable("end") int end) {
        return mongoRepository.findByAgeBetween(start, end);
    }

    @GetMapping(value = "/stream/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAges(@PathVariable("start") int start, @PathVariable("end") int end) {
        return mongoRepository.findByAgeBetween(start, end);
    }
}