package com.citiustech.apsc.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.apsc.clients.UserClient;
import com.citiustech.apsc.model.Appointment;
import com.citiustech.apsc.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
@CrossOrigin("*")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserClient userClient;

	@PostMapping("/{userId}")
	public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment, @PathVariable long userId) throws MessagingException {

		Appointment createdappointment = appointmentService.bookAppointment(userId, appointment);
		return new ResponseEntity<>(createdappointment, HttpStatus.CREATED);
	}

	@GetMapping("/physician/{physicianId}")
	public ResponseEntity<List<Appointment>> getAppointmentByPhysicianId(@PathVariable long physicianId) {
		return new ResponseEntity<List<Appointment>>(appointmentService.getAppointmentsByPhysicianId(physicianId), HttpStatus.OK);
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<Appointment> getAppointmentByPatientId(@PathVariable long patientId) {
		return new ResponseEntity<Appointment>(appointmentService.getAppointmentByPatientId(patientId), HttpStatus.OK);
	}

	@GetMapping("/{appointmentId}")
	public ResponseEntity<Appointment> getAppointmentByAppointmentId(@PathVariable long appointmentId) {
		return new ResponseEntity<Appointment>(appointmentService.getAppointmentById(appointmentId), HttpStatus.OK);
	}


	@PutMapping("/update")
	public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
		return  new ResponseEntity<Appointment>(appointmentService.updateAppointmentStatus(appointment),HttpStatus.OK);
	}


	@GetMapping("/find")
	public Object getUser() {
		return this.userClient.getUser("shrikar");
	}

	@GetMapping("/hi")
	public ResponseEntity<String> sayHello(@RequestHeader String token) {
		

		String string = this.userClient.sayHello();
		return new ResponseEntity<String>(string,HttpStatus.OK);
	}
	
	
}
