package com.example.websocket_chat.Controller;

import com.example.websocket_chat.dto.RoomDTO;
import com.example.websocket_chat.dto.request.UserRequestDTO;
import com.example.websocket_chat.dto.response.RoomResponseDTO;
import com.example.websocket_chat.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/rooms")
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
        log.info(roomDTO.getUsername());
        RoomResponseDTO roomResponseDTO = roomService.createRoom(roomDTO);

//      return ResponseEntity.ok(명시값);
        return ResponseEntity.ok().body(roomResponseDTO);
        // └ 요청 받아서 온 값이 들어감.
    }


    // 방삭제
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId, @RequestBody UserRequestDTO userRequestDTO) {
        roomService.deleteRoom(roomId,userRequestDTO);
        return ResponseEntity.noContent().build();
    }

    // 방 수정
    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponseDTO> editRoom(@PathVariable Long roomId, @RequestBody RoomDTO roomDTO) {
       return ResponseEntity.ok(roomService.updateRoom(roomId, roomDTO));
    }

    // 방 상세보기
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponseDTO> enterRoom(@PathVariable Long roomId) {
       return ResponseEntity.ok(roomService.enterRoom(roomId));
    }

    // 방리스트.
    @GetMapping
    public List<RoomResponseDTO> listRooms() {
        List<RoomResponseDTO> roomResponseDTOList = roomService.roomList();
        return roomService.roomList();
    }

}
// get post put del -> 주소 하나로 모든걸 할 수 있음 .

