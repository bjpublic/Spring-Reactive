package chapter14.operator_1_create;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * range 예제
 */
@Slf4j
public class Example14_5 {
    public static void main(String[] args) {
        Flux
            .range(7, 5)
            .map(idx -> SampleData.btcTopPricesPerYear.get(idx))
            .subscribe(tuple -> log.info("{}'s {}", tuple.getT1(), tuple.getT2()));
    }
}
