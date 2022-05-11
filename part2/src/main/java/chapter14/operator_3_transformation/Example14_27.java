package chapter14.operator_3_transformation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * map 예제
 */
@Slf4j
public class Example14_27 {
    public static void main(String[] args) {
        Flux
            .just("1-Circle", "3-Circle", "5-Circle")
            .map(circle -> circle.replace("Circle", "Rectangle"))
            .subscribe(data -> log.info("# onNext: {}", data));
    }
}
