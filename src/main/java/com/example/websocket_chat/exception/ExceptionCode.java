package com.example.websocket_chat.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    // 일단 방이랑 사용자 없는거만. 나중에 추가 할지도 ?
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"사용자를 찾을 수 없습니다." ),
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND,"방을 찾을 수 없습니다.");

    private final HttpStatus httpstatus;
    private final String message;

    ExceptionCode(HttpStatus httpstatus, String message) {
        this.httpstatus = httpstatus;
        this.message = message;
    }

}
