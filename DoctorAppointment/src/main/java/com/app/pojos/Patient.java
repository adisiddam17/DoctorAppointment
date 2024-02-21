package com.app.pojos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Patient extends  BaseEntity{
	@Column(nullable = false) 
	private String patientEmail;
	@Column(nullable = false) 
	private String patientName;
	@Column(nullable = false) 
	private String patientPassword;
	@Column(nullable = false) 
	private String patientAddress;
	@Column(nullable = false) 
	private String patientNic;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate patientDob;
	private String patientTel;
	@OneToMany(mappedBy="patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;
	
	public void addAppointment(Appointment a) {
		appointments.add(a);// dept --> emp
		a.setPatient(this);// emp --> dept
	}
	public void removeAppointment(Appointment a) {
		appointments.remove(a);
		a.setPatient(null);
	}
}
