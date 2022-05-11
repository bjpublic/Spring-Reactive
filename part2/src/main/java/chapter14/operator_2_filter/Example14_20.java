package chapter14.operator_2_filter;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * skip 예제
 */
@Slf4j
public class Example14_20 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .filter(tuple -> tuple.getT2() >= 20_000_000)
            .skip(2)
            .subscribe(tuple -> log.info("{}, {}", tuple.getT1(), tuple.getT2()));
    }
}
