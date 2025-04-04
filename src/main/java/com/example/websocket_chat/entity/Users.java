package com.example.websocket_chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkPW(String password) {
        return this.password.equals(password);
    }


//    public int CheckMember(UserRequestDTO userRequestDTO) {
//
//        if(this.userName.equals(userRequestDTO.getUserName()) && this.passWord.equals(userRequestDTO.getPassWord())) {
//            return 1;
//        } else{
//            return 0;
//        }
//    }

}
