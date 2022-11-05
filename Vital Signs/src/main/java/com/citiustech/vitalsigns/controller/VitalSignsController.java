package com.citiustech.vitalsigns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.vitalsigns.model.VitalSigns;
import com.citiustech.vitalsigns.service.VitalSignsService;

@RestController
public class VitalSignsController {

	@Autowired
	private VitalSignsService  vitalSignsService;

	@GetMapping("/all")
	public ResponseEntity<List<VitalSigns>> allProcedures(){

		List<VitalSigns> list = vitalSignsService.allVitalSigns();

		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}

