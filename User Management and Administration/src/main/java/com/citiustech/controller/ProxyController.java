package com.citiustech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.config.proxy.DiagnosisProxy;
import com.citiustech.config.proxy.MedicationProxy;
import com.citiustech.config.proxy.ProcedureProxy;
import com.citiustech.dto.DiagnosisDto;
import com.citiustech.dto.MedicationDto;
import com.citiustech.dto.ProcedureDto;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProxyController {

	@Autowired 
	private MedicationProxy medicationProxy;

	@Autowired 
	private DiagnosisProxy diagnosisProxy;

	@Autowired 
	private ProcedureProxy proceduresProxy;


	@GetMapping("/medi") 
	public ResponseEntity<List<MedicationDto>> allMedication(){

		List<MedicationDto> list = medicationProxy.allMedications();
		System.out.println(list); 
		return new ResponseEntity<>(list,HttpStatus.OK); 
	}

	@GetMapping("/dai") public ResponseEntity<List<DiagnosisDto>> allDiagnosis(){

		List<DiagnosisDto> list = diagnosisProxy.allDiagnosis();
		System.out.println(list); return new ResponseEntity<>(list,HttpStatus.OK); }

	@GetMapping("/procedure") public ResponseEntity<List<ProcedureDto>>
	allProcedures(){

		List<ProcedureDto> list = proceduresProxy.allProcedures();
		System.out.println(list); return new ResponseEntity<>(list,HttpStatus.OK); 
	}

}

