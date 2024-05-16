package com.example.scheduleappserver.controller;


import com.example.scheduleappserver.dto.ScheduleRequestDto;
import com.example.scheduleappserver.dto.ScheduleResponseDto;
import com.example.scheduleappserver.entity.Schedule;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleMap = new HashMap<>();

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        // requestDto받은 것으로 schedule을 새로 만듬
        Schedule schedule = new Schedule(requestDto);

        Long maxId = scheduleMap.size() > 0 ? Collections.max(scheduleMap.keySet())+1:1;
        schedule.setId(maxId);

        // scheduleMap에 id와 schedule entity 넣음
        scheduleMap.put(schedule.getId(), schedule);

        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);

        return responseDto;
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> readSchedule(){
        List<ScheduleResponseDto> list = new ArrayList<>();
        for(Schedule schedule : scheduleMap.values()){
            list.add(new ScheduleResponseDto(schedule));
        }
        return list;
    }

    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto readIdSchedule(@PathVariable Long id,@RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = null;
        if(scheduleMap.containsKey(id)){
             schedule = scheduleMap.get(id);
        }else{
            throw new IllegalStateException("다시한번 확인해주세요");
        }


        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }




}
