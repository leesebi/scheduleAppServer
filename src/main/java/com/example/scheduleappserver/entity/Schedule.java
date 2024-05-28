package com.example.scheduleappserver.entity;

import com.example.scheduleappserver.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer password;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String manager;
    @Column
    private LocalDateTime createdAt;


    public Schedule(ScheduleRequestDto requestDto) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
        this.createdAt = LocalDateTime.now();
    }
}
