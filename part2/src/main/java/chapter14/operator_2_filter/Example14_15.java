package chapter14.operator_2_filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * filter 예제
 */
@Slf4j
public class Example14_15 {
    public static void main(String[] args) {
        Flux
            .range(1, 20)
            .filter(num -> num % 2 != 0)
            .subscribe(data -> log.info("# onNext: {}", data));
    }
}
