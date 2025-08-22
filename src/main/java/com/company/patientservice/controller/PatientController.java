package com.company.patientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.patientservice.dto.PatientRequestDTO;
import com.company.patientservice.dto.PatientResponseDTO;
import com.company.patientservice.service.PatientServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients/api/")
public class PatientController {

	@Autowired
	private PatientServiceImpl patientService;

	@PostMapping("createPatient")
	@Operation(summary = "Create a Patient")
	public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientDTO) {
		return new ResponseEntity<PatientResponseDTO>(patientService.createPatient(patientDTO), HttpStatus.OK);
	}

	@GetMapping("getPatients")
	@Operation(summary = "Get Patients")
	public ResponseEntity<List<PatientResponseDTO>> getPatients() {
		return new ResponseEntity<List<PatientResponseDTO>>(patientService.getPatients(), HttpStatus.OK);
	}

	@GetMapping("findById/{id}")
	@Operation(summary = "Get Patient By Id")
	public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable int id) {
		return new ResponseEntity<PatientResponseDTO>(patientService.getPatientById(id), HttpStatus.OK);
	}

	@GetMapping("findByEmail/{email}")
	@Operation(summary = "Get Patient By Email")
	public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable String email) {
		return new ResponseEntity<PatientResponseDTO>(patientService.getPatientByEmail(email), HttpStatus.OK);
	}

	@PutMapping("updatePatient/{id}")
	@Operation(summary = "Update Patient")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable int id,
			@Valid @RequestBody PatientRequestDTO patientRequestDto) {
		return ResponseEntity.ok().body(patientService.updatePatient(id, patientRequestDto));
	}

	@DeleteMapping("/deletePatient/{id}")
	@Operation(summary = "Delete Patient by id")
	public ResponseEntity<String> deletePatientById(@PathVariable int id) {
		return ResponseEntity.ok().body(patientService.deletePatient(id));
	}

}
