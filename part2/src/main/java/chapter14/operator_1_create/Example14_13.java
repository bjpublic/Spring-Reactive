package chapter14.operator_1_create;

import chapter14.CryptoCurrencyPriceEmitter;
import chapter14.CryptoCurrencyPriceListener;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * create 예제
 *  - push 방식
 */
@Slf4j
public class Example14_13 {
    public static void main(String[] args) throws InterruptedException {
        CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

        Flux.create((FluxSink<Integer> sink) ->
                        priceEmitter.setListener(new CryptoCurrencyPriceListener() {
            @Override
            public void onPrice(List<Integer> priceList) {
                priceList.stream().forEach(price -> {
                    sink.next(price);
                });
            }

            @Override
            public void onComplete() {
                sink.complete();
            }
        }))
        .publishOn(Schedulers.parallel())
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> {},
            () -> log.info("# onComplete"));

        Thread.sleep(3000L);

        priceEmitter.flowInto();

        Thread.sleep(2000L);
        priceEmitter.complete();
    }
}