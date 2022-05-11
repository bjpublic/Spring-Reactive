package chapter14.operator_1_create;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * fromIterable 예제
 */
@Slf4j
public class Example14_2 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coins)
                .subscribe(coin ->
                        log.info("coin 명: {}, 현재가: {}", coin.getT1(), coin.getT2())
                );
    }
}
