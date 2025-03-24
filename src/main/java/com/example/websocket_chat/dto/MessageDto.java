package com.example.websocket_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private String roomId;
    private String userId;
    private String content;
    private MessageType type;
    private LocalDateTime timestamp;    // 아직 쓸모 없음.

    public enum MessageType {
        CHAT, ENTER, LEAVE
    }
}
