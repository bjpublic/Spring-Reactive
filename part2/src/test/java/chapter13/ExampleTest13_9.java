package chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * StepVerifier 활용 예제
 *  - 지정된 대기 시간동안 이벤트가 없을을 확인한다.
 */
public class ExampleTest13_9 {
    @Test
    public void getVoteCountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedTestExample.getVoteCount(
                                Flux.interval(Duration.ofMinutes(1))
                        )
                )
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }
}
