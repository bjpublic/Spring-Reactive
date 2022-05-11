package chapter14.operator_3_transformation;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * map 예제
 */
@Slf4j
public class Example14_28 {
    public static void main(String[] args) {
        final double buyPrice = 50_000_000;
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .filter(tuple -> tuple.getT1() == 2021)
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .map(tuple -> calculateProfitRate(buyPrice, tuple.getT2()))
                .subscribe(data -> log.info("# onNext: {}%", data));
    }

    private static double calculateProfitRate(final double buyPrice, Long topPrice) {
        return (topPrice - buyPrice) / buyPrice * 100;
    }
}
