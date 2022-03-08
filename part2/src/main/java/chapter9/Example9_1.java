package chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/**
 * create() Operator를 사용하는 예제
 *  - 일반적으로 Publisher가 단일 쓰레드에서 데이터 생성한다.
 */
@Slf4j
public class Example9_1 {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;
        Flux
            .create((FluxSink<String> sink) -> {
                IntStream
                        .range(1, tasks)
                        .forEach(n -> sink.next(doTask(n)));
            })
            .subscribeOn(Schedulers.boundedElastic())
            .doOnNext(n -> log.info("# create(): {}", n))
            .publishOn(Schedulers.parallel())
            .map(result -> result + " success!")
            .doOnNext(n -> log.info("# map(): {}", n))
            .publishOn(Schedulers.parallel())
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }

    private static String doTask(int taskNumber) {
        // now tasking.
        // complete to task.
        return "task " + taskNumber + " result";
    }
}
