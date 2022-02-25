package com.itvillage;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/**
 * Sinks를 사용하는 예제
 *  - Publisher의 데이터 생성을 멀티 쓰레드에서 진행해도 Thread safe 하다.
 */
@Slf4j
public class Example9_2 {
    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();
        IntStream
                .range(1, 6)
                .forEach(n -> {
                    try {
                        new Thread(() -> {
                            unicastSink.emitNext(n, Sinks.EmitFailureHandler.FAIL_FAST);
                            log.info("# emitted: {}", n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {}
                });

        fluxView
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }
}
