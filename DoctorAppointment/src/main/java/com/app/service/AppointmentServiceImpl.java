package com.app.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AppointmentDao;
import com.app.dao.PatientDao;
import com.app.dao.ScheduleDao;
import com.app.dto.AddAppointmentDTO;
import com.app.dto.ApiResponse;
import com.app.dto.AppointmentRespDTO;

import com.app.pojos.Appointment;
import com.app.pojos.Patient;
import com.app.pojos.Schedule;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private PatientDao patientDao;

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<AddAppointmentDTO> getAllAppointmentsFromDept(long deptId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AppointmentRespDTO addNewAppointment(AddAppointmentDTO dto) {
		Schedule sche = scheduleDao.findById(dto.getScheduleId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Sche Id!!!"));
		Patient pai = patientDao.findById(dto.getPatientId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Pai Id!!!"));
		Appointment appEntity = mapper.map(dto, Appointment.class);
		sche.addAppointment(appEntity);
		pai.addAppointment(appEntity);
		Appointment savedApp = appointmentDao.save(appEntity);
		return mapper.map(savedApp, AppointmentRespDTO.class);
}

	@Override
	public AppointmentRespDTO updateAppointment(Long appId, AddAppointmentDTO dto) {
		Appointment app = appointmentDao.findById(appId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid App ID , App not found !!!!"));
			mapper.map(dto, app);		
			Schedule sche = scheduleDao.findById(dto.getScheduleId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid Sche Id!!!"));
			Patient pai = patientDao.findById(dto.getPatientId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid Pai Id!!!"));
			sche.addAppointment(app);
			pai.addAppointment(app);
			return mapper.map(app, AppointmentRespDTO.class);
	}

	@Override
	public AppointmentRespDTO getAppDetails(long appId, Long paiId) {
		Appointment app = appointmentDao.findById(appId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid App ID , App not found !!!!"));
		if(app.getPatient().getId() != paiId)
			throw new ResourceNotFoundException("Pai id does not match !!!");
			
		return mapper.map(app, AppointmentRespDTO.class);
	}

	@Override
	public List<AppointmentRespDTO> getAllAppointments(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Appointment> empList = appointmentDao.findAll(pageable).getContent();
		return empList.stream().
				map(emp -> mapper.map(emp, AppointmentRespDTO.class))
				.collect(Collectors.toList());	
	}
	
	@Override
	public ApiResponse deleteAppDetails(long Id) {
		Appointment app = appointmentDao.findById(Id).
				orElseThrow(() -> new ResourceNotFoundException("Invalid app id"));
		app.setSchedule(null);
		appointmentDao.delete(app);
		return new ApiResponse("App Details of app with ID "+app.getId() +" deleted....");
	}


}
