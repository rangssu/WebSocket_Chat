package com.example.websocket_chat.exception;

import com.example.websocket_chat.exception.WsChatException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WsErrorResponse {
    private final int status;
    private final String message;

    public static WsErrorResponse from(WsChatException e) {
        return new WsErrorResponse(e.getHttpStatus().value(), e.getMessage());
    }
}
