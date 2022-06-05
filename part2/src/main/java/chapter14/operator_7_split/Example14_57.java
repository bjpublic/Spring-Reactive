package chapter14.operator_7_split;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * split 예제
 *  - groupBy(keyMapper) Operator
 *      - emit되는 데이터를 key를 기준으로 그룹화 한 GroupedFlux를 리턴한다.
 *      - 그룹화 된 GroupedFlux로 그룹별 작업을 할 수 있다.
 */
@Slf4j
public class Example14_57 {
    public static void main(String[] args) {
        Flux.fromIterable(SampleData.books)
                .groupBy(book -> book.getAuthorName())
                .flatMap(groupedFlux ->
                        groupedFlux
                                .map(book -> book.getBookName() +
                                        "(" + book.getAuthorName() + ")")
                                .collectList()
                )
                .subscribe(bookByAuthor ->
                        log.info("# book by author: {}", bookByAuthor));
    }
}