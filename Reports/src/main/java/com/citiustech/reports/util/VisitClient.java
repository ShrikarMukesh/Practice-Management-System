package com.citiustech.reports.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.citiustech.reports.dto.VisitDetails;

@FeignClient(name="PATIENTVISIT",url = "http://localhost:8500/visit")
@Service
public interface VisitClient {

	@GetMapping("/lastvisit/{patientId}")
	public VisitDetails lastVisit(@PathVariable("patientId")long patientId);
}
