package com.example.scheduleappserver.entity;

import com.example.scheduleappserver.dto.comment.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_fk")
    private User user;

    public Comment(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.userName = requestDto.getUserName();
        this.createdAt = LocalDateTime.now();
    }


    public void commentUser(User user){
        this.user = user;
    }
}
