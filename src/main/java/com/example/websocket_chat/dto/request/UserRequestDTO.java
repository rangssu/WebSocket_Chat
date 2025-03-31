package com.example.websocket_chat.dto.request;

import lombok.Getter;

@Getter
public class UserRequestDTO {

    private String userName;

    private String passWord;

    public void aaa(String userName, String passWord ) {
        userName = getUserName();
        passWord = getPassWord();
    }

}
