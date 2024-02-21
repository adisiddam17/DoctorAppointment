package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddDoctorDTO;
import com.app.service.DoctorService;


@RestController
@RequestMapping("/doctors")
@Validated
public class DoctorController {
	
	@Autowired 
	private DoctorService doctorService;
	@PostMapping
	public ResponseEntity<?> addNewDoctor(@RequestBody @Valid AddDoctorDTO doc) {
		System.out.println("in add dept " + doc);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(doctorService.addNewDoctor(doc));

	}
	
	@GetMapping("/{docId}")
	public ResponseEntity<?> getDepartmentDetails(@PathVariable Long docId) {
		System.out.println("in get doc " + docId);
		return ResponseEntity
				.ok(doctorService.getDoctorDetails(docId));

	}
	
	@PutMapping("/{docId}")
	public ResponseEntity<?> updateDoctor(@PathVariable Long docId,  @RequestBody @Valid AddDoctorDTO dto) {
		System.out.println("in update doc " +docId+" "+ dto);
		return ResponseEntity.
				ok(doctorService.updateDoctor(docId,dto));
	}
	
	@DeleteMapping("/{docId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long docId) {
		System.out.println("in delete doc " +docId);
		return ResponseEntity.
				ok(doctorService.deleteDocDetails(docId));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllDoctorDetails() {
		System.out.println("in get all docs" );
		return ResponseEntity
				.ok(doctorService.getAllDoctors());
	}
}
