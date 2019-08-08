package com.fpt.projectfinal.daos.noteProcess;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.User;

public interface NoteProcessDao {
	
	public List<NoteProcess> getNoteProcessByMedicalRecord(MedicalRecord medicalRecord);
	
	public List<NoteProcess> getNoteProcessByMedicalRecordID (int medicalRecordID);
	
	public void addNoteProcess(NoteProcess noteProcess);
	
	public void updateNoteProcess(NoteProcess noteProcess);
	
	public NoteProcess getNoteProcessByID (int notePeocessID);
	
	public List<NoteProcess> getNoteProcessByUserSendRequest(User user);
}
