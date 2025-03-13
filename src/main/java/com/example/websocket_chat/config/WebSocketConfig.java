package com.example.websocket_chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration      // 스프링 설정 클래스임.
@EnableWebSocket    // websocket 활성화 어노테이션
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                // /ws/conn 경로로 WebSocket 연결을 허용
                .addHandler(webSocketHandler, "/ws/conn")
                .setAllowedOrigins("http://localhost:8080");
    }

}