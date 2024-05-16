package com.example.scheduleappserver.entity;

import com.example.scheduleappserver.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Schedule {
    private Long id;
    private Integer password;
    private String title;
    private String content;
    private String manager;
//    private Date createdAt;


    public Schedule(ScheduleRequestDto requestDto) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
    }


}
