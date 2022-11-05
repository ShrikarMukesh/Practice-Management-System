package com.citiustech.apsc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto {
	
	
	private String physicianName;
	private String physicianEmail;
	
	private String patientName;
	private String patientEmail;
	
	private String status;
	private String date;
	private String time;

}
