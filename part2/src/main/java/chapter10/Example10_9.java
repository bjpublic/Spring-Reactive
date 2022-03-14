package chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


/**
 * subscribeOn()과 publishOn()의 동작 과정 예
 *  - subscribeOn()을 두 번 사용한 경우
 *      - subscribeOn()이 여러개 있어도 가장 앞쪽에 추가된 subscribeOn()만 실행 쓰레드를 변경한다.
 *
 */
@Slf4j
public class Example10_9 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .fromArray(new Integer[] {1, 3, 5, 7})
            .doOnNext(data -> log.info("# doOnNext fromArray: {}", data))
            .publishOn(Schedulers.parallel())
            .filter(data -> data > 3)
            .doOnNext(data -> log.info("# doOnNext filter: {}", data))
            .subscribeOn(Schedulers.boundedElastic())
            .map(data -> data * 10)
            .doOnNext(data -> log.info("# doOnNext map: {}", data))
            .subscribeOn(Schedulers.parallel())
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}
