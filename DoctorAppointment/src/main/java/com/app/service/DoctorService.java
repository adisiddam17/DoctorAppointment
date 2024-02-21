package com.app.service;

import java.util.List;

import com.app.dto.AddDoctorDTO;
import com.app.dto.ApiResponse;

public interface DoctorService {
	List<AddDoctorDTO> getAllDoctors();
	AddDoctorDTO getDoctorDetails(Long docId);
	AddDoctorDTO addNewDoctor(AddDoctorDTO doc);
	AddDoctorDTO updateDoctor(Long empId,AddDoctorDTO dto);
	ApiResponse deleteDocDetails(long empId);


}
