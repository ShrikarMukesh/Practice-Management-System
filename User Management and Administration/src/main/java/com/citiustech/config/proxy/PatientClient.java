package com.citiustech.config.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.citiustech.dto.Patient;


@FeignClient(name="PATIENTVISIT",url = "http://localhost:7000/visit")
@Service
public interface PatientClient {

	@PostMapping("/register")
	public void register(Patient patient);
}
