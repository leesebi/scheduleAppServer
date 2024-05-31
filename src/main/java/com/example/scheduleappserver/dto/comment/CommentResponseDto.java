package com.example.scheduleappserver.dto.comment;

import com.example.scheduleappserver.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private Long id;
    private String userId;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
