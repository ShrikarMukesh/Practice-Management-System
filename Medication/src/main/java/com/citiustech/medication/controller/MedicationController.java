package com.citiustech.medication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.medication.model.Medication;
import com.citiustech.medication.service.MedicationService;

@RestController
@RequestMapping("/medication")
@CrossOrigin("*")
public class MedicationController {
	
    @Autowired
    private MedicationService  medicationService;

	public MedicationController(MedicationService medicationService) {
		this.medicationService = medicationService;
	}
    
	@GetMapping("/all")
	public ResponseEntity<List<Medication>> allMedications(){
		
		List<Medication> list = medicationService.allMedications();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Medication>> getMedicationById(@PathVariable String id){
		 Optional<Medication> med = medicationService.getMedicationById(id);
		 return new ResponseEntity<>(med,HttpStatus.OK);
	}
}
