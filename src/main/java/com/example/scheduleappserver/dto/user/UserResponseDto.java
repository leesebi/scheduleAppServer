package com.example.scheduleappserver.dto.user;

import com.example.scheduleappserver.entity.User;
import com.example.scheduleappserver.entity.UserRoleEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final String nickname;
    private final String username;
    private final String password;
    private final UserRoleEnum role;
    private final LocalDateTime createdAt;

    public UserResponseDto(User user){
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.createdAt = LocalDateTime.now();
    }


}
