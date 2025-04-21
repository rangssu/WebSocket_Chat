package com.example.websocket_chat.service;

import com.example.websocket_chat.dto.RoomDTO;
import com.example.websocket_chat.dto.request.UserRequestDTO;
import com.example.websocket_chat.dto.response.RoomResponseDTO;
import com.example.websocket_chat.entity.Room;
import com.example.websocket_chat.entity.Users;
import com.example.websocket_chat.exception.ExceptionCode;
import com.example.websocket_chat.exception.WsChatException;
import com.example.websocket_chat.repository.RoomRepository;
import com.example.websocket_chat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
   private final RoomRepository roomRepository;
   private final UserRepository userRepository;


   // Void로 써도되는데 Test 용으로 확인하기 위해서.
   public RoomResponseDTO createRoom(RoomDTO roomDTO) {
      Users users = userRepository.fetchByUserName(roomDTO.getUsername());
      Room room = roomRepository.save(roomDTO.toRoom(users));
//      엔티티에 있는 데이터 값을 DTO에 넣어서 레이어 간의 이동을 하기 위함.//       roomDTO << title 소유중
//      Getter 를 안쓰고 이거를 Entity로 해야한다
//      RoomDTO<Room> room1 = new Room(roomDTO)''
//      Room room = new Room();

      return RoomResponseDTO.of(room);
//    return : Room 타입임.
   }

// 방리스트
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
// 삭제
   @Transactional
   public void deleteRoom(Long roomId, UserRequestDTO userRequestDTO) {
//      roomRepository.deleteById(roomId); << 이건 자동으로 nullpoint 어쩌구 해준다 했었나 ? 다시 한번 확인해야함.
      Room room = fetchRoom(roomId);  // 내부 exception
      Users user = userRepository.fetchByUserName(userRequestDTO.getUsername());

      log.info(room.getUsers().getUsername());
      log.info(user.getUsername());


      if(!room.checkUser(user)){
         throw new IllegalArgumentException("방 생성자가 아닙니다.");
      }

      roomRepository.delete(room);
//    1. 당연하게도 커스텀 익셉션이 좋음
//    2. EntityNotFoundException << 엔티티가 존재하지 않을때 발생하는 예외.
//    3. IllegalArgumentException  << roomId가 잘못된 값일경우.
//    물어볼것 . 과거에 물어봣듯이 여기서도 @Transactional 을 사용하면 좋은가 ?
//    쉽게 말해서 거래인데 내가 선입금 했는데 공기가 오면 개꼴받으니까 다시 입금한 돈을 돌려받는다는 작업 ?
//    람다식
   }

// 수정
   @Transactional
   public RoomResponseDTO updateRoom(Long roomId, RoomDTO roomDTO) {
      Room room = fetchRoom(roomId);

      room.setTitle(roomDTO.getTitle());
//    room.setPw(room.getPw)

      // roomRepository.save(room);
      // return을 할때는
//      RoomResponseDTO roomResponseDTO = RoomResponseDTO.of(room);
      return RoomResponseDTO.of(room);

   }

// 방이 있는지
   public RoomResponseDTO enterRoom(Long roomId) {
      Room roomCheck = roomRepository.findById(roomId).orElseThrow(
              () -> new WsChatException(ExceptionCode.ROOM_NOT_FOUND)
      );
      return RoomResponseDTO.of(roomCheck) ;
   }


   public Room fetchRoom(Long roomId) {
      return roomRepository.findById(roomId).orElseThrow(
              () -> new IllegalArgumentException("해당 방이 존재하지 않습니다.")
      );
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