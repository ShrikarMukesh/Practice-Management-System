package com.citiustech.apsc.service;

import java.util.List;

import javax.mail.MessagingException;

import com.citiustech.apsc.model.Appointment;

public interface AppointmentService {

	Appointment bookAppointment(Long userId, Appointment appointment) throws MessagingException;
	
	Appointment getAppointmentById(Long appointmentId);
	
	Appointment updateAppointmentStatus(Appointment appointment);
	
	List<Appointment> getAppointmentsByPhysicianId(Long physicianId);
	
	Appointment getAppointmentByPatientId(Long patientId);

	//New One
	//Appointment scheduleAppointment(Long userId, AppointmentInfo appointmentInfo);
}
