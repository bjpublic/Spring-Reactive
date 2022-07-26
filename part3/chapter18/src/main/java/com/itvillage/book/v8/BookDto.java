package com.itvillage.book.v8;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class BookDto {
    @Getter
    public static class Post {
        @NotBlank
        private String titleKorean;

        @NotBlank
        private String titleEnglish;

        @NotBlank
        private String description;

        @NotBlank
        private String author;

        @NotBlank
        private String isbn;

        @NotBlank
        private String publishDate;
    }

    @Getter
    public static class Patch {
        @Setter
        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }

    @Builder
    @Getter
    public static class Response {
        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }
}
