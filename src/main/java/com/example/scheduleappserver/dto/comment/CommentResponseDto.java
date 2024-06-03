package com.example.scheduleappserver.dto.comment;

import com.example.scheduleappserver.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private final Long id;
    private final String userId;
    private final String content;
    private final LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
