package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleRespDTO {
	private String scheduleTitle;
	private LocalDate scheduleDate;
	private LocalTime scheduleTime;
	private int nop;
}
