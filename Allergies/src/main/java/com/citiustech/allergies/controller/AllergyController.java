package com.citiustech.allergies.controller;

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

import com.citiustech.allergies.exception.AllergyNotFoundException;
import com.citiustech.allergies.model.Allergy;
import com.citiustech.allergies.service.AllergyService;

@RestController
@RequestMapping("/allergy")
@CrossOrigin("*")
public class AllergyController {

	@Autowired
	private AllergyService allergyService;

	@GetMapping("/all")
	public ResponseEntity<List<Allergy>> getAllAllergy(){

		List<Allergy> allergies = allergyService.findAllAllergy();
		return new ResponseEntity<>(allergies,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Allergy> getAllergyById(@PathVariable  String id){

		Allergy allergy = this.allergyService.getAllergyById(id);
		if(allergy == null) {
			throw new AllergyNotFoundException("The Allergy with this Id is not Present");
		}
		return new ResponseEntity<>(allergy,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Allergy> addAllergy(@RequestBody Allergy allergy){

		Allergy createdAllergy = allergyService.createAllergy(allergy);
		return new ResponseEntity<>(createdAllergy,HttpStatus.CREATED);
	}

}
