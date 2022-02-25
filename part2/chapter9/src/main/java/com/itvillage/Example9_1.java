package com.itvillage;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/**
 * create() Operator를 사용하는 예제
 *  - 일반적으로 Publisher의 데이터 생성을 단일 쓰레드에서 진행한다.
 */
@Slf4j
public class Example9_1 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .create((FluxSink<Integer> sink) -> {
                IntStream
                        .range(1, 6)
                        .forEach(n -> sink.next(n));
            })
            .subscribeOn(Schedulers.boundedElastic())
            .doOnNext(n -> log.info("# doOnNext create(): {}", n))
            .publishOn(Schedulers.parallel())
            .filter(n -> n % 2 == 0)
            .doOnNext(n -> log.info("# doOnNext filter(): {}", n))
            .publishOn(Schedulers.parallel())
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}
