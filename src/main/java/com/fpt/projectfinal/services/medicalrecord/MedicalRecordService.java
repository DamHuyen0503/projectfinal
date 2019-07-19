package com.fpt.projectfinal.services.medicalrecord;

import java.util.Map;

import com.fpt.projectfinal.models.MedicalRecord;

public interface MedicalRecordService {
	
	public Map<String, Object> addMedicalRecord(Map<String, Object> payload);
	
	public Map<String, Object> getMedicalRecordByID(String medicalRecordByID);
}
