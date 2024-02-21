package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Long>{

}
