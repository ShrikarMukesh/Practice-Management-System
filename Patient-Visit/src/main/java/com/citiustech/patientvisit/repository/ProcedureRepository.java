package com.citiustech.patientvisit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.citiustech.patientvisit.model.Procedure;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure,String>{

}
