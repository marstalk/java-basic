package demo;

import reactor.core.publisher.Flux;

public class FluxDemo2 {
    public static void main(String[] args) {
        // input = hello mates, i am Louis Liu from Mars, welcome to the new world of reactive stream->project rector
        // output = abcdefghijklmnopqrstuvwxyz.

        String src = "hello mates I am Louis Liu from Mars welcome to the new world of from reactiveStream to projectRector";
        Flux.just(src.split(" "))
                .flatMap(s -> Flux.just(s.split("")))
                .map(String::toLowerCase)
                .distinct()
                .sort()
                .subscribe(System.out::println);

    }
}
