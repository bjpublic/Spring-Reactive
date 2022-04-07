package chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

/**
 * StepVerifier 활용 예제
 * - 주어진 시간을 앞당겨서 테스트 한다.
 */
public class ExampleTest13_7 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedTestExample.getCOVID19Count(
                                Flux.interval(Duration.ofHours(1)).take(1)
                        )
                )
                .expectSubscription()
                .then(() -> VirtualTimeScheduler
                                    .get()
                                    .advanceTimeBy(Duration.ofHours(1)))
                .expectNextCount(11)
                .expectComplete()
                .verify();

    }
}
