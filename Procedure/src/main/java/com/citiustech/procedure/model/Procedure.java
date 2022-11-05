package com.citiustech.procedure.model;

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
@Table(name="procedures")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Procedure {
     
	@Id
	@Column(name = "procedure_code")
	private String procedureCode;
	
	@Column(name = "procedure_description")
	private String procedureDescription;
	
	@Column(name="procedure_is_depricated")
	private boolean procedureIsDepricated;
	
	@Column(name="approach_type")
	private String approachType;
}
