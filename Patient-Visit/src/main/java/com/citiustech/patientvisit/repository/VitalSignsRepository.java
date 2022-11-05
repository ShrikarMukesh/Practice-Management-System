package com.citiustech.patientvisit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.patientvisit.model.VitalSigns;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns, Long>{

}
