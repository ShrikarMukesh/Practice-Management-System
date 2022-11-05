package com.citiustech.reports.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VitalSigns {
	
    private long vitalsignid;
    private int height;
    private double weight;
 
    private float bpSystolic;

    private float bpDiastolic;

    private float  RespirationRate;

}
