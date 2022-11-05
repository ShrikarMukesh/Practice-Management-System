package com.citiustech.apsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.apsc.model.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	List<Appointment> getAppointmentsByPhysicianId(long physicianId);
	
	Appointment getAppointmentsByPatientId(long patientId);
	
	Appointment getAppointmentByAppointmentId(long appointmentId);
}
