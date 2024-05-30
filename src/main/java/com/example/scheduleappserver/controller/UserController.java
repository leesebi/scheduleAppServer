package com.example.scheduleappserver.controller;

import com.example.scheduleappserver.dto.user.LoginRequestDto;
import com.example.scheduleappserver.dto.user.LoginResponseDto;
import com.example.scheduleappserver.dto.user.UserRequestDto;
import com.example.scheduleappserver.dto.user.UserResponseDto;
import com.example.scheduleappserver.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody UserRequestDto request){
        UserResponseDto responseDto = userService.signup(request);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request, HttpServletResponse res){
        LoginResponseDto responseDto = userService.login(request, res);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
