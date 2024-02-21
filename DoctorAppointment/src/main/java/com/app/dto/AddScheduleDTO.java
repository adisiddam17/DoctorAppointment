package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddScheduleDTO {
	@NotBlank
	private long docId;
	@NotBlank
	private String scheduleTitle;
	private LocalDate scheduleDate;
	private LocalTime scheduleTime;
	@NotBlank
	private int nop;
}
