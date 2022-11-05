package com.citiustech.config.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.citiustech.dto.ProcedureDto;



@FeignClient(name="PROCEDURE",url="http://localhost:7300")
public interface ProcedureProxy {
   @GetMapping("/all")
   public List<ProcedureDto> allProcedures();
}
