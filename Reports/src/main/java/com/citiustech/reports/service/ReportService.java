package com.citiustech.reports.service;

import com.citiustech.reports.dto.VisitDetails;

public interface ReportService {
	
	public VisitDetails lastVisitDetail(long patientId);
}
