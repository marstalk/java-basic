package demo;

import reactor.core.publisher.Mono;

public class MonoDemo {
    public static void main(String[] args) {
        // create mono
        Mono.empty().subscribe(System.out::println);
        Mono.just("hello Mono").subscribe(System.out::println);


    }
}
