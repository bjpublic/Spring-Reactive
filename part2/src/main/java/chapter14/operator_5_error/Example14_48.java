package chapter14.operator_5_error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * error 처리 예제
 *  - onErrorContinue Operator
 *      - 예외가 발생했을 때, 예외를 발생시킨 데이터를 건너뛰고 Upstream에서 emit된 다음 데이터를
 *        처리한다.
 */
@Slf4j
public class Example14_48 {
    public static void main(String[] args) {
        Flux
            .just(1, 2, 4, 0, 6, 12)
            .map(num -> 12 / num)
            .onErrorContinue((error, num) ->
                    log.error("error: {}, num: {}", error, num))
            .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));
    }
}