package chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks.Many 예제
 *  - unicast()통해 단 하나의 Subscriber만 데이터를 전달 받을 수 있다
 */
@Slf4j
public class Example9_8 {
    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);


        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        unicastSink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));
    }
}
