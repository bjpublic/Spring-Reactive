package chapter14.operator_7_split;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.math.MathFlux;

/**
 * split 예제
 *  - window(maxSize) Operator
 *      - Upstream에서 emit되는 첫 번째 데이터부터 maxSize의 숫자만큼의 데이터를 포함하는 새로운 Flux로 분할한다.
 *      - 새롭게 생성되는 Flux를 윈도우(Window)라고 한다.
 *      - 마지막 윈도우가 포함하는 데이터는 maxSize보다 작거나 같다.
 */
@Slf4j
public class Example14_54 {
    public static void main(String[] args) {
        Flux.fromIterable(SampleData.monthlyBookSales2021)
                .window(3)
                .flatMap(flux -> MathFlux.sumInt(flux))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscription.request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("# onNext: {}", value);
                        request(2);
                    }
                });
    }
}