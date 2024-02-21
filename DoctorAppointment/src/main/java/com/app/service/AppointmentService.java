package com.app.service;

import java.util.List;

import com.app.dto.AddAppointmentDTO;
import com.app.dto.ApiResponse;
import com.app.dto.AppointmentRespDTO;

public interface AppointmentService {
		List<AddAppointmentDTO> getAllAppointmentsFromDept(long deptId);

		ApiResponse deleteAppDetails(long empId);

		AppointmentRespDTO addNewAppointment(AddAppointmentDTO dto);

		AppointmentRespDTO updateAppointment(Long empId,AddAppointmentDTO dto);

		AppointmentRespDTO getAppDetails(long appId, Long paiId);
		
		List<AppointmentRespDTO> getAllAppointments(int pageNumber,int pageSize);
}
