package com.citiustech.patientvisit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citiustech.patientvisit.model.VisitDetails;

@Service
public interface VisitService {


	public VisitDetails captureVisitDetails(VisitDetails visitDetails);

	public List<VisitDetails> patientVisitDetails(Long patientId);
	
	public VisitDetails attendPatient(VisitDetails visitDetails ,long patientId);
	
	public VisitDetails lastVisitDetail(long patientId);
	 
}