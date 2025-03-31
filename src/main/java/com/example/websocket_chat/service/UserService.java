package com.example.websocket_chat.service;

import com.example.websocket_chat.dto.request.UserRequestDTO;
import com.example.websocket_chat.entity.Users;
import com.example.websocket_chat.repository.UserJpaRepository;
import com.example.websocket_chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;

//  Member C
    @Transactional
    public Users registerMember(UserRequestDTO userRequestDTO) {
//      아이디 체크
        validateUserNameCheck(userRequestDTO.getUserName());

        Users users = new Users(userRequestDTO.getUserName(), userRequestDTO.getPassWord());
        return userJpaRepository.save(users);
//      return userResponseDTO
    }

    public void validateUserNameCheck(String userName) {
//        if(userRepository.findByUserName(userName).isPresent()) {
//            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
//        }
//      jpa 활용 방식.
        if (userJpaRepository.existsByUserName(userName)){
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
    }

// Member R ( 로그인 )
    public Users login(UserRequestDTO userRequestDTO) {
        Users users = userRepository.fetchByUserName(userRequestDTO.getUserName());

        validateCorrectPassword(users, userRequestDTO.getPassWord());


//        if (!users1.getPassWord().equals(userRequestDTO.getPassWord())) {
//            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
//        }
//      else를 안쓸 방법.

        return users;
    }

    public void validateCorrectPassword(Users users, String password) {
        if (!users.checkPW(password)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }

// Member U
    @Transactional
    public Users UpdateMember(UserRequestDTO userRequestDTO) {
        Users users = userJpaRepository.findByUserName(userRequestDTO.getUserName()).orElseThrow(
                        () -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
        );
        users.setUserName(userRequestDTO.getUserName());
        users.setPassWord(userRequestDTO.getPassWord());

        return users;
    }

// Member D
    @Transactional
    public void deleteMember(UserRequestDTO userRequestDTO) {
        Users users = userJpaRepository.findByUserName(userRequestDTO.getUserName()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        validateCorrectPassword(users, userRequestDTO.getPassWord());

        userJpaRepository.delete(users);
    }




}

/*
    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 */
