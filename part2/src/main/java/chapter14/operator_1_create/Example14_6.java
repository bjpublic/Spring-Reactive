package chapter14.operator_1_create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * defer 예제
 */
@Slf4j
public class Example14_6 {
    public static void main(String[] args) throws InterruptedException {
        log.info("# start: {}", LocalDateTime.now());
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() ->
                                                    Mono.just(LocalDateTime.now()));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("# onNext just1: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer1: {}", data));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("# onNext just2: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer2: {}", data));
    }
}
