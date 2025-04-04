package com.example.websocket_chat.dto.response;


import com.example.websocket_chat.entity.Room;
import lombok.Getter;


@Getter
public class RoomResponseDTO {

    private Long id;

    private String title;

    private String username;

//    @Builder
    public RoomResponseDTO(Long id, String title, String username) {
        this.id = id;
        this.title = title;
        this.username = username;
    }

    // 내가 Room 객체를 넣어줄테니까 이 데이터를 가지고 자기 자신을 return 해줘
//    객체를 만들지 않고 메서드를 사용하는 방식.
//    값을 초기화 하지 않아서 . 값을 사용하지 않고서 주로 사용함.
    // of 는 정적 팩토리메서드의 명명 규칙
    public static RoomResponseDTO of(Room room) {

        return new RoomResponseDTO(room.getId(), room.getTitle(), room.getUsers().getUsername());
//        return RoomResponseDTO.builder()
//                .id(room.getId())
//                .title(room.getTitle())
//                .password(room.getPassWord())
//                .build();
    }


}
//Client에게 Entity를 보여주기 보다는 보여주고싶은 정보만을 추려서 ResponseDTO 를 보여주는게 좋다 ? 라고 이해함.