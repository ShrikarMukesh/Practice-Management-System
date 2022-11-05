package com.citiustech.procedure;

import java.util.Arrays;
import java.util.List;


import com.citiustech.procedure.model.Procedure;

public class Demo {
	public static void main(String[] args) {
	
		List<Procedure> list = Arrays.asList(new Procedure("001607A","Open Approach",false,"Bypass Cerebral Ventricle to Subgaleal Space with Autologous Tissue Substitute"),
				                            new Procedure("001607B","New Approach",false,"Bypass Cerebral Ventricle to Subgaleal Space with Autologous Tissue Substitute"));
		list.forEach(pro->System.out.println(pro.getApproachType()));
	}
	
	
}
