package com.fpt.projectfinal.services.useraccess;

import java.util.List;
import java.util.Map;

public interface UserAccessService {
	
	/*
	 * Create new users who are granted the right to manage medical records.
	 * 医療記録を管理する権利を付与された新しいユーザーを作成します。
	 */
	public String addUserAccess(Map<String, Object> payload);
	
	/*
	 * update users who are granted the right to manage medical records.
	 * 医療記録を管理する権利を付与されたユーザーを更新します。
	 */
	public String updateUserAccess(Map<String, Object> payload);
	
	/*
	 * get all the manager of the medical record.
	 * 医療記録のすべての管理者を取得します。
	 */
	public  List<Map<String, Object>> getUserAccessByMedicalRecordID(int medicalRecordID);
	
	/*
	 * get all of the user's profile management rights.
	 * ユーザーのすべてのプロファイル管理権限を取得します。
	 */
	public  List<Map<String, Object>> getUserAccessByUser();
	
	
}
