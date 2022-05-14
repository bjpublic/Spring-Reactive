package chapter14.operator_3_transformation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * concat 예제
 */
@Slf4j
public class Example14_31 {
    public static void main(String[] args) {
        Flux
            .concat(Flux.just(1, 2, 3), Flux.just(4, 5))
            .subscribe(data -> log.info("# onNext: {}", data));
    }
}