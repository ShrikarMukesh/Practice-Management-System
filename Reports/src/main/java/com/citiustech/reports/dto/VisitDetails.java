package com.citiustech.reports.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class VisitDetails {

	private long visitid;
	
	private long patientId;
	
	private String visitedAt;
	private VitalSigns vitalSigns;
	
	private List<Medication> medication = new ArrayList<>();
	private List<Procedure> procedure = new ArrayList<>();
	private List<Diagnosis> diagnosis = new ArrayList<>();

}