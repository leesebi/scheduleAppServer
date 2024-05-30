package com.example.scheduleappserver.dto;

import com.example.scheduleappserver.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private Integer password;
    private String title;
    private String content;
    private String manager;
    private LocalDateTime createdAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.password = schedule.getPassword();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.createdAt = schedule.getCreatedAt();
    }
}
