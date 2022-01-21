package com.itvillage;

import com.itvillage.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 오프라인 서점의 검색용 PC에서 들어오는 요청을 처리하는 Spring WebFlux 기반
 * 지점 API Server
 */
@Slf4j
@RequestMapping(path = "/v1/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
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
        Thread.sleep(200);

        Book book = bookMap.get(bookId);

        return Mono.just(book);
    }
}
