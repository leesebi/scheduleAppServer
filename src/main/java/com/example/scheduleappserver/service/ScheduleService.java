package com.example.scheduleappserver.service;

import com.example.scheduleappserver.dto.ScheduleRequestDto;
import com.example.scheduleappserver.dto.ScheduleResponseDto;
import com.example.scheduleappserver.entity.Schedule;
import com.example.scheduleappserver.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void save(Schedule schedule){
        scheduleRepository.save(schedule);
    }

    @Transactional
    public List<Schedule> findAll() {
        List <Schedule> scheduleList = scheduleRepository.findAllByOrderByCreatedAtDesc();
        return scheduleList;
    }

    @Transactional
    public ScheduleResponseDto findById(Long id) { //Optional을 쓰는 이유는 id값이 null일 수도 있기 때문이다.
        Schedule schedule = findSchedule(id);
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }


    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        schedule.setPassword(requestDto.getPassword());
        schedule.setManager(requestDto.getManager());
        schedule.setContent(requestDto.getContent());
        schedule.setTitle(requestDto.getTitle());
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    @Transactional
    public void deleteFindIdPassword(Long id, Integer password){
        scheduleRepository.deleteByIdAndPassword(id, password);
    }



    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 일정은 존재하지 않습니다.")
                );
    }

}

