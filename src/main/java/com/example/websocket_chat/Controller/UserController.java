package com.example.websocket_chat.Controller;

import com.example.websocket_chat.dto.request.UserRequestDTO;
import com.example.websocket_chat.entity.Users;
import com.example.websocket_chat.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    //회원가입
    @PostMapping
    public ResponseEntity<Users> SignUpUser( @RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.registerMember(userRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Users> loginMember(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.login(userRequestDTO));
    }

    @PutMapping
    public ResponseEntity<Users> editMember(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.UpdateMember(userRequestDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@RequestBody UserRequestDTO userRequestDTO) {
        userService.deleteMember(userRequestDTO);
        return ResponseEntity.noContent().build();
    }

}
