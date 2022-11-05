package com.citiustech.apsc.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.apsc.mapper.EmailDtoMapper;
import com.citiustech.apsc.model.Appointment;
import com.citiustech.apsc.repository.AppointmentRepository;
import com.citiustech.apsc.service.AppointmentService;
import com.citiustech.apsc.service.EmailService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public Appointment bookAppointment(Long userId, Appointment appointment) throws MessagingException {
		
		appointment.setStatus("Active");
		appointment.getSlot().setStatus("pending");
		appointment.getSlot().setDayWeek(getDayStringNew(appointment.getSlot().getSlotDate(), Locale.ENGLISH));
		emailService.sendMail(EmailDtoMapper.appintmentToEmailDtomapper2(appointment));	
		return appointmentRepository.save(appointment);
		
	}
	public static String getDayStringNew(LocalDate date, Locale locale) {
	    DayOfWeek day = date.getDayOfWeek();
	    return day.getDisplayName(TextStyle.FULL, locale);
	}
	
	@Override
	public List<Appointment> getAppointmentsByPhysicianId(Long physicianId) {
		
		return appointmentRepository.getAppointmentsByPhysicianId(physicianId);
	}


	@Override
	public Appointment getAppointmentByPatientId(Long patientId) {
		return appointmentRepository.getAppointmentsByPatientId(patientId);
	}

	@Override
	public Appointment getAppointmentById(Long appointmentId) {
		Appointment appointment = appointmentRepository.getAppointmentByAppointmentId(appointmentId);
		return appointment;
	}

	@Override
	public Appointment updateAppointmentStatus(Appointment appointment) {

		Appointment updateAppointment = appointmentRepository.getOne(appointment.getAppointmentId());
		updateAppointment.setStatus(appointment.getStatus());

		return appointmentRepository.save(appointment);
	}

}
