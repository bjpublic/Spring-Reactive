package chapter5;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Hello Reactor 예제
 */
@Slf4j
public class Example5_1 {
    public static void main(String[] args) {
        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence.map(data -> data.toLowerCase())
                .subscribe(data -> System.out.println(data));
    }
}
