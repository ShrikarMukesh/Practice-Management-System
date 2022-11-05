package com.citiustech.medication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.medication.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String>{

}
