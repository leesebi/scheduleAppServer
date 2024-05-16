package com.example.scheduleappserver.entity;

import com.example.scheduleappserver.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
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
    private String createdAt;


    public Schedule(ScheduleRequestDto requestDto) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
        this.createdAt = simpleDateFormat.format(nowDate);
    }
}
