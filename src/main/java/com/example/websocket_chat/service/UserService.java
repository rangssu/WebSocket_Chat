package com.example.websocket_chat.service;

import com.example.websocket_chat.dto.request.UserRequestDTO;
import com.example.websocket_chat.entity.Users;
import com.example.websocket_chat.repository.UserJpaRepository;
import com.example.websocket_chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;

//  Member C
    @Transactional
    public Users registerMember(UserRequestDTO userRequestDTO) {
        validateUserNameCheck(userRequestDTO.getUsername());

        Users users = new Users(userRequestDTO.getUsername(), userRequestDTO.getPassword());
        return userJpaRepository.save(users);
//      return userResponseDTO
    }

    public void validateUserNameCheck(String username) {
//        if(userRepository.findByUserName(userName).isPresent()) {
//            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
//        }
//      jpa 활용 방식.
        if (userJpaRepository.existsByUsername(username)){
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
    }

// Member R ( 로그인 )
    public Users login(UserRequestDTO userRequestDTO) {
        Users users = userRepository.fetchByUserName(userRequestDTO.getUsername());

        userRepository.validateCorrectPassword(users, userRequestDTO.getPassword());
//        if (!users1.getPassWord().equals(userRequestDTO.getPassWord())) {
//            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
//        }
//      else 를 안쓸 방법.
        return users;
    }


// Member U
    @Transactional
    public Users UpdateMember(UserRequestDTO userRequestDTO) {
        Users users = userRepository.fetchByUserName(userRequestDTO.getUsername());
        users.setUsername(userRequestDTO.getUsername());
        users.setPassword(userRequestDTO.getPassword());

        return users;
    }

// Member D
    @Transactional
    public void deleteMember(UserRequestDTO userRequestDTO) {
        Users users = userRepository.fetchByUserName(userRequestDTO.getUsername());
        userRepository.validateCorrectPassword(users, userRequestDTO.getPassword());

        userJpaRepository.delete(users);
    }

//    다 userRepository 드갔음.
////  아이디 확인
//    public Users validateUsername(String username) {
//        return userJpaRepository.findByUserName(username).orElseThrow(
//                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
//        );
//    }

//// 비밀번호 확인
//    public void validateCorrectPassword(Users users, String password) {
//        if (!users.checkPW(password)) {
//            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
//        }
//    }


}

/*
    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 */
