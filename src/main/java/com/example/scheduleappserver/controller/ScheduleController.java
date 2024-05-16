package com.example.scheduleappserver.controller;


import com.example.scheduleappserver.dto.ScheduleRequestDto;
import com.example.scheduleappserver.dto.ScheduleResponseDto;
import com.example.scheduleappserver.entity.Schedule;
import com.example.scheduleappserver.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping("/schedule")
    public void createSchedule(@RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto);
        service.save(schedule);
    }

}
