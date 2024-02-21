package com.app.pojos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "specialities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Speciality extends BaseEntity{
	@Column(nullable = false) 
	private String specialityName;
	
	@ManyToMany(mappedBy = "specialities")
	private Set<Doctor> doctors = new HashSet<>();
	
	
	public void addDoctor(Doctor d)
	{
		doctors.add(d);
		d.getSpecialities().add(this);
	}
	
	public void removeDoctor(Doctor d)
	{
		doctors.remove(d);
		d.getSpecialities().remove(this);
	}
}
