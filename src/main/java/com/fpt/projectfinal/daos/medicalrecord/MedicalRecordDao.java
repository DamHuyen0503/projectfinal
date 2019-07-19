package com.fpt.projectfinal.daos.medicalrecord;

import java.util.Date;
import java.util.Map;

import com.fpt.projectfinal.models.MedicalRecord;

public interface MedicalRecordDao {

	public void addMedicalRecord(MedicalRecord medicalRecord);
	
	public Map<String, Date> getMedicalRecordID(MedicalRecord medicalRecord);
	
	public boolean checkExitMedicalRecord(String medicalRecordID);
	
	public MedicalRecord getMedicalRecordByID(String medicalRecordID);
}
