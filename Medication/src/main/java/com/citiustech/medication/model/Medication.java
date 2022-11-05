package com.citiustech.medication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="medication")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medication {
	
	  @Id
	  private String applNo;
	  
	  @Column(name="product_no")
	  private String productNo;
	  
	  private String form;
	 
	  private String strength;
	  
	  @Column(name="reference_drug")
	  private String referenceDrug;
	  
	  @Column(name="drug_name")
	  private String drugName;
	  
	  @Column(name="active_ingredient")
	  private String activeIngredient;
	  
	  @Column(name="reference_standard")
	  private String referenceStandard;
}
