package com.itvillage.book.v10;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String message;

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message);
    }
}
