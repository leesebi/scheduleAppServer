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

    @GetMapping("/scheduleList")
    public List<Schedule> readSchedule(){
        List<Schedule> scheduleList = service.findAll();
        return scheduleList;
    }

    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto readIdSchedule(@PathVariable Long id){
        return service.findById(id);
    }


    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        return service.update(id, requestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        service.deleteFindIdPassword(id, requestDto.getPassword());
    }

}
