package com.app.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.app.pojos.Speciality;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddDoctorDTO {
	@NotBlank
	private String doctorEmail;
	@NotBlank
	private String doctorName;
	@NotBlank
	private String doctorPassword;
	@NotBlank
	private String doctorNic;	
	@NotBlank
	private String doctorTel;
}
