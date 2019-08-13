package com.fpt.projectfinal.services.noteprocess;

import java.util.List;
import java.util.Map;

public interface NoteProcessServices {
	

	/*
	 * Get all notes of a medical record.
	 * 医療記録のすべてのメモを取得します。
	 */
	public Map<String, Object> getNoteProcessByMedicalRecordID(int medicalRecordID);
	
	/*
	 * Create new notes of a medical record.
	 * 医療記録の新しいメモを作成します。
	 */
	public Map<String, Object> addNoteProcess(Map<String, Object> payload);

	/*
	 * Update more notes for medical records.
	 * 医療記録のメモを更新します。
	 */
	public String updateNoteProcess(Map<String, Object> payload);
	
	/*
	 * Get all user notes.
	 * すべてのユーザーメモを取得します。
	 */
	public List<Map<String, Object>> getNoteProcessByUserSendRequest();
}
