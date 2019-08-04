package com.fpt.projectfinal.services.noteprocess;

import java.util.Map;

public interface NoteProcessServices {
	public Map<String, Object> getNoteProcessByMedicalRecordID(int medicalRecordID);
	
	public Map<String, Object> addNoteProcess(Map<String, Object> payload);

	public String updateNoteProcess(Map<String, Object> payload);
}
