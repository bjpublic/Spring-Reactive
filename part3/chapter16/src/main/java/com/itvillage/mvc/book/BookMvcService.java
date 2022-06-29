package com.itvillage.mvc.book;

import org.springframework.stereotype.Service;

@Service
public class BookMvcService {
    public Book createBook(Book book) {
        // not implement business logic;
        return book;
    }

    public Book updateBook(Book book) {
        // not implement business logic;
        return book;
    }

    public Book findBook(long bookId) {
        return Book.builder()
                .bookId(bookId)
                .titleKorean("Java 고급")
                .titleEnglish("Advanced Java")
                .author("Kevin")
                .isbn("111-11-1111-111-1")
                .description("Java 중급 프로그래밍 마스터")
                .publishDate("2022-03-22")
                .build();
    }
}
