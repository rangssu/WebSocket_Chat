package com.example.websocket_chat.entity;

import com.example.websocket_chat.dto.request.UserRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String passWord;

    public Users(String username, String passWord) {
        this.username = username;
        this.passWord = passWord;
    }

    public boolean checkPW(String passWord) {
        return this.passWord.equals(passWord);
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
