package com.itvillage.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Book {
    private long bookId;
    private String titleKorean;
    private String titleEnglish;
    private String description;
    private String author;
    private String isbn;
    private String publishDate;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
