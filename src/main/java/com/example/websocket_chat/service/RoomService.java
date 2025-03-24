package com.example.websocket_chat.service;

import com.example.websocket_chat.entity.Room;
import com.example.websocket_chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
   private final RoomRepository roomRepository;

   public void createroom(Long id, String title) {

      roomRepository.save(new Room(id, title));

   }

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



*/