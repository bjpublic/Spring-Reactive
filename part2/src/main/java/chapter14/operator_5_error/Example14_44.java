package chapter14.operator_5_error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

/**
 * error 처리 예제
 *  - error Operator
 *      - 명시적으로 error 이벤트를 발생시켜야 하는 경우
 *      - flatMap처럼 Inner Sequence가 존재하는 경우 체크 예외 발생 시 Flux로 래핑해서 onError Signal을 전송할 수 있다.
 */
@Slf4j
public class Example14_44 {
    public static void main(String[] args) {
        Flux
            .just('a', 'b', 'c', '3', 'd')
            .flatMap(letter -> {
                try {
                    return convert(letter);
                } catch (DataFormatException e) {
                    return Flux.error(e);
                }
            })
            .subscribe(data -> log.info("# onNext: {}", data),
                    error -> log.error("# onError: ", error));
    }

    private static Mono<String> convert(char ch) throws DataFormatException {
        if (!Character.isAlphabetic(ch)) {
            throw new DataFormatException("Not Alphabetic");
        }
        return Mono.just("Converted to " + Character.toUpperCase(ch));
    }
}