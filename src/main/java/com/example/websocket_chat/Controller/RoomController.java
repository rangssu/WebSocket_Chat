package com.example.websocket_chat.Controller;

import com.example.websocket_chat.dto.RoomDTO;
import com.example.websocket_chat.dto.response.RoomResponseDTO;
import com.example.websocket_chat.entity.Room;
import com.example.websocket_chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public ResponseEntity<RoomResponseDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        // 상태 코드를 같이 보내줘서 좋

        RoomResponseDTO roomResponseDTO = roomService.createRoom(roomDTO);

//      return ResponseEntity.ok(명시값);
        return ResponseEntity.ok(roomResponseDTO);
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
    public List<RoomResponseDTO> listRooms() {
        List<RoomResponseDTO> roomResponseDTOList = roomService.roomList();
        return roomService.roomList();
    }

}
// get post put del -> 주소 하나로 모든걸 할 수 있음 .

