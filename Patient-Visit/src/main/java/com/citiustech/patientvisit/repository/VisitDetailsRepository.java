package com.citiustech.patientvisit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.patientvisit.model.VisitDetails;

@Repository
public interface VisitDetailsRepository extends JpaRepository<VisitDetails, Long>{

}
