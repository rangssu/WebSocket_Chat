package com.example.websocket_chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestDTO {

    @NotBlank(message = "아이디를 반드시 입력 해 주세요")
    private String username;

    @NotBlank
    private String password;


}
