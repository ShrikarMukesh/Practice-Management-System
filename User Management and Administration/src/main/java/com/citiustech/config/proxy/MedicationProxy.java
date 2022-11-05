package com.citiustech.config.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.citiustech.dto.MedicationDto;

@FeignClient(name="Medication",url = "http://localhost:7100/medication")
@Service
public interface MedicationProxy {	
	
    @GetMapping("/all")
    public List<MedicationDto> allMedications();
}
