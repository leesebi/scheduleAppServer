package com.example.scheduleappserver.repository;

import com.example.scheduleappserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository <Schedule, Long>{
}
