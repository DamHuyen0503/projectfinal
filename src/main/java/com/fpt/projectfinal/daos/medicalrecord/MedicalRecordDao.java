package com.fpt.projectfinal.daos.medicalrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;

public interface MedicalRecordDao {

	public void addMedicalRecord(MedicalRecord medicalRecord);
	
	public Map<String, Date> getMedicalRecordID(MedicalRecord medicalRecord);
	
	public boolean checkExitMedicalRecord(String medicalRecordID);
	
	public MedicalRecord getMedicalRecordByID(String medicalRecordID);
	
	public void updateMedicalRecord(MedicalRecord medicalRecord);
	
	public List<MedicalRecord> getMedicalRecordByDay(Date day);
	
	public List<MedicalRecord> getMedicalRecordByClient( Client client);
}
