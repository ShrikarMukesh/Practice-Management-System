package com.citiustech.vitalsigns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.vitalsigns.model.VitalSigns;
import com.citiustech.vitalsigns.repository.VitalSignsRepository;

@Service
public class VitalSignsService {

	@Autowired
	private VitalSignsRepository vitalSignsRepo;
	
	public List<VitalSigns> allVitalSigns(){
		return vitalSignsRepo.findAll();
	}
}
