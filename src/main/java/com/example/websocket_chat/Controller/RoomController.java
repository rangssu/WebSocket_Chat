package com.example.websocket_chat.Controller;

import com.example.websocket_chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
//@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

//    생성자로 초기화
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // 방 생성
    @PostMapping
    public void createRoom(Long id, String title) {
        roomService.createroom(id, title);
        // └ 요청 받아서 온 값이 들어감.

    }

    // 방삭제
    @DeleteMapping("/{roomId}")
    public void deleteRoom(){
//        ~~ id
    }

    // 방 수적
    @PutMapping("/{roomId}")
    public void editRoom(){
//        room의 아이디를 받아서 title 수정
    }

    // 방 상세보기
    @GetMapping("/{roomId}")
    public void enterRoom() {

    }

    @GetMapping
    public void listRooms() {

    }

}
// get post put del -> 주소 하나로 모든걸 할 수 있음 .

