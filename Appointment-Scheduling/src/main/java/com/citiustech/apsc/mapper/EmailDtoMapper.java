package com.citiustech.apsc.mapper;

import org.springframework.stereotype.Service;

import com.citiustech.apsc.model.Appointment;
import com.citiustech.apsc.model.EmailDto;

@Service
public class EmailDtoMapper {

	public static EmailDto appintmentToEmailDtomapper(Appointment appointment) {

		EmailDto emailDto = new EmailDto();
		emailDto.setPatientName("PatientName");
		emailDto.setPatientEmail("");
		emailDto.setPhysicianName(appointment.getPhysicianName());

		if(appointment.getStatus().equalsIgnoreCase("Active")) {

			emailDto.setStatus("Appointment Scheduled");
		}
		else {
			emailDto.setStatus("Appointment Canceled");
		}

		emailDto.setDate(appointment.getSlot().getSlotDate().toString());
		emailDto.setTime(appointment.getSlot().getSlotStart().toString() + "-" +appointment.getSlot().getSlotStart().toString());

		return emailDto;
	}
	
	public static EmailDto appintmentToEmailDtomapper2(Appointment appointment) {

		EmailDto emailDto = new EmailDto();
		
		emailDto.setPatientName(appointment.getPatientName());
		emailDto.setPatientEmail(appointment.getPatientEmail());
	
		emailDto.setPhysicianName(appointment.getPhysicianName());
		emailDto.setPhysicianEmail(appointment.getPhysicianEmail());

		
		if(appointment.getStatus().equalsIgnoreCase("Active")) {

			emailDto.setStatus("Appointment Scheduled");
		}
		else {
			emailDto.setStatus("Appointment Canceled");
		}

		emailDto.setDate(appointment.getSlot().getSlotDate().toString());
		emailDto.setTime(appointment.getSlot().getSlotStart().toString() + "-" +appointment.getSlot().getSlotStart().toString());

		return emailDto;
	}

}
