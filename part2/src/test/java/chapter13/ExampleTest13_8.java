package chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * StepVerifier 활용 예제
 *  -검증에 소요되는 시간을 제한한다.
 */
public class ExampleTest13_8 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .create(TimeBasedTestExample.getCOVID19Count(
                                Flux.interval(Duration.ofMinutes(1)).take(1)
                        )
                )
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }
}
