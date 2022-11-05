package com.citiustech.allergies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.allergies.model.Allergy;
import com.citiustech.allergies.model.repository.AllergyRepository;

@Service
public class AllergyService {

	private AllergyRepository allergyRepository;
	
	@Autowired
	public AllergyService(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
	}
	
	public List<Allergy> findAllAllergy( ){
		return this.allergyRepository.findAll();
	}
	
	public Allergy createAllergy(Allergy allergy){
		return allergyRepository.save(allergy);
	}

	public Allergy getAllergyById(String id) {
		
		return this.allergyRepository.findById(id).get();
	}
}
