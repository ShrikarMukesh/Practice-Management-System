package com.citiustech.patientvisit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.patientvisit.model.Diagnosis;
import com.citiustech.patientvisit.model.Medication;
import com.citiustech.patientvisit.model.Procedure;
import com.citiustech.patientvisit.repository.DiagnosisRepository;
import com.citiustech.patientvisit.repository.MedicationRepository;
import com.citiustech.patientvisit.repository.ProcedureRepository;

@RestController
@RequestMapping("/api")
public class AttendPatientController {

	@Autowired
	private MedicationRepository medicationRepo;

	@Autowired
	private ProcedureRepository procedureRepo;

	@Autowired
	private DiagnosisRepository diagnosisRepo;

	@GetMapping("/medications")
	public ResponseEntity<List<Medication>> allMedications(){
		
		List<Medication> medications = medicationRepo.findAll();
		return new ResponseEntity<>(medications,HttpStatus.OK);
	}

	@GetMapping("/medications/{applNo}")
	public ResponseEntity<Medication> getMedication(@PathVariable("applNo") String applNo){
		
		Medication medication = medicationRepo.findById(applNo).get();
		return new ResponseEntity<>(medication,HttpStatus.OK);
	}

	@GetMapping("/procedures")
	public ResponseEntity<List<Procedure>> allProcedures(){
		
		List<Procedure> procedures = procedureRepo.findAll();
		return new ResponseEntity<>(procedures,HttpStatus.OK);
	}

	@GetMapping("/procedures/{procedureCode}")
	public ResponseEntity<Procedure> getProcedure(@PathVariable("procedureCode") String procedureCode){
		
		Procedure procedure = procedureRepo.findById(procedureCode).get();
		return new ResponseEntity<>(procedure,HttpStatus.OK);
	}

	@GetMapping("/diagnosis")
	public ResponseEntity<List<Diagnosis>> allDiagnosis(){
		List<Diagnosis> diagnosis = diagnosisRepo.findAll();
		return new ResponseEntity<>(diagnosis,HttpStatus.OK);
	}

	@GetMapping("/diagnosis/{diagnosisCode}")
	public ResponseEntity<Diagnosis> getDiagnosis(@PathVariable("diagnosisCode") String diagnosisCode){
		
		Diagnosis diagnosis = diagnosisRepo.findById(diagnosisCode).get();
		return new ResponseEntity<>(diagnosis,HttpStatus.OK);
	}


}