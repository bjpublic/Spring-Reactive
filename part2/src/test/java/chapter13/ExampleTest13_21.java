package chapter13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

/**
 * PublisherProbe 예제
 */
public class ExampleTest13_21 {
    @Test
    public void publisherProbeTest() {
        PublisherProbe<String> probe =
                PublisherProbe.of(PublisherProbeTestExample.supplyStandbyPower());

        StepVerifier
                .create(PublisherProbeTestExample
                        .processTask(
                                PublisherProbeTestExample.supplyMainPower(),
                                probe.mono())
                )
                .expectNextCount(1)
                .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }
}
