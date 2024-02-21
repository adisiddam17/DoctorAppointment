package com.app.pojos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Schedule extends BaseEntity{
	@Column(nullable = false) 
	private String scheduleTitle;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate scheduleDate;
	@Column(nullable = false) 
	private LocalTime scheduleTime;
	@Column(nullable = false) 
	private int nop;
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	@MapsId
	private Doctor doctor;
	
	public void addAppointment(Appointment a) {
		appointments.add(a);// dept --> emp
		a.setSchedule(this);// emp --> dept
	}
	public void removeAppointment(Appointment a) {
		appointments.remove(a);
		a.setSchedule(null);
	}
	
}
