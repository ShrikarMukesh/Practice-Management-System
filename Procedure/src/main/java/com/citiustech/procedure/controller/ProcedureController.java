package com.citiustech.procedure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.procedure.model.Procedure;
import com.citiustech.procedure.service.ProcedureService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {

	@Autowired
	private ProcedureService  procedureService;

	@GetMapping("/all")
	public ResponseEntity<List<Procedure>> allProcedures(){

		List<Procedure> list = procedureService.allProcedures();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("{/id}")
	public ResponseEntity<Procedure> findProcedureById(@PathVariable String id){

		Procedure procedure = procedureService.getProcedureById(id);
		return new ResponseEntity<>(procedure,HttpStatus.OK);
	}
	@GetMapping("/add")
	public ResponseEntity<Procedure> addProcedure(@RequestBody Procedure procedure){

		Procedure addedProcedure = procedureService.addProcedure(procedure);
		return new ResponseEntity<>(addedProcedure,HttpStatus.OK);
	}
}

