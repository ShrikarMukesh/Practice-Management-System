package com.citiustech.dto;


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
public class ProcedureDto {
     
	
	private String procedureCode;
	
	
	private String procedureDescription;
	
	
	private boolean procedureIsDepricated;
	
	
	private String approachType;
}