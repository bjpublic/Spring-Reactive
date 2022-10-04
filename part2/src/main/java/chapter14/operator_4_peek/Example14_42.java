package chapter14.operator_4_peek;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * doOnXXXX 예제
 *  - doOnXXXX() Operator의 호출 시점을 알 수 있다.
 */
@Slf4j
public class Example14_42 {
    public static void main(String[] args) {
        Flux.range(1, 5)
            .doFinally(signalType -> log.info("# doFinally 1: {}", signalType))
            .doFinally(signalType -> log.info("# doFinally 2: {}", signalType))
            .doOnNext(data -> log.info("# range > doOnNext(): {}", data))
            .doOnRequest(data -> log.info("# doOnRequest: {}", data))
            .doOnSubscribe(subscription -> log.info("# doOnSubscribe 1"))
            .doFirst(() -> log.info("# doFirst()"))
            .filter(num -> num % 2 == 1)
            .doOnNext(data -> log.info("# filter > doOnNext(): {}", data))
            .doOnComplete(() -> log.info("# doOnComplete()"))
            .subscribe(new BaseSubscriber<>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    log.info("# hookOnNext: {}", value);
                    request(1);
                }
            });
    }
}