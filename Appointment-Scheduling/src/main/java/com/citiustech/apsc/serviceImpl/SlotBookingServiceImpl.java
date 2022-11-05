package com.citiustech.apsc.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.apsc.model.Slot;
import com.citiustech.apsc.model.SlotDate;
import com.citiustech.apsc.repository.SlotRepository;
import com.citiustech.apsc.service.SlotBookingService;

@Service
public class SlotBookingServiceImpl implements SlotBookingService {

	@Autowired
	SlotRepository slotRepository;

	@Override
	public List<Slot> getAvailableSlotsBetween(SlotDate slotDate) {
		return slotRepository.findBySlotDateBetween(LocalDate.parse(slotDate.getStartDate()), LocalDate.parse(slotDate.getEndDate()))
				.stream()
				.filter(s->s.getPhysicianId()==slotDate.getPhysicianId()).collect(Collectors.toList());
	}




	@Override
	public List<Slot> getSlotsByDate(SlotDate slotDate) {
		return slotRepository.getSlotBySlotDateAndPhysicianId(LocalDate.parse(slotDate.getDate()), slotDate.getPhysicianId());
	}
	
}
