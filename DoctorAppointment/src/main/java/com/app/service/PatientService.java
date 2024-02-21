package com.app.service;

import java.util.List;

import com.app.dto.AddPatientDTO;
import com.app.dto.ApiResponse;

public interface PatientService {
		ApiResponse deletePaiDetails(long paiId);

		AddPatientDTO addNewPatient(AddPatientDTO dto);

		AddPatientDTO updatePatient(Long paiId,AddPatientDTO dto);
		
		//get all patients : pagination
		List<AddPatientDTO> getAllPatients(int pageNumber,int pageSize);
}
