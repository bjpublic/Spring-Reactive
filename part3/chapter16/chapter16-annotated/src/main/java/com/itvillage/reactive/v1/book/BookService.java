package com.itvillage.reactive.v1.book;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class BookService {
    public Mono<Book> createBook(Book book) {
        // not implement business logic;
        return Mono.just(book);
    }

    public Mono<Book> updateBook(Book book) {
        // not implement business logic;
        return Mono.just(book);
    }

    public Mono<Book> findBook(long bookId) {
        return Mono.just(
                    new Book(bookId,
                            "Java 고급",
                            "Advanced Java",
                            "Kevin",
                            "111-11-1111-111-1",
                            "Java 중급 프로그래밍 마스터",
                            "2022-03-22",
                            LocalDateTime.now(),
                            LocalDateTime.now())
        );
    }
}
