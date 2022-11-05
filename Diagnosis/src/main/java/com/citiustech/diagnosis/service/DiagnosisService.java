package com.citiustech.diagnosis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.diagnosis.exception.DiagnosisNotFoundException;
import com.citiustech.diagnosis.model.Diagnosis;
import com.citiustech.diagnosis.repository.DiagnosisRepository;

@Service
public class DiagnosisService {

	@Autowired
	private DiagnosisRepository diagnosisRepo;

	public List<Diagnosis> allDiagnosis(){
		return diagnosisRepo.findAll();

	}

	public Diagnosis getDiagnosisById(String id) {
		
		return diagnosisRepo.findById(id)
				                         .orElseThrow(()->new DiagnosisNotFoundException("Diagnosis for the given id does not exist."));
		
	}
}
