package com.citiustech.diagnosis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="diagnosis")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Diagnosis {
     
	@Id
	@Column(name="diagnosis_code")
	private String diagnosisCode;
	
	@Column(name="diagnosis_description")
	private String diagnosisDescription;
	
	@Column(name="diagnosis_is_depricated")
	private boolean diagnosisIsDepricated;
}
