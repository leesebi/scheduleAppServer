package com.example.scheduleappserver.dto.schedule;

import com.example.scheduleappserver.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final Integer password;
    private final String title;
    private final String content;
    private final String manager;
    private final LocalDateTime createdAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.password = schedule.getPassword();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.createdAt = schedule.getCreatedAt();
    }
}
