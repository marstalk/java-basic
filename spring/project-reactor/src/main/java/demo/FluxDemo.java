package demo;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

public class FluxDemo {
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4).subscribe(System.out::println);

        Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5)).subscribe(System.out::println);

        Flux.fromArray(new String[]{"a", "b"}).subscribe(System.out::println);

        Flux.fromStream(Stream.of(1, 2, 3, 444, 5)).subscribe(System.out::println);

        Flux.range(1, 100).subscribe(System.out::println);

        /**
         * 0*2=0
         * 1*2=2
         * 2*2=4
         * ...
         * 9*2=18
         */
        Flux.generate(() -> 0, (i, sink) -> {
            sink.next(i + "*2=" + (i * 2));
            if (i == 9) sink.complete();
            return i + 1;
        }).subscribe(System.out::println);
    }
}
