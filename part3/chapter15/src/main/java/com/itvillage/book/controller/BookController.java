package com.itvillage.book.controller;

import com.itvillage.book.dto.BookDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/controller/books")
public class BookController {
    @GetMapping("/{book-id}")
    public Mono<BookDto.Response> getBook(@PathVariable("book-id") long bookId) {
        return Mono.just(BookDto.Response.builder()
                .bookId(bookId)
                .bookName("Advanced Java")
                .author("Kevin")
                .isbn("111-11-1111-111-1").build());
    }
}
