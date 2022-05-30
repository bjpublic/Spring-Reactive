package chapter14.operator_6_time;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 시간 측정 예제
 *  - elapsed Operator
 *      - emit된 데이터 사이의 경과된 시간을 측정한다.
 *      - emit된 첫번째 데이터는 onSubscribe Signal과 첫번째 데이터 사이의 시간을 기준으로 측정한다.
 *      - 측정된 시간 단위는 milliseconds이다.
 */
@Slf4j
public class Example14_51 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .range(1, 5)
            .delayElements(Duration.ofSeconds(1))
            .elapsed()
            .subscribe(data -> log.info("# onNext: {}, time: {}",
                                                data.getT2(), data.getT1()));

        Thread.sleep(6000);
    }
}