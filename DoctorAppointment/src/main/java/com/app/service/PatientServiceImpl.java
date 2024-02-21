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
import com.app.dao.AppointmentDao;
import com.app.dao.PatientDao;
import com.app.dto.AddPatientDTO;
import com.app.dto.ApiResponse;
import com.app.pojos.Patient;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {


	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse deletePaiDetails(long paiId) {
		Optional<Patient> optionalPai = patientDao.findById(paiId);
		if (optionalPai.isPresent())
		{
			patientDao.delete(optionalPai.get());
			return new ApiResponse("Patient Details of pai with ID "+optionalPai.get().getId() +" deleted....");
		}
		return new ApiResponse("Patient Details cannot be deleted....");

	}

	@Override
	public AddPatientDTO addNewPatient(AddPatientDTO dto) {
		Patient paiEntity = mapper.map(dto, Patient.class);
		Patient persistentPai = patientDao.save(paiEntity);
		return mapper.map(persistentPai, AddPatientDTO.class);
	}

	@Override
	public AddPatientDTO updatePatient(Long paiId, AddPatientDTO dto) {
		Patient pai = patientDao.findById(paiId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Pai ID , Pai not found !!!!"));
		mapper.map(dto, pai);
			return mapper.map(pai, AddPatientDTO.class);
	}

	@Override
	public List<AddPatientDTO> getAllPatients(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Patient> empList = patientDao.findAll(pageable).getContent();
		return empList.stream().
				map(emp -> mapper.map(emp, AddPatientDTO.class))
				.collect(Collectors.toList());
	}
	
}
