package com.example.websocket_chat.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class webSocketHandler extends TextWebSocketHandler {

    // 연결된 클라이언트 세션을 저장하는 SET
    private final Set<WebSocketSession> sessions = new HashSet<>();

    // 새로운 WebSocket 연결이 될때 호출하게 함.
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} 연결됨", session.getId());
        sessions.add(session);
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(session.getId()+"websocket 연결 성공"));
        }
    }

    // 메시지를 받을 때 호출됨
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        log.info("payload {}", msg);
        // 연결된 모든 사람에게 메시지 전송
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(msg));
        }
    }

    // 연결이 닫힐 때 호출함.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 종료된 세션을 제거함.
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(session.getId()+"websocket 연결 종료"));
        }
//        session.sendMessage(new TextMessage("websocket 연결 종료"));
    }
}

//// 실행 시간 비교 코드
//@Override
//public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//    long startRemoveTime = System.currentTimeMillis();
//    sessions.remove(session);
//    long endRemoveTime = System.currentTimeMillis();
//    log.info("세션 제거 실행 시간: {} ms", (endRemoveTime - startRemoveTime));
//
//    long startSendTime = System.currentTimeMillis();
//    session.sendMessage(new TextMessage("websocket 연결 종료"));
//    long endSendTime = System.currentTimeMillis();
//    log.info("메시지 전송 실행 시간: {} ms", (endSendTime - startSendTime));
//}
