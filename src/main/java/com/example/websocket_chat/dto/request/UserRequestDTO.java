package com.example.websocket_chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
