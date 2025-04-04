package com.example.websocket_chat.repository;

import com.example.websocket_chat.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;

    public Users fetchByUserName(String username) {
        return userJpaRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

    }
    // 비밀번호 확인
    public void validateCorrectPassword(Users users, String password) {
        if (!users.checkPW(password)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }
}
