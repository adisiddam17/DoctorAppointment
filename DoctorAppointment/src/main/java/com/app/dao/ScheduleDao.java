package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Schedule;

public interface ScheduleDao extends JpaRepository<Schedule, Long> {

}
