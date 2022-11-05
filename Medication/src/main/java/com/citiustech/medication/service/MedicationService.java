package com.citiustech.medication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.medication.model.Medication;
import com.citiustech.medication.repository.MedicationRepository;
import java.util.*;
@Service
public class MedicationService {

	@Autowired
	private MedicationRepository medicationRepository;

	public MedicationService(MedicationRepository medicationRepository) {
		this.medicationRepository = medicationRepository;
	}
	
	public List<Medication> allMedications(){
		return medicationRepository.findAll();
	}
	
	public Optional<Medication> getMedicationById(String id) {
		return medicationRepository.findById(id);
	}
	
	public List<Medication> listofMedicationsByIds(List<String> ids){
		
		   return medicationRepository.findAllById(ids);
	}
}
