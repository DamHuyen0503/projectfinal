package com.fpt.projectfinal.daos.noteProcess;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;

public interface NoteProcessDao {
	
	public Map<String, Object> getNoteProcessByMedicalRecord(MedicalRecord medicalRecord);
	
	public Map<String, Object> getNoteProcessByMedicalRecordID (String medicalRecordID);
}
