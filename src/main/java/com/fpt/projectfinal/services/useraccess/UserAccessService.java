package com.fpt.projectfinal.services.useraccess;

import java.util.List;
import java.util.Map;

public interface UserAccessService {
	public String addUserAccess(Map<String, Object> payload);
	
	public String updateUserAccess(Map<String, Object> payload);
	
	public  List<Map<String, Object>> getUserAccessByMedicalRecordID(int medicalRecordID);
	
	public  List<Map<String, Object>> getUserAccessByUser();
	
	
}
