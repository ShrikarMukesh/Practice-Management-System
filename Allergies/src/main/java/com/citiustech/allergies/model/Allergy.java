package com.citiustech.allergies.model;

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
@Table(name="allergy")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Allergy {
	
	@Id
	@Column(name="allergy_id")
	private String allergyId;
	
	@Column(name="allergy_type")
	private String alergyType;
	
	@Column(name="allergy_name")
	private String allergyName;
	
	@Column(name="allergen_source")
	private String allergenSource;
	
	@Column(name="isoforms")
	private String isoforms;
	
	@Column(name = "allergini_city")
	private String allerginiCity;
	
	

}
