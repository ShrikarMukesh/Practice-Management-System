package com.citiustech.patientvisit.serviceImpl;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.citiustech.patientvisit.model.VisitDetails;
import com.citiustech.patientvisit.repository.VisitDetailsRepository;
import com.citiustech.patientvisit.service.VisitService;

import lombok.extern.slf4j.Slf4j;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
@Service
@Slf4j
public class VisitSeriveImpl implements VisitService{

	@Autowired
	private VisitDetailsRepository visitRepository;

	@Override
	public VisitDetails captureVisitDetails(VisitDetails visitDetails) {
		return this.visitRepository.save(visitDetails);
	}

	@Override
	public List<VisitDetails> patientVisitDetails(Long patientId) {
		log.info("VisitSeriveImpl ---PatientVisitDetails");

		List<VisitDetails> details = this.visitRepository.findAll();
		details = details.stream().filter(detail->detail.getPatientId()==patientId).collect(Collectors.toList());
		return details;
	}

	@Override
	public VisitDetails attendPatient(VisitDetails visitDetails, long patientId) {
		
		log.info("VisitSeriveImpl ---AttendPatient");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		
		visitDetails.setVisitedAt(dtf.format(now));
		visitDetails.setPatientId(patientId);
		VisitDetails attendDetails =  visitRepository.save(visitDetails);
		
		if(attendDetails != null){
			return  attendDetails;
		}else{
			throw new RuntimeException("Issue while attending patient");
		}
	}

	@Override
	public VisitDetails lastVisitDetail(long patientId) {
		
		List<VisitDetails> details = this.visitRepository.findAll();
		details = details.stream().filter(detail->detail.getPatientId()==patientId).collect(Collectors.toList());
		VisitDetails lastvisitDetails = Collections.max(details,Comparator.comparing(s -> s.getVisitedAt()));
		return lastvisitDetails;
	
	}
}
