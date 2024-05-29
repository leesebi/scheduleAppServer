package com.example.scheduleappserver.controller;

import com.example.scheduleappserver.dto.CommentRequestDto;
import com.example.scheduleappserver.dto.CommentResponseDto;
import com.example.scheduleappserver.dto.CommentUpdateRequestDto;
import com.example.scheduleappserver.service.CommentService;
import com.example.scheduleappserver.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping("/schedule/{scheduleId}/comments")
    public void createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto requestDto){
        service.save(scheduleId, requestDto);
    }

    @PutMapping("/schedule/{scheduleId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto){
        return service.update(scheduleId, commentId, requestDto);
    }
}
