package chapter14.operator_3_transformation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * flatMap 예제
 */
@Slf4j
public class Example14_30 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .range(2, 8)
            .flatMap(dan -> Flux
                                .range(1, 9)
                                .publishOn(Schedulers.parallel())
                                .map(n -> dan + " * " + n + " = " + dan * n))
            .subscribe(log::info);

        Thread.sleep(100L);
    }
}