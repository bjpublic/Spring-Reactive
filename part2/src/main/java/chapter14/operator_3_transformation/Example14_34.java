package chapter14.operator_3_transformation;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * merge 예제
 */
@Slf4j
public class Example14_34 {
    public static void main(String[] args) throws InterruptedException {
        String[] usaStates = {
                "Ohio", "Michigan", "New Jersey", "Illinois", "New Hampshire",
                "Virginia", "Vermont", "North Carolina", "Ontario", "Georgia"
        };

        Flux
                .merge(getMeltDownRecoveryMessage(usaStates))
                .subscribe(log::info);

        Thread.sleep(2000L);
    }

    private static List<Mono<String>> getMeltDownRecoveryMessage(String[] usaStates) {
        List<Mono<String>> messages = new ArrayList<>();
        for (String state : usaStates) {
            messages.add(SampleData.nppMap.get(state));
        }

        return messages;
    }
}