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

import com.app.dto.AddScheduleDTO;
import com.app.dto.ScheduleRespDTO;
import com.app.service.ScheduleService;

@RestController
@RequestMapping("/schedules")
@Validated
public class ScheduleController {
	@Autowired
	private ScheduleService ScheduleService;
	
	@PostMapping
	public ResponseEntity<?> addNewSchedule(@RequestBody @Valid AddScheduleDTO dto) {
		System.out.println("in add Sche " + dto);
		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(ScheduleService.addNewSchedule(dto));
	}
	
	@GetMapping("/{docId}")
	public ResponseEntity<?> getSchedByDoc(@PathVariable Long docId)
	{
		return ResponseEntity.ok(ScheduleService.getScheDetails(docId));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllSchesPaginated(
			@RequestParam(defaultValue = "0", required = false) int pageNumber,
		    @RequestParam(defaultValue = "3", required = false) int pageSize)
{
		System.out.println("in get all Sche " +pageNumber+" "+pageSize);
		List<ScheduleRespDTO> list = ScheduleService.
				getAllSchedules(pageNumber,pageSize);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		// emps found
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/{ScheId}")
	public ResponseEntity<?> updateSchedule(@PathVariable Long ScheId,  @RequestBody @Valid AddScheduleDTO dto) {
		System.out.println("in update emp " +ScheId+" "+ dto);
		return ResponseEntity.
				ok(ScheduleService.updateSchedule(ScheId,dto));
	}
	
	@DeleteMapping("/{ScheId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long ScheId) {
		System.out.println("in update emp " +ScheId);
		return ResponseEntity.
				ok(ScheduleService.deleteScheDetails(ScheId));
	}
}
