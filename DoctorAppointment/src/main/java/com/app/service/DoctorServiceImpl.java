package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.DoctorDao;
import com.app.dao.ScheduleDao;
import com.app.dto.AddDoctorDTO;
import com.app.dto.ApiResponse;
import com.app.pojos.Doctor;
import com.app.pojos.Schedule;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<AddDoctorDTO> getAllDoctors() {
		return doctorDao.findAll()
				.stream()
				.map(doc -> mapper.map(doc, AddDoctorDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public AddDoctorDTO getDoctorDetails(Long docId) {
		return mapper.map(
				doctorDao.findById(docId).
				orElseThrow(() -> new ResourceNotFoundException("Invalid Doc Id !!!!"))
				,AddDoctorDTO.class);
	}

	@Override
	public AddDoctorDTO updateDoctor(Long docId, AddDoctorDTO dto) {
				Doctor doc = doctorDao.findById(docId)
						.orElseThrow(() -> new ResourceNotFoundException("Invalid Doc ID , Doc not found !!!!"));
				mapper.map(dto, doc);
					return mapper.map(doc, AddDoctorDTO.class);
	}

	@Override
	public ApiResponse deleteDocDetails(long docId) {
				Optional<Schedule> optionalSched = scheduleDao.findById(docId);
				if (optionalSched.isPresent())
					scheduleDao.delete(optionalSched.get());
				Doctor doc = doctorDao.findById(docId).
						orElseThrow(() -> new ResourceNotFoundException("Invalid doc id"));
				return new ApiResponse("Doctor Details of doc with ID "+doc.getId() +" deleted....");
	}
	
	@Override
	public AddDoctorDTO addNewDoctor(AddDoctorDTO doc) {
		Doctor docEntity = mapper.map(doc, Doctor.class);
		Doctor persistentDoc = doctorDao.save(docEntity);
		return mapper.map(persistentDoc, AddDoctorDTO.class);
	}
	
}
