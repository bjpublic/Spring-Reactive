package chapter14.operator_5_error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * error 처리 예제
 *  - error Operator
 *      - 명시적으로 error 이벤트를 발생시켜야 하는 경우
 */
@Slf4j
public class Example14_43 {
    public static void main(String[] args) {
        Flux
            .range(1, 5)
            .flatMap(num -> {
                if ((num * 2) % 3 == 0) {
                    return Flux.error(
                            new IllegalArgumentException("Not allowed multiple of 3"));
                } else {
                    return Mono.just(num * 2);
                }
            })
            .subscribe(data -> log.info("# onNext: {}", data),
                    error -> log.error("# onError: ", error));
    }
}