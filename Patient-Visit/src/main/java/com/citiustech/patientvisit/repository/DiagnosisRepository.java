package com.citiustech.patientvisit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.patientvisit.model.Diagnosis;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, String>{

}
