package chapter14.operator_1_create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

/**
 * create 예제
 *  - Backpressure 전략 적용
 */
@Slf4j
public class Example14_14 {
    static int start = 1;
    static int end = 4;

    public static void main(String[] args) throws InterruptedException {
        Flux.create((FluxSink<Integer> emitter) -> {
            emitter.onRequest(n -> {
                log.info("# requested: " + n);
                try {
                    Thread.sleep(500L);
                    for (int i = start; i <= end; i++) {
                        emitter.next(i);
                    }
                    start += 4;
                    end += 4;
                } catch (InterruptedException e) {}
            });

            emitter.onDispose(() -> {
                log.info("# clean up");
            });
        }, FluxSink.OverflowStrategy.DROP)
        .subscribeOn(Schedulers.boundedElastic())
        .publishOn(Schedulers.parallel(), 2)
        .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3000L);
    }
}