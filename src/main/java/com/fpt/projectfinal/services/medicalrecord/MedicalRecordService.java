package com.fpt.projectfinal.services.medicalrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;

public interface MedicalRecordService {
	
	public Map<String, Object> addMedicalRecord(Map<String, Object> payload);
	
	public Map<String, Object> getMedicalRecordByID(int medicalRecordByID);
	
	public void updateMedicalRecord(Map<String, Object> payload);
	
	public List<Map<String, Object>> getMedicalRecordByDay(Date day);
	
	public Map<String, Object> getMedicalRecordByClient(Client client);
	
	public List<MedicalRecord> deleteMedicalRecord(int medicalRecordID);
}
