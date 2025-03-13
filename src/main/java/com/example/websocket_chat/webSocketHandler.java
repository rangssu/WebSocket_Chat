package com.example.websocket_chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

// 메시지 처리
//Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용할 수 있다.
@Component
@RequiredArgsConstructor
public class webSocketHandler extends TextWebSocketHandler {

    // 연결된 클라이언트 세션을 저장하는 SET
    private final Set<WebSocketSession> sessions = new HashSet<>();

    // 새로운 WebSocket 연결이 될때 호출하게 함.
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        session.sendMessage(new TextMessage("web socket 연결 완료"));
    }

    // 메시지를 받을 때 호출됨
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        // 연결된 모든 사람에게 메시지 전송
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(msg));
        }
    }

    // 연결이 닫힐 때 호출함.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 종료된 세션을 제거함.
        sessions.remove(session);
        session.sendMessage(new TextMessage("websocket 연결 종료"));
    }
}
