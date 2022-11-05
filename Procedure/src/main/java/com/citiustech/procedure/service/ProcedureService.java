package com.citiustech.procedure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.procedure.model.Procedure;
import com.citiustech.procedure.repository.ProcedureRepository;

@Service
public class ProcedureService {

	@Autowired
	private ProcedureRepository procedureRepo;

	public List<Procedure> allProcedures(){
		return procedureRepo.findAll();
	}

	public Procedure getProcedureById(String id) {

		return procedureRepo.findById(id).get();
	}

	public Procedure addProcedure(Procedure procedure) {

		return procedureRepo.save(procedure);
	}
}
