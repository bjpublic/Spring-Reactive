package chapter14.operator_2_filter;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * filter 예제
 */
@Slf4j
public class Example14_16 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .filter(tuple -> tuple.getT2() > 20_000_000)
            .subscribe(data -> log.info(data.getT1() + ":" + data.getT2()));
    }
}
