package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddPatientDTO {
	@Email
	private String patientEmail;
	@NotBlank
	private String patientName;
	@NotBlank
	private String patientPassword;
	@NotBlank
	private String patientAddress;
	@NotBlank
	private String patientNic;
	private LocalDate patientDob;
	@NotBlank
	private String patientTel;
}
