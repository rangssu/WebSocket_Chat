package com.example.websocket_chat.dto.request;

import lombok.Getter;

@Getter
public class UserRequestDTO {

    private String username;

    private String passWord;

    public void aaa(String username, String passWord ) {
        username = getUsername();
        passWord = getPassWord();
    }

}
