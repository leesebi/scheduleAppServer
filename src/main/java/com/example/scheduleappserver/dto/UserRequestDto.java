package com.example.scheduleappserver.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String nickname;
    @Pattern(regexp = "[a-z0-9]{4,10}")
    private String username;
    @Pattern(regexp = "[a-zA-Z0-9]{8,15}")
    private String password;
    private boolean admin = false;
    private String adminToken ="";
}
