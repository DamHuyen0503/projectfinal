package com.fpt.projectfinal.daos.medicalrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;

public interface MedicalRecordDao {

	public void addMedicalRecord(MedicalRecord medicalRecord);
	
	
	public boolean checkExitMedicalRecord(int medicalRecordID);
	
	public MedicalRecord getMedicalRecordByID(int medicalRecordID);
	
	public void updateMedicalRecord(MedicalRecord medicalRecord);
	
	public List<MedicalRecord> getMedicalRecordByDay(Date day);
	
	public List<MedicalRecord> getMedicalRecordByClient( Client client);
	
	public void deleteMedical(MedicalRecord medicalRecord);
}
