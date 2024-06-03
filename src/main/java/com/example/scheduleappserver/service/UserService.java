package com.example.scheduleappserver.service;

import com.example.scheduleappserver.dto.user.LoginRequestDto;
import com.example.scheduleappserver.dto.user.LoginResponseDto;
import com.example.scheduleappserver.dto.user.UserRequestDto;
import com.example.scheduleappserver.dto.user.UserResponseDto;
import com.example.scheduleappserver.entity.User;
import com.example.scheduleappserver.entity.UserRole;
import com.example.scheduleappserver.jwt.JwtUtil;
import com.example.scheduleappserver.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public UserResponseDto signup(UserRequestDto request) {
        // 회원 중복확인
        Optional<User> checkUser= repository.findByUsername(request.getUsername());
        if(checkUser.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        //사용자 role 확인 -> admin이면 token이 맞는지 비교 아니면 user
        UserRole role = UserRole.USER;
        if(request.isAdmin()){
            if(!request.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 다릅니다.");
            }
            role = UserRole.ADMIN;
        }

        // 사용자 저장
        User user = new User(request, role);
        repository.save(user);

        // 반환
        return new UserResponseDto(user);
    }

    public LoginResponseDto login(LoginRequestDto request, HttpServletResponse res) {
        // 회원 조회
        Optional<User> checkUser = repository.findByUsername(request.getUsername());
        if(checkUser.isEmpty()){
            throw new IllegalArgumentException("이름을 다시 확인해 주세요");
        }

        // 비밀번호 조회
        User user = checkUser.orElseThrow();
        if(!user.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요");
        }

        // 토큰 생성
        String token = jwtUtil.createToken(request.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token, res); // 클라이언트에 전달함

        return new LoginResponseDto(token);
    }


}
