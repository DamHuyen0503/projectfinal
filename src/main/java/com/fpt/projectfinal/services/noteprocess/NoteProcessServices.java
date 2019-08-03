package com.fpt.projectfinal.services.noteprocess;

import java.util.Map;

public interface NoteProcessServices {
	public Map<String, Object> getNoteProcessByMedicalRecordID(int medicalRecordID);
	
	public String addNoteProcess(Map<String, Object> payload);

	public String updateNoteProcess(Map<String, Object> payload);
}
