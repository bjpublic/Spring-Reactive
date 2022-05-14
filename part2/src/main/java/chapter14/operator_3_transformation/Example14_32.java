package chapter14.operator_3_transformation;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;

/**
 * concat 예제
 */
@Slf4j
public class Example14_32 {
    public static void main(String[] args) {
        Flux
                .concat(
                        Flux.fromIterable(getViralVector()),
                        Flux.fromIterable(getMRNA()),
                        Flux.fromIterable(getSubunit()))
                .subscribe(data -> log.info("# onNext: {}", data));
    }

    private static List<Tuple2<SampleData.CovidVaccine, Integer>> getViralVector() {
        return SampleData.viralVectorVaccines;
    }

    private static List<Tuple2<SampleData.CovidVaccine, Integer>> getMRNA() {
        return SampleData.mRNAVaccines;
    }

    private static List<Tuple2<SampleData.CovidVaccine, Integer>> getSubunit() {
        return SampleData.subunitVaccines;
    }
}