package com.fpt.projectfinal.daos.useraccess;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;

public interface UserAccessDao {
	public void addUserAccess(UserAccess userAccess);
	
	public void updateUserAccess(UserAccess userAccess);
	
	public UserAccess getUserAccessByID(int userAccessID);
	
	public List<UserAccess> getUserAccessByMedicalRecord(MedicalRecord medicalRecord);
	
	public List<UserAccess> getUserAccessByUser(User user);
}
