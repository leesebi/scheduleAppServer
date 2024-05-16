package com.example.scheduleappserver.service;

import com.example.scheduleappserver.entity.Schedule;
import com.example.scheduleappserver.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void save(Schedule schedule){
        scheduleRepository.save(schedule);
    }

    @Transactional
    public void findAll() {
        scheduleRepository.findAll();
    }

    @Transactional
    public void findById(Long id) {
        scheduleRepository.findById(id);
    }


}

