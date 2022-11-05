package com.citiustech.diagnosis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.diagnosis.model.Diagnosis;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, String>{

}
