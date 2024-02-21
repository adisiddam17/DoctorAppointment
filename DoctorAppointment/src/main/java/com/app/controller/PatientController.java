package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddPatientDTO;
import com.app.service.PatientService;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping
	public ResponseEntity<?> addNewPatient(@RequestBody @Valid AddPatientDTO dto) {
		System.out.println("in add pai " + dto);
		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(patientService.addNewPatient(dto));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPaisPaginated(
			@RequestParam(defaultValue = "0", required = false) int pageNumber,
		    @RequestParam(defaultValue = "3", required = false) int pageSize)
{
		System.out.println("in get all pai " +pageNumber+" "+pageSize);
		List<AddPatientDTO> list = patientService.
				getAllPatients(pageNumber,pageSize);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		// emps found
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/{paiId}")
	public ResponseEntity<?> updatePatient(@PathVariable Long paiId,  @RequestBody @Valid AddPatientDTO dto) {
		System.out.println("in update emp " +paiId+" "+ dto);
		return ResponseEntity.
				ok(patientService.updatePatient(paiId,dto));
	}
	
	@DeleteMapping("/{paiId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long paiId) {
		System.out.println("in update emp " +paiId);
		return ResponseEntity.
				ok(patientService.deletePaiDetails(paiId));
	}
}
