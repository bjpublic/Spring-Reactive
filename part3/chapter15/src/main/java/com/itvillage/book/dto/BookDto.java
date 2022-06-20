package com.itvillage.book.dto;

import lombok.Builder;
import lombok.Getter;

public class BookDto {
    @Builder
    @Getter
    public static class Response {
        private long bookId;
        private String bookName;
        private String author;
        private String isbn;
    }
}
