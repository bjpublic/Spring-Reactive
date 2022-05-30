package chapter14.operator_5_error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * error 처리 예제
 *  - retry Operator
 *      - 에러가 발생했을 때, 지정된 횟수만큼 Sequence를 다시 구독한다.
 */
@Slf4j
public class Example14_49 {
    public static void main(String[] args) throws InterruptedException {
        final int[] count = {1};
        Flux
            .range(1, 3)
            .delayElements(Duration.ofSeconds(1))
            .map(num -> {
                try {
                    if (num == 3 && count[0] == 1) {
                        count[0]++;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {}

                return num;
            })
            .timeout(Duration.ofMillis(1500))
            .retry(1)
            .subscribe(data -> log.info("# onNext: {}", data),
                    (error -> log.error("# onError: ", error)),
                    () -> log.info("# onComplete"));

        Thread.sleep(7000);
    }
}