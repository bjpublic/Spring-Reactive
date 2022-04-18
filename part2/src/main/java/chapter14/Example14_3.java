package chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * fromIterable 예제
 */
@Slf4j
public class Example14_3 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coins)
                .subscribe(coin ->
                        log.info("coin 명: {}, 현재가: {}", coin.getT1(), coin.getT2())
                );
    }
}
