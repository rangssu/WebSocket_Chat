package com.example.websocket_chat.Controller;

import com.example.websocket_chat.dto.request.UserRequestDTO;
import com.example.websocket_chat.entity.Users;
import com.example.websocket_chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public Users SignUpUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.registerMember(userRequestDTO);
    }

    @PostMapping("/login")
    public void loginMember(@RequestBody UserRequestDTO userRequestDTO) {


    }

}
