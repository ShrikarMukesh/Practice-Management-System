package com.citiustech.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medication {

	private String applNo;
	private String productNo;
	private String form;
	private String strength;
	private String referenceDrug;
	private String drugName;
	private String activeIngredient;
	private String referenceStandard;
}
