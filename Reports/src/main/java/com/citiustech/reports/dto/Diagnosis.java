package com.citiustech.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Diagnosis {
     
	private String diagnosisCode;
	private String diagnosisDescription;	
	private boolean diagnosisIsDepricated;
}
