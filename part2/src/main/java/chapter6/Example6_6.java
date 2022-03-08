package chapter6;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 2개의 Mono를 연결해서 Flux로 변환하는 예제
 */
public class Example6_6 {
    public static void main(String[] args) {
        Flux<String> flux =
                Mono.justOrEmpty("Steve")
                        .concatWith(Mono.justOrEmpty("Jobs"));
        flux.subscribe(System.out::println);
    }
}
