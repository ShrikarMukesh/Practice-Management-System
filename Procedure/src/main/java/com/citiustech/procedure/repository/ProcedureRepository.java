package com.citiustech.procedure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.procedure.model.Procedure;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure,String>{

}
