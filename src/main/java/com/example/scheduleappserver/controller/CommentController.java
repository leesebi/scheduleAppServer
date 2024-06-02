package com.example.scheduleappserver.controller;

import com.example.scheduleappserver.dto.comment.CommentRequestDto;
import com.example.scheduleappserver.dto.comment.CommentResponseDto;
import com.example.scheduleappserver.dto.comment.CommentUpdateRequestDto;
import com.example.scheduleappserver.entity.User;
import com.example.scheduleappserver.service.CommentService;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping
    public void createComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto requestDto,
                              ServletRequest request){
        User user = (User) request.getAttribute("user");
        service.save(scheduleId, requestDto, user);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId,
                                            @RequestBody CommentUpdateRequestDto requestDto, ServletRequest request){
        User user = (User) request.getAttribute("user");
        return service.update(scheduleId, commentId, requestDto, user);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId,
                                                            ServletRequest request){
        User user = (User) request.getAttribute("user");
        service.delete(scheduleId, commentId, user);
        return ResponseEntity.ok().build();
    }



}
