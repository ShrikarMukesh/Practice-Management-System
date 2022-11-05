package com.citiustech.procedure;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.citiustech.procedure.model.Procedure;
import com.citiustech.procedure.repository.ProcedureRepository;

@SpringBootTest
public class ProcedureRepositoryTest {

	@Autowired
	private ProcedureRepository repository;

	@Test
	public void SaveProcedureMaterial() {
		System.out.println("Inside Save Procedure save");
		
		Procedure procedure = new Procedure();
		procedure.setApproachType("ABC");
		procedure.setProcedureCode("111");
		procedure.setProcedureDescription("Helm");
		procedure.setProcedureIsDepricated(false);
		System.out.println(procedure);
		repository.save(procedure);
		/*
		 * List<Procedure> list = Arrays.asList(new Procedure("001607A","Open Approach"
		 * ,false,"Bypass Cerebral Ventricle to Subgaleal Space with Autologous Tissue Substitute"
		 * ), new Procedure("001607B","New Approach"
		 * ,false,"Bypass Cerebral Ventricle to Subgaleal Space with Autologous Tissue Substitute"
		 * ));
		 */
		
		/*
		 * System.out.println(list); list.forEach(precedure ->
		 * repository.save(procedure));
		 */
	}

	@Test
	public void printAllprocedures() {
		List<Procedure> procedures =  repository.findAll();
		System.out.println("procedures = " + procedures);
	}
}
