package com.citiustech.reports.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.reports.dto.VisitDetails;
import com.citiustech.reports.model.Customer;
import com.citiustech.reports.repository.CustomerRepository;
import com.citiustech.reports.util.PDFGenerator;
import com.citiustech.reports.util.VisitClient;
import com.citiustech.reports.util.VisitPdfGenerator;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/api/pdf")
public class ReportsController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	VisitClient visitClient;

	@GetMapping(value = "/customers",produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customersReport() throws IOException {

		List<Customer> customers = (List<Customer>) customerRepository.findAll();

		ByteArrayInputStream bis = PDFGenerator.customerPDFReport(customers);

		System.out.println(bis);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=customers.pdf");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	@GetMapping("/visit/{patientId}")
	public ResponseEntity<VisitDetails> lastVisit(@PathVariable("patientId") long patientId){
		VisitDetails details = visitClient.lastVisit(patientId);
		System.out.println("--->"+ details);
		return new ResponseEntity<>(details,HttpStatus.OK);
	}
	
	@GetMapping(value = "/report/{patientId}",produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> lastVisitReport(@PathVariable("patientId") long patientId) throws IOException, DocumentException {
		
		VisitDetails details = visitClient.lastVisit(patientId);
		ByteArrayInputStream bis = VisitPdfGenerator.patietvisitPDFReport(details);

		System.out.println(bis);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=lastVist.pdf");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}