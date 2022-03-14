package chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


/**
 * subscribeOn()과 publishOn()의 동작 과정 예
 *  - 두 개의 publishOn()을 사용한 경우
 *      - 다음 publishOn()을 만나기 전까지 publishOn() 아래 쪽 Operator들의 실행 쓰레드를 변경한다.
 *
 */
@Slf4j
public class Example10_7 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .fromArray(new Integer[] {1, 3, 5, 7})
            .doOnNext(data -> log.info("# doOnNext fromArray: {}", data))
            .publishOn(Schedulers.parallel())
            .filter(data -> data > 3)
            .doOnNext(data -> log.info("# doOnNext filter: {}", data))
            .publishOn(Schedulers.parallel())
            .map(data -> data * 10)
            .doOnNext(data -> log.info("# doOnNext map: {}", data))
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}
