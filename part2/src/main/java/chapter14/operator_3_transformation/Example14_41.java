package chapter14.operator_3_transformation;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * collectMap 예제
 */
@Slf4j
public class Example14_41 {
    public static void main(String[] args) {
        Flux
            .range(0, 26)
            .collectMap(key -> SampleData.morseCodes[key],
                    value -> transformToLetter(value))
            .subscribe(map -> log.info("# onNext: {}", map));
    }

    private static String transformToLetter(int value) {
        return Character.toString((char) ('a' + value));
    }
}