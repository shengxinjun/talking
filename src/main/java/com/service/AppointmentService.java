package com.service;

import java.util.List;

import com.domain.Appointment;
import com.util.Paging;

public interface AppointmentService {
	
	Paging<Appointment> list(Paging<Appointment> paging);
	
	void insert(Appointment appointment);
	
	void ensureConsumption(Integer id);
}
