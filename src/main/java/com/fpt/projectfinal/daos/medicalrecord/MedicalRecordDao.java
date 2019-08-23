package com.fpt.projectfinal.daos.medicalrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;

public interface MedicalRecordDao {

	/*
	 * Create new medical records.
	 * 新しい医療記録を作成します。
	 */
	public void addMedicalRecord(MedicalRecord medicalRecord);
	
	/*
	 * Check if the medical record ID already exists.
	 * 医療記録IDがすでに存在するかどうかを確認してください。
	 */
	public boolean checkExitMedicalRecord(int medicalRecordID);
	
	/*
	 * Get all client information according to clientID.
	 * clientIDに従ってすべてのクライアント情報を取得します。
	 */
	public MedicalRecord getMedicalRecordByID(int medicalRecordID);
	
	/*
	 * Update medical record information.
	 * 医療記録情報を更新します。
	 */
	public void updateMedicalRecord(MedicalRecord medicalRecord);
	
	/*
	 * Get all the medical record information created in the day.
	 * その日に作成されたすべての医療記録情報を取得します。
	 */
	public List<MedicalRecord> getMedicalRecordByDay(Date day);
	
	/*
	 * Get all the information of all medical records when the user is admin.
	 * ユーザーが管理者である場合、すべての医療記録のすべての情報を取得します。
	 */
	public List<MedicalRecord> getMedicalRecordByClient( Client client);
	
	/*
	 * Delete medical records.
	 * 医療記録を削除します。
	 */
	public void deleteMedical(MedicalRecord medicalRecord);
	
	/*
	 * Get all medical record when user authorized.
	 * ユーザーが承認したときにすべての医療記録を取得します。
	 */
	public List<MedicalRecord> getMedicalRecordByUserAccess(UserAccess userAccess);
	
	/*
	 * Get medical records according to the note procedure.
	 * メモ手順に従って医療記録を入手します。
	 */
	public List<MedicalRecord> getMedicalByNoteProcess(NoteProcess note);
	
	public List<MedicalRecord> getAll();
}
