package com.example.scheduleappserver.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String token;
    private String msg;


    public LoginResponseDto(String token) {
        this.token = token;
    }
}
