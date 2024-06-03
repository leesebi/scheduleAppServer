package com.example.scheduleappserver.controller;

import com.example.scheduleappserver.dto.CommonResponse;
import com.example.scheduleappserver.dto.comment.CommentRequestDto;
import com.example.scheduleappserver.dto.comment.CommentResponseDto;
import com.example.scheduleappserver.dto.comment.CommentUpdateRequestDto;
import com.example.scheduleappserver.entity.Comment;
import com.example.scheduleappserver.entity.User;
import com.example.scheduleappserver.service.CommentService;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @PostMapping
    public ResponseEntity<CommonResponse<CommentResponseDto>> createComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto requestDto,
                                                                            ServletRequest request) {
        User user = (User) request.getAttribute("user");
        Comment comment = service.save(scheduleId, requestDto, user);
        CommentResponseDto responseDto = new CommentResponseDto(comment);

        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("생성이 완료되었습니다.")
                .data(responseDto)
                .build());
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponse<CommentResponseDto>> updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId,
                                            @RequestBody CommentUpdateRequestDto requestDto, ServletRequest request) {
        User user = (User) request.getAttribute("user");
        CommentResponseDto responseDto = service.update(scheduleId, commentId, requestDto, user);

        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("생성이 완료되었습니다.")
                .data(responseDto)
                .build()
        );
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponse<CommentResponseDto>> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId,
                                                            ServletRequest request) {
        User user = (User) request.getAttribute("user");
        service.delete(scheduleId, commentId, user);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto> builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료되었습니다")
                .build());
    }


}
