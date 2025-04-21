package com.example.websocket_chat.repository;

import com.example.websocket_chat.entity.Users;
import com.example.websocket_chat.exception.ExceptionCode;
import com.example.websocket_chat.exception.WsChatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;

    public Users fetchByUserName(String username) {
        return userJpaRepository.findByUsername(username).orElseThrow(
                () -> new WsChatException(ExceptionCode.USER_NOT_FOUND)
        );

    }
    // 비밀번호 확인
    public void validateCorrectPassword(Users users, String password) {
        if (!users.checkPW(password)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }
}
