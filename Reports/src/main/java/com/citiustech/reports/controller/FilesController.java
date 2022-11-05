package com.citiustech.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.reports.model.SummaryRequest;
import com.citiustech.reports.model.SummaryResponce;
import com.citiustech.reports.serviceimpl.FileService;


@RestController
public class FilesController {

	@Autowired
	public FileService fileService;
	
	@GetMapping("/request")
	public ResponseEntity<SummaryResponce> summayCount(@RequestBody SummaryRequest request){
		
		SummaryResponce summaryResponce = fileService.summaryCount(request.getRunId());
		return new ResponseEntity<SummaryResponce>(summaryResponce,HttpStatus.OK);

	}
}
