package chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * checkpoint(description)을 사용한 디버깅 예
 * - description 을 추가해서 에러가 발생한 지점을 구분할 수 있다.
 * - forceStackTrace 을 true로 지정할 경우 traceback도 추가한다.
 */
@Slf4j
public class Example12_5 {
    public static void main(String[] args) {
        Flux
            .just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
            .checkpoint("Example12_4.zipWith.checkpoint", true)
            .map(num -> num + 2)
            .checkpoint("Example12_4.map.checkpoint", true)
            .subscribe(
                    data -> log.info("# onNext: {}", data),
                    error -> log.error("# onError:", error)
            );
    }
}
