package com.itvillage.exception;

import lombok.Getter;

public enum ExceptionCode {
    BOOK_NOT_FOUND(404, "Book not found"),
    BOOK_EXISTS(409, "Book exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
