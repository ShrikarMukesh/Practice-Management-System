package com.citiustech.config.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.citiustech.dto.DiagnosisDto;

@FeignClient(name="Diagnosis",url = "http://localhost:7200")
@Service
public interface DiagnosisProxy {
	
	@GetMapping("/all")
    public List<DiagnosisDto> allDiagnosis();
}
