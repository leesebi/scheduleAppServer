package com.example.scheduleappserver.dto.user;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String token;


    public LoginResponseDto(String token) {
        this.token = token;
    }
}
