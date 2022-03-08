package chapter6;

import reactor.core.publisher.Flux;

/**
 * 여러개의 Flux를 연결해서 하나의 Flux로 결합하는 예제
 */
public class Example6_7 {
    public static void main(String[] args) {
        Flux.concat(
                        Flux.just("Mercury", "Venus", "Earth"),
                        Flux.just("Mars", "Jupiter", "Saturn"),
                        Flux.just("Uranus", "Neptune", "Pluto"))
                .collectList()
                .subscribe(planets -> System.out.println(planets));
    }
}
