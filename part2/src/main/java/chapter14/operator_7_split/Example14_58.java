package chapter14.operator_7_split;

import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * split 예제
 *  - groupBy(keyMapper, valueMapper) Operator
 *      - emit되는 데이터를 key를 기준으로 그룹화 한 GroupedFlux를 리턴한다.
 *      - 그룹화 된 GroupedFlux로 그룹별 작업을 할 수 있다.
 *      - valueMapper를 추가로 전달해서 그룹화 되어 emit되는 데이터의 값을 미리 다른 값으로 변경할 수 있다.
 */
@Slf4j
public class Example14_58 {
    public static void main(String[] args) {
        Flux.fromIterable(SampleData.books)
                .groupBy(book ->
                        book.getAuthorName(),
                        book -> book.getBookName() + "(" + book.getAuthorName() + ")")
                .flatMap(groupedFlux -> groupedFlux.collectList())
                .subscribe(bookByAuthor ->
                        log.info("# book by author: {}", bookByAuthor));
    }
}