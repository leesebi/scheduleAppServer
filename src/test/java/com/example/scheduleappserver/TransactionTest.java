package com.example.scheduleappserver;

import com.example.scheduleappserver.entity.Schedule;
import com.example.scheduleappserver.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {
    @Autowired
    ScheduleRepository repository;


    @Test
    @Rollback(value = false)
    @Transactional
    public void testCreate(){
        Schedule schedule = new Schedule();
        schedule.setPassword(1111);
        schedule.setTitle("title");
        schedule.setContent("content");
        schedule.setManager("Manager");
        repository.save(schedule);
    }


}
