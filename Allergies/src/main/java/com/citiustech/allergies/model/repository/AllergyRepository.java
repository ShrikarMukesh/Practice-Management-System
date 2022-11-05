package com.citiustech.allergies.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.allergies.model.Allergy;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, String>{

}
