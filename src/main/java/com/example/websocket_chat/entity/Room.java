package com.example.websocket_chat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="users_username")
    private Users users;

//    private string password;


    public Room(String title) {
        this.title = title;
    }
    public Room(String title, Users users) {
        this.title = title;
        this.users = users;
    }

    public Room(Long id, String title, Users users) {
        this.id = id;
        this.title = title;
        this.users = users;
    }

    public boolean checkUser(Users users) {
        return this.getUsers().equals(users);
    }


}
