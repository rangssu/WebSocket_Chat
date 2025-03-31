package com.example.websocket_chat.entity;

import com.example.websocket_chat.dto.request.UserRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String userName;

    @NotBlank
    private String passWord;

    public Users(String userName, String passWord) {
        this.userName = userName;
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
