package com.app.service;

import java.util.List;

import com.app.dto.AddScheduleDTO;
import com.app.dto.ApiResponse;
import com.app.dto.ScheduleRespDTO;

public interface ScheduleService {
	ApiResponse deleteScheDetails(long ScheId);

	ScheduleRespDTO addNewSchedule(AddScheduleDTO dto);

	ScheduleRespDTO updateSchedule(Long ScheId,AddScheduleDTO dto);

	ScheduleRespDTO getScheDetails(Long docId);
	
	//get all Schedules : pagination
	List<ScheduleRespDTO> getAllSchedules(int pageNumber,int pageSize);
}
