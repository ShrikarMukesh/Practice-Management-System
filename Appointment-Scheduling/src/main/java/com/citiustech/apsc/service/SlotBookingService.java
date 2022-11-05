package com.citiustech.apsc.service;

import java.util.List;

import com.citiustech.apsc.model.Slot;
import com.citiustech.apsc.model.SlotDate;

public interface SlotBookingService {
	
	 //Slot bookSlot(Long physicianId, Slot slot);
	
	 List<Slot> getAvailableSlotsBetween(SlotDate slotDate);
	 
	 List<Slot> getSlotsByDate(SlotDate slotDate);
	 
	 

}
