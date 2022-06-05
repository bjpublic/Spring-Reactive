package chapter14.operator_5_error;

import chapter14.Book;
import chapter14.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

/**
 * error 처리 예제
 *  - retry Operator
 *      - 에러가 발생했을 때, 지정된 횟수만큼 Sequence를 다시 구독한다.
 */
@Slf4j
public class Example14_50 {
    public static void main(String[] args) throws InterruptedException {
        getBooks()
                .collect(Collectors.toSet())
                .subscribe(bookSet -> bookSet.stream()
                        .forEach(book -> log.info("book name: {}, price: {}",
                                book.getBookName(), book.getPrice())));

        Thread.sleep(12000);
    }

    private static Flux<Book> getBooks() {
        final int[] count = {0};
        return Flux
                .fromIterable(SampleData.books)
                .delayElements(Duration.ofMillis(500))
                .map(book -> {
                    try {
                        count[0]++;
                        if (count[0] == 3) {
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {
                    }

                    return book;
                })
                .timeout(Duration.ofSeconds(2))
                .retry(1)
                .doOnNext(book -> log.info("# getBooks > doOnNext: {}, price: {}",
                        book.getBookName(), book.getPrice()));
    }
}