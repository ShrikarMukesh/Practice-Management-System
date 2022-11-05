package com.citiustech.patientvisit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citiustech.patientvisit.model.VisitDetails;

import com.citiustech.patientvisit.serviceImpl.VisitSeriveImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/visit")
@Slf4j
@CrossOrigin("*")
public class VisitDetailsController {

	@Autowired
	private VisitSeriveImpl seriveImpl;

	public VisitDetailsController(VisitSeriveImpl seriveImpl) {
		this.seriveImpl = seriveImpl;
	}


	@PostMapping("/capture")
	public ResponseEntity<VisitDetails> captureVisitDetails(@RequestBody VisitDetails visitDetails) {

		VisitDetails capturedDetails = this.seriveImpl.captureVisitDetails(visitDetails);
		return new ResponseEntity<>(capturedDetails, HttpStatus.CREATED);
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<List<VisitDetails>> patientVisitDetails(@PathVariable("patientId") long patientId){
		log.info("allVisits");
		List<VisitDetails> visitDetails = seriveImpl.patientVisitDetails(patientId);
		return new ResponseEntity<>(visitDetails, HttpStatus.OK);
	}
	
	@PostMapping("/attend/{patientId}")
	public ResponseEntity<VisitDetails> attendPatient(@PathVariable("patientId") long patientId,@RequestBody VisitDetails visitDetails){
		log.info("");
		VisitDetails visitreuslt = seriveImpl.attendPatient(visitDetails,patientId);
		return new ResponseEntity<>(visitreuslt, HttpStatus.CREATED);
	}
	
	@GetMapping("/lastvisit/{patientId}")
	public ResponseEntity<VisitDetails> lastVisit(@PathVariable("patientId") long patientId){
	    log.info("lastVisit---------" );
		VisitDetails lastVisitDetails = seriveImpl.lastVisitDetail(patientId);
		System.out.println("------"+lastVisitDetails);
		return new ResponseEntity<>(lastVisitDetails, HttpStatus.CREATED);
	}
	
}
