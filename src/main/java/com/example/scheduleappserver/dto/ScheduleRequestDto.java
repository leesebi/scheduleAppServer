package com.example.scheduleappserver.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private Integer password;
    private String title;
    private String content;
    private String manager;
}
