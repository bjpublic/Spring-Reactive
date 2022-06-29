package com.itvillage;

import com.itvillage.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 오프라인 서점의 검색용 PC에서 들어오는 요청을 처리하는 Spring WebFlux 기반
 * 지점 API Server
 */
@Slf4j
@RequestMapping("/v1/books")
@RestController
public class SpringReactiveBranchOfficeController {
    private Map<Long, Book> bookMap;

    @Autowired
    public SpringReactiveBranchOfficeController(Map<Long, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") long bookId)
            throws InterruptedException {
        Thread.sleep(5000);

        Book book = bookMap.get(bookId);
        log.info("# book for response: {}, {}", book.getBookId(), book.getName());
        return Mono.just(book);
    }
}
