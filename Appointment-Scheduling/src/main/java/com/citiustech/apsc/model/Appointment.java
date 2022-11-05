package com.citiustech.apsc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_id")
	private long appointmentId;
	
	@Column(name = "meeting_title")
	private String meetingTitle;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "edit_history_id")
    private long edit_historyId;
	
	//physician--
	@Column(name = "physician_id")
    private long physicianId;
    
	@Column(name = "physician_email")
    private String physicianEmail;
	
	@Column(name = "physician_name")
    private String physicianName;
	
	//patient--
	@Column(name = "patient_id")
    private long patientId;
	
	@Column(name = "patient_email")
    private String patientEmail;
	
	@Column(name = "patient_name")
    private String patientName;
	
	//The Person who is scheduling appointment
	@Column(name ="user_id")
	private long userId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "slot_id")
    private Slot slot;

}
