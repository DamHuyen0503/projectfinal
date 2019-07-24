package com.fpt.projectfinal.services.medicalrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MedicalRecordService {
	
	public Map<String, Object> addMedicalRecord(Map<String, Object> payload);
	
	public Map<String, Object> getMedicalRecordByID(String medicalRecordByID);
	
	public void updateMedicalRecord(Map<String, Object> payload);
	
	public List<Map<String, Object>> getMedicalRecordByDay(Date day);
}
