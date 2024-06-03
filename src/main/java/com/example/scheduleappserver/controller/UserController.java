package com.example.scheduleappserver.controller;

import com.example.scheduleappserver.dto.CommonResponse;
import com.example.scheduleappserver.dto.user.LoginRequestDto;
import com.example.scheduleappserver.dto.user.LoginResponseDto;
import com.example.scheduleappserver.dto.user.UserRequestDto;
import com.example.scheduleappserver.dto.user.UserResponseDto;
import com.example.scheduleappserver.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<UserResponseDto>> signup(@Valid @RequestBody UserRequestDto request){
        UserResponseDto responseDto = userService.signup(request);
        return ResponseEntity.ok().body(CommonResponse.<UserResponseDto> builder()
                .statusCode(HttpStatus.OK.value())
                .msg("회원가입이 완료되었습니다")
                .data(responseDto)
                .build());
    }

    @GetMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto request, HttpServletResponse res){
        LoginResponseDto responseDto = userService.login(request, res);
        return ResponseEntity.ok().body(CommonResponse.<LoginResponseDto> builder()
                .statusCode(HttpStatus.OK.value())
                .msg("로그인이 완료되었습니다")
                .data(responseDto)
                .build());
    }

}
