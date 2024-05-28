package com.example.scheduleappserver.dto;

import com.example.scheduleappserver.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
    }
}
