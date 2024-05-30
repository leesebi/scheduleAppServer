package com.example.scheduleappserver.service;

import com.example.scheduleappserver.dto.ScheduleRequestDto;
import com.example.scheduleappserver.dto.ScheduleResponseDto;
import com.example.scheduleappserver.entity.Schedule;
import com.example.scheduleappserver.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 할일 저장
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    // 할일 전체 조회
    public List<Schedule> findAll() {
        return scheduleRepository.findAll(Sort.by("createdAt").descending());
    }

    // 할일 단건 조회
    public ScheduleResponseDto findById(Long id) { //Optional을 쓰는 이유는 id값이 null일 수도 있기 때문이다.
        Schedule schedule = findSchedule(id);
        return new ScheduleResponseDto(schedule);
    }

    // 할일 수정
    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);

        if (schedule.getPassword() == null && !schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException();
        }

        schedule.setManager(requestDto.getManager());
        schedule.setContent(requestDto.getContent());
        schedule.setTitle(requestDto.getTitle());

        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    // 할 일 삭제
    public void deleteFindIdPassword(Long id, Integer password) {
        Schedule schedule = checkPW(id, password);

        scheduleRepository.delete(schedule);
    }

    // 비밀번호 조회
    public Schedule checkPW(Long id, Integer password) {
        Schedule schedule = findSchedule(id);
        if (schedule.getPassword() == null && !schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException();
        }
        return schedule;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 일정은 존재하지 않습니다.")
        );
    }

}

