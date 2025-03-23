package com.example.websocket_chat.Controller;

import com.example.websocket_chat.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class WebSocketController {

    @MessageMapping("/chat/{roomId}")  // 방 번호 처리
    @SendTo("/sub/chat/{roomId}")  // 특정 방 에만 메시지 뿌림.
    public MessageDto sendMessage(@Payload MessageDto chatMessage, @DestinationVariable String roomId) {
        chatMessage.setType(MessageDto.MessageType.CHAT);
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessage.setRoomId(roomId);
        return chatMessage;
    }

}

