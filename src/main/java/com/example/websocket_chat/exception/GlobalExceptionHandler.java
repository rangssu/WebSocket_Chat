package com.example.websocket_chat.exception;

import com.example.websocket_chat.exception.WsErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WsChatException.class)  // WsChatException이 발생했을 때
    public ResponseEntity<WsErrorResponse> handleWsChatException(WsChatException e) {
        // 예외 정보 로그로 출력
        log.warn("WsChatException [statusCode = {}, errorMessage = {}, cause = {}]", e.getHttpStatus(), e.getMessage(), e.getStackTrace());

        // ErrorResponse 객체 생성 및 반환
        return ResponseEntity.status(e.getHttpStatus()).body(WsErrorResponse.from(e));  // ErrorResponse 생성
    }

}