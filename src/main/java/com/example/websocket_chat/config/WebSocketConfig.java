package com.example.websocket_chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration      // 스프링 설정 클래스임.
@EnableWebSocketMessageBroker    // websocket 활성화 어노테이션
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // sub 으로 시작되는 요청을 구독한 모든 사용자들에게 메세지를 브로드캐스트 함 . (subscribe)
        registry.enableSimpleBroker("/sub");
        // pub 으로 시작되는 메시지는 message-handling method 로 라우팅 됨.
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();

    }

}