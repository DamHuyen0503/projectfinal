package com.fpt.projectfinal.daos.useraccess;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;

public interface UserAccessDao {
	
	/*
	 * Create new users who are granted the right to manage medical records.
	 * 医療記録を管理する権利を付与された新しいユーザーを作成します。
	 */
	public void addUserAccess(UserAccess userAccess);
	
	/*
	 * update users who are granted the right to manage medical records.
	 * 医療記録を管理する権利を付与されたユーザーを更新します。
	 */
	public void updateUserAccess(UserAccess userAccess);
	
	/*
	 * get user access by ID
	 * IDでユーザーアクセスを取得する
	 */
	public UserAccess getUserAccessByID(int userAccessID);
	
	/*
	 * get all the manager of the medical record.
	 * 医療記録のすべての管理者を取得します。
	 */
	public List<UserAccess> getUserAccessByMedicalRecord(MedicalRecord medicalRecord);
	
	/*
	 * get all of the user's profile management rights.
	 * ユーザーのすべてのプロファイル管理権限を取得します。
	 */
	public List<UserAccess> getUserAccessByUser(User user, int clientID);
}
