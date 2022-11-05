package com.citiustech.apsc.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SlotDate {

	private String startDate;
	private String endDate;
	private String date;
	private long physicianId;
}
