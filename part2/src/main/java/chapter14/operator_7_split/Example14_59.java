package chapter14.operator_7_split;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * split 예제
 *  - groupBy() Operator
 *      - emit되는 데이터를 key를 기준으로 그룹화 한 GroupedFlux를 리턴한다.
 *      - 그룹화 된 GroupedFlux로 그룹별 작업을 할 수 있다.
 *      - 저자 명으로 된 도서의 가격
 */
@Slf4j
public class Example14_59 {
    public static void main(String[] args) {
        Flux.fromIterable(SampleData.books)
                .groupBy(book -> book.getAuthorName())
                .flatMap(groupedFlux ->
                    Mono
                        .just(groupedFlux.key())
                        .zipWith(
                            groupedFlux
                                .map(book ->
                                    (int)(book.getPrice() * book.getStockQuantity() * 0.1))
                                .reduce((y1, y2) -> y1 + y2),
                                    (authorName, sumRoyalty) ->
                                        authorName + "'s royalty: " + sumRoyalty)
                )
                .subscribe(log::info);
    }
}