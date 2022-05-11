package chapter14.operator_2_filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * take 예제
 */
@Slf4j
public class Example14_21 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .interval(Duration.ofSeconds(1))
            .take(3)
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(4000L);
    }
}
