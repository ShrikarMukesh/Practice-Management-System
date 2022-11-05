package com.citiustech.patientvisit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "visit")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class VisitDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long visitid;

	@Column(name="patient_id")
	private long patientId;

	@Column(name="visited_at")
	private String visitedAt;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "vitalsign_id")
	private VitalSigns vitalSigns;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Medication> medication = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Procedure> procedure = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Diagnosis> diagnosis = new ArrayList<>();

}