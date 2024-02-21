package com.app.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddAppointmentDTO;
import com.app.dto.AppointmentRespDTO;
import com.app.service.AppointmentService;
import com.app.service.PatientService;
import com.app.service.ScheduleService;

@RestController
@RequestMapping("/appointments")
@Validated
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PatientService patientService;

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping
	public ResponseEntity<?> addNewAppointment(@RequestBody @Valid AddAppointmentDTO dto) {
		System.out.println("in add app " + dto);
		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(appointmentService.addNewAppointment(dto));
	}
	
	
		@GetMapping("/patients/{appId}/{paiId}")
		public ResponseEntity<?> getEmpDetailsByDeptAndEmpId(@PathVariable Long appId,@PathVariable Long paiId) {
			System.out.println("in get emp details by dept id n emp id " +appId+" "+ paiId);
			return ResponseEntity.ok(appointmentService.getAppDetails(appId,paiId));
		}
		

		@PutMapping("/{appId}")
		public ResponseEntity<?> updateEmployee(@PathVariable Long appId,  @RequestBody @Valid AddAppointmentDTO dto) {
			System.out.println("in update app " +appId+" "+ dto);
			return ResponseEntity.
					ok(appointmentService.updateAppointment(appId,dto));
		}

		@DeleteMapping("/{appId}")
		public ResponseEntity<?> deleteEmployee(@PathVariable Long appId) {
			System.out.println("in update app " +appId);
			return ResponseEntity.
					ok(appointmentService.deleteAppDetails(appId));
		}

		@GetMapping
			public ResponseEntity<?> getAllAppsPaginated(
					@RequestParam(defaultValue = "0", required = false) int pageNumber,
				    @RequestParam(defaultValue = "3", required = false) int pageSize)
		{
				System.out.println("in get all apps " +pageNumber+" "+pageSize);
				List<AppointmentRespDTO> list = appointmentService.
						getAllAppointments(pageNumber,pageSize);
				if (list.isEmpty())
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				return ResponseEntity.ok(list);
		}
		

}
