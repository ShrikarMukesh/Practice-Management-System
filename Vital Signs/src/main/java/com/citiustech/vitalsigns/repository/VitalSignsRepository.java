package com.citiustech.vitalsigns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.vitalsigns.model.VitalSigns;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns,Long>{

}
