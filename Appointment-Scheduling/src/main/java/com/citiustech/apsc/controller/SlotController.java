package com.citiustech.apsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.apsc.model.Slot;
import com.citiustech.apsc.model.SlotDate;
import com.citiustech.apsc.service.SlotBookingService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SlotController {
	
	@Autowired
	private SlotBookingService slotBookingService;

	@GetMapping("/slot/date/physicianId")
	 public ResponseEntity<List<Slot>> getSlotsByDate(@RequestBody SlotDate date) {
		return new ResponseEntity<List<Slot>>(slotBookingService.getSlotsByDate(date), HttpStatus.OK);
		 
	 }
	
	@GetMapping("/slot/dateBetween")
	public ResponseEntity<List<Slot>> getSlotsBetween(@RequestBody SlotDate slotDate) {
		return new ResponseEntity<List<Slot>>(slotBookingService.getAvailableSlotsBetween(slotDate), HttpStatus.OK);
	}
}
