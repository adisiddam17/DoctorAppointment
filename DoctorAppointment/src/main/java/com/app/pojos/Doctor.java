package com.app.pojos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Doctor extends BaseEntity {
	@Column(nullable = false) 
	private String doctorEmail;
	@Column(nullable = false) 
	private String doctorName;
	@Column(nullable = false) 
	private String doctorPassword;
	@Column(nullable = false) 
	private String doctorNic;
	@Column(nullable = false) 
	private String doctorTel;
	
	@ManyToMany // mandatory!
	@JoinTable(name="specialities_doctors",
	joinColumns = @JoinColumn(name="speciality_id"),
	inverseJoinColumns = @JoinColumn(name="doctor_id")
	)
	private Set<Speciality> specialities=new HashSet<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doctorName == null) ? 0 : doctorName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (doctorName == null) {
			if (other.doctorName != null)
				return false;
		} else if (!doctorName.equals(other.doctorName))
			return false;
		return true;
	}
	//add helper methods
	public void addSpeciality(Speciality s )
	{
		specialities.add(s);
		s.getDoctors().add(this);
	}
	public void removeSpeciality(Speciality s )
	{
		specialities.remove(s);
		s.getDoctors().remove(this);
	}
	

}
