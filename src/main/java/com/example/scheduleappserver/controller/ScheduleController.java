package com.example.scheduleappserver.controller;


import com.example.scheduleappserver.dto.ScheduleRequestDto;
import com.example.scheduleappserver.dto.ScheduleResponseDto;
import com.example.scheduleappserver.entity.Schedule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleMap = new HashMap<>();

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        // requestDto받은 것으로 schedule을 새로 만듬
        Schedule schedule = new Schedule(requestDto);

        // id 부분 처리를 어떻게 해야될지 모르겟다

        // scheduleMap에 id와 schedule entity 넣음
        scheduleMap.put(schedule.getId(), schedule);

        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);

        return responseDto;
    }

}
