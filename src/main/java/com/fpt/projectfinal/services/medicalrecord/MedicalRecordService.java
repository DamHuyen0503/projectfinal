package com.fpt.projectfinal.services.medicalrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.UserAccess;

public interface MedicalRecordService {
	
	/*
	 * Create new medical records.
	 * 新しい医療記録を作成します。
	 */
	public Map<String, Object> addMedicalRecord(Map<String, Object> payload);
	
	/*
	 * Get all client information according to clientID.
	 * clientIDに従ってすべてのクライアント情報を取得します。
	 */
	public Map<String, Object> getMedicalRecordByID(int medicalRecordByID);
	
	/*
	 * Update medical record information.
	 * 医療記録情報を更新します。
	 */
	public String updateMedicalRecord(Map<String, Object> payload) throws Exception;
	
	/*
	 * Get all the medical record information created in the day.
	 * その日に作成されたすべての医療記録情報を取得します。
	 */
	public List<Map<String, Object>> getMedicalRecordByDay(Date day);
	
	
	/*
	 * Get all the information of all medical records when the user is admin.
	 * ユーザーが管理者である場合、すべての医療記録のすべての情報を取得します。
	 */
	public List<Map<String, Object>> getMedicalRecordByClient(int clientID);
	
	/*
	 * Delete medical records.
	 * 医療記録を削除します。
	 */
	public List<MedicalRecord> deleteMedicalRecord(int medicalRecordID);
	
	public List<MedicalRecord> getMedicalRecordByUserAccess(UserAccess userAccess);
}
