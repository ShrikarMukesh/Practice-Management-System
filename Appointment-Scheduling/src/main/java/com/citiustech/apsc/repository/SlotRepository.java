package com.citiustech.apsc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.apsc.model.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long>{
	
	//public List<Slot> getSlotsByDoctorId(Long physicianId);
	
	public List<Slot> findBySlotDateBetween(LocalDate startDate, LocalDate EndDate);
	
	public List<Slot> getSlotBySlotDateAndPhysicianId(LocalDate sloteDate, long physicianId);


}
