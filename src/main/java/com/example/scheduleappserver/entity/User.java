package com.example.scheduleappserver.entity;

import com.example.scheduleappserver.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String nickname;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private LocalDateTime createdAt;

    @Enumerated(value=EnumType.STRING)
    private UserRoleEnum role;
    public User(UserRequestDto request, UserRoleEnum role) {
        this.nickname = request.getNickname();
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }
}
