package com.fpt.projectfinal.daos.noteProcess;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.User;

public interface NoteProcessDao {
	

	/*
	 * Get all notes of a medical record.
	 * 医療記録のすべてのメモを取得します。
	 */
	public List<NoteProcess> getNoteProcessByMedicalRecord(MedicalRecord medicalRecord);
	
	/*
	 * Get all the notes according to the medical record ID.
	 * medicalRecordIDに従ってすべてのメモを取得します。
	 */
	public List<NoteProcess> getNoteProcessByMedicalRecordID (int medicalRecordID);
	
	/*
	 * Create new notes of a medical record.
	 * 医療記録の新しいメモを作成します。
	 */
	public void addNoteProcess(NoteProcess noteProcess);
	
	/*
	 * Update more notes for medical records.
	 * 医療記録のメモを更新します。
	 */
	public void updateNoteProcess(NoteProcess noteProcess);
	
	/*
	 * Get all the notes according to the note  process ID.
	 * noteProcessIDに従ってすべてのノートを取得します。
	 */
	public NoteProcess getNoteProcessByID (int notePeocessID);
	
	/*
	 * Get all user notes.
	 * すべてのユーザーメモを取得します。
	 */
	public List<NoteProcess> getNoteProcessByUserSendRequest(User user);
}
