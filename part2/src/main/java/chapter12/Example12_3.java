package chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 *  checkpoint()를 사용한 디버깅 예
 * - checkpoint()를 지정한 Operator 체인에서만 동작한다.
 */
@Slf4j
public class Example12_3 {
    public static void main(String[] args) {
        Flux
            .just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
            .checkpoint()
            .map(num -> num + 2)
            .checkpoint()
            .subscribe(
                    data -> log.info("# onNext: {}", data),
                    error -> log.error("# onError:", error)
            );
    }
}
