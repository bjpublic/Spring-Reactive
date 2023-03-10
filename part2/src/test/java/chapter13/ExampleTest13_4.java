package chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * StepVerifier 활용 예제.
 */
public class ExampleTest13_4 {
    @Test
    public void divideByTwoTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier
                .create(GeneralTestExample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
//                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }
}
