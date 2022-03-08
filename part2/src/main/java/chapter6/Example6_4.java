package chapter6;

import reactor.core.publisher.Flux;

/**
 * Flux 기본 예제
 */
public class Example6_4 {
    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(System.out::println);
    }
}
