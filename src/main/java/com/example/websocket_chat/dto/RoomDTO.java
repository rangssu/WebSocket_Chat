package com.example.websocket_chat.dto;

import com.example.websocket_chat.dto.request.UserRequestDTO;
import com.example.websocket_chat.entity.Room;
import com.example.websocket_chat.entity.Users;
import lombok.Getter;
import lombok.Setter;


@Getter
public class RoomDTO {

    private String title;
    private String username;


//    갖고있는거 갖고 Room 객체를 만들어서 반환해 줘.
    public Room toRoom(Users users) {
        return new Room(title, users);

    }



}

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
// Getter, Setter : 메서드
// 게터 세터를 사용하는 이유  :
// dto 사용 이유 : 신뢰성을 높히기 위해서 아용함. Data Transfal Object
