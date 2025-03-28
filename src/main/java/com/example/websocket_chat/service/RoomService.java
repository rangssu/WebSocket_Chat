package com.example.websocket_chat.service;

import com.example.websocket_chat.dto.RoomDTO;
import com.example.websocket_chat.dto.response.RoomResponseDTO;
import com.example.websocket_chat.entity.Room;
import com.example.websocket_chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
   private final RoomRepository roomRepository;

   // Void로 써도되는데 Test 용으로 확인하기 위해서.
   public RoomResponseDTO createRoom(RoomDTO roomDTO) {
      Room room = roomRepository.save(roomDTO.toRoom());
      // 엔티티에 있는 데이터 값을 DTO에 넣어서 레이어 간의 이동을 하기 위함.//       roomDTO << title 소유중
//      Getter 를 안쓰고 이거를 Entity로 해야한다

      RoomResponseDTO roomResponseDTO = RoomResponseDTO.of(room);

//      RoomDTO<Room> room1 = new Room(roomDTO)''
//      Room room = new Room();

      return roomResponseDTO;
//    return : Room 타입임.
   }

   public List<RoomResponseDTO> roomList(){
      List<RoomResponseDTO> roomResponseDTOList = new ArrayList<RoomResponseDTO>();
      List<Room> roomList = roomRepository.findAll();

      for(int i = 0 ; i < roomList.size() ; i++ ){
//       RoomResponseDTO roomss = new RoomResponseDTO(rooms.get(i).getId(), rooms.get(i).getTitle());
         RoomResponseDTO roomss = RoomResponseDTO.of(roomList.get(i));
         roomResponseDTOList.add(roomss);
      }

      return roomResponseDTOList;

//      roomList.stream()
//              .map(RoomResponseDTO::of)
//              .toList();
   }



}

//   public List<RoomResponseDTO> listRooms(List<Room> rooms){
//      List<RoomResponseDTO> dtos = new ArrayList<RoomResponseDTO>();
//      for(int i = 0 ; i < rooms.size() ; i++ ){
////            RoomResponseDTO roomss = new RoomResponseDTO(rooms.get(i).getId(), rooms.get(i).getTitle());
//         RoomResponseDTO roomss = RoomResponseDTO.of(rooms.get(i));
//         dtos.add(roomss);
//      }
//      return dtos;
//   }






/*
   controller 에서 요청한걸 ? -> repository 에서 받고 service 에서 기능을 해줌\
   그러면 repository 에선 ? entity 를 받았으니까
   service 에서는 그걸 사용 해야겠지 ?
   그러면 이제 그걸 어떻게 써야 하는가

   방을 생성 ? 할 때 써야 하는데

   생성자가 없을때 . -> 다 있을 떄 까지
   모든 경우의 수를 만듬.

   .save (object entity)

   a  = 10 이라는 멤버 변수를 가진 circle 클래스를 만든 후 , 객체를 생성.

      public class Circle {
      int a;

      public Circle(int a){
         this.a = a;
      }

   }

   //Circle circle = new Circle();
   Circle circle2 = new Circle(10);
//  └ 타입 └ 객체명     └ 객체 생성

   Circle circle  = new Circle(20);
//

}


*/