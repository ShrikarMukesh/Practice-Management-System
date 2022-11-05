package com.citiustech.vitalsigns.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vital_signs")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VitalSigns {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vitalsignid;

    private int height;

    private double weight;

    @Column(name = "bp_systolic")
    private float bpSystolic;

    @Column(name = "bp_diastolic")
    private float bpDiastolic;

    @Column(name = "respiration_rate")
    private float  RespirationRate;

   
}
