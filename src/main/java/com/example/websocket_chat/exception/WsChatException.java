package com.example.websocket_chat.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WsChatException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public WsChatException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public HttpStatus getHttpStatus() {
        return exceptionCode.getHttpstatus();
    }
}
