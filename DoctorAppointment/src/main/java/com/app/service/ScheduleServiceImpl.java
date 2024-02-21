package com.app.service;

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
import com.app.dao.DoctorDao;
import com.app.dao.ScheduleDao;
import com.app.dto.AddScheduleDTO;
import com.app.dto.ApiResponse;
import com.app.dto.ScheduleRespDTO;
import com.app.pojos.Doctor;
import com.app.pojos.Schedule;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse deleteScheDetails(long scheId) {
		Optional<Schedule> optionalSche = scheduleDao.findById(scheId);
		if (optionalSche.isPresent())
		{
			scheduleDao.delete(optionalSche.get());
			return new ApiResponse("schedule Details of pai with ID "+optionalSche.get().getId() +" deleted....");
		}
		return new ApiResponse("schedule Details cannot be deleted....");
	}

	@Override
	public ScheduleRespDTO addNewSchedule(AddScheduleDTO dto) {
		Doctor doc = doctorDao.findById(dto.getDocId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid doc ID , doc not found !!!!"));
		Schedule ScheEntity = mapper.map(dto, Schedule.class);
		ScheEntity.setDoctor(doc);
		Schedule persistentSche = scheduleDao.save(ScheEntity);
		return mapper.map(persistentSche, ScheduleRespDTO.class);
	}

	@Override
	public ScheduleRespDTO updateSchedule(Long scheId, AddScheduleDTO dto) {
		Doctor doc = doctorDao.findById(dto.getDocId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid doc ID , doc not found !!!!"));
		Schedule sche = scheduleDao.findById(scheId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid sche ID , sche not found !!!!"));
		sche.setDoctor(doc);
		Schedule persistentSche = scheduleDao.save(sche);
		mapper.map(dto, sche);
			return mapper.map(sche, ScheduleRespDTO.class);
	}

	@Override
	public ScheduleRespDTO getScheDetails(Long docId) {
		Schedule sche = scheduleDao.findById(docId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid sche ID , sche not found !!!!"));
		return mapper.map(sche, ScheduleRespDTO.class);
	}

	@Override
	public List<ScheduleRespDTO> getAllSchedules(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Schedule> scheList = scheduleDao.findAll(pageable).getContent();
		return scheList.stream().
				map(emp -> mapper.map(emp, ScheduleRespDTO.class))
				.collect(Collectors.toList());
	}
	
}
