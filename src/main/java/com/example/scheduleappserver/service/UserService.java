package com.example.scheduleappserver.service;

import com.example.scheduleappserver.dto.UserRequestDto;
import com.example.scheduleappserver.dto.UserResponseDto;
import com.example.scheduleappserver.entity.User;
import com.example.scheduleappserver.entity.UserRoleEnum;
import com.example.scheduleappserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public UserResponseDto signup(UserRequestDto request) {
        // 회원 중복확인
        Optional<User> checkUser= repository.findByUsername(request.getUsername());
        if(checkUser.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        //사용자 role 확인 -> admin이면 token이 맞는지 비교 아니면 user
        UserRoleEnum role = UserRoleEnum.USER;
        if(request.isAdmin()){
            if(!request.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 다릅니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 저장
        User user = new User(request, role);
        repository.save(user);

        // 반환
        return new UserResponseDto(user);
    }
}
