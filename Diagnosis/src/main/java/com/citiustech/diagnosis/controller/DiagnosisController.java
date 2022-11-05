package com.citiustech.diagnosis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.diagnosis.model.Diagnosis;
import com.citiustech.diagnosis.service.DiagnosisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diagnosis")
@Slf4j
public class DiagnosisController {

	@Autowired
	private DiagnosisService diagnosisService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Diagnosis>> getAllDiagnosis(){
		log.info("getAllDiagnosis");
		List<Diagnosis> allDiagnosis = diagnosisService.allDiagnosis();
		return new ResponseEntity<>(allDiagnosis,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable String id){
		
		Diagnosis diagnosis = diagnosisService.getDiagnosisById(id);
		return new ResponseEntity<>(diagnosis,HttpStatus.OK);
	}
}
