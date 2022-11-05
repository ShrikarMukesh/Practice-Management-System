package com.citiustech.patientvisit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.patientvisit.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String>{
       
}
