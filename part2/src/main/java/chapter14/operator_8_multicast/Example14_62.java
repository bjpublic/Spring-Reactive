package chapter14.operator_8_multicast;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * multicast 예제
 *  - autoConnect() Operator
 *      - 다수의 Subscriber와 Flux를 공유한다.
 *      - 즉, Cold Sequence를 Hot Sequence로 변환한다.
 *      - 파라미터로 입력한 숫자만큼의 구독이 발생하는 시점에 connect()가 자동으로 호출된다.
 */
@Slf4j
public class Example14_62 {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> publisher =
                Flux
                    .just("Concert part1", "Concert part2", "Concert part3")
                    .delayElements(Duration.ofMillis(300L))
                    .publish()
                    .autoConnect(2);

        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 1 is watching {}", data));

        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 2 is watching {}", data));

        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 3 is watching {}", data));

        Thread.sleep(1000L);
    }
}