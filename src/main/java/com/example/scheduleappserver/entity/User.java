package com.example.scheduleappserver.entity;

import com.example.scheduleappserver.dto.user.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//    @JoinColumn(name = "user_id_fK")
    private List<Comment> comments = new ArrayList<>();

    public User(UserRequestDto request, UserRole role) {
        this.nickname = request.getNickname();
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }
}
