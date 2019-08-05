package com.fpt.projectfinal.services.useraccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.useraccess.UserAccessDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;
import com.fpt.projectfinal.services.authentication.AuthenService;
import com.google.common.hash.Hashing;
@Service
public class UserAccessServiceImpl implements UserAccessService{

	@Autowired
	UserAccessDao userAccessDao;
	
	@Autowired
	MedicalRecordDao medicalRecordDao;
	

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	UserDao userDao ;
	@Override
	public String addUserAccess(Map<String, Object> payload) {
		if (payload.get("userID") == null) {
			return "userID null";
		}
		if (payload.get("medicalRecordID") == null) {
			return "medicalRecord null";
		}
		try {
			UserAccess userAccess = new UserAccess();
			MedicalRecord medical = medicalRecordDao.getMedicalRecordByID((int)payload.get("medicalRecordID"));
			User user = userDao.getUserByID((int)payload.get("userID"));
			userAccess.setUser(user);
			userAccess.setMedicalRecord(medical);
			userAccess.setStartedDate(new Date());
			userAccess.setStatus((int)payload.get("status"));
			userAccessDao.addUserAccess(userAccess);
			
			return "successful";
		} catch (Exception e) {
			return "add fail";
		}
		
	}
	@Override
	public String updateUserAccess(Map<String, Object> payload) {
		if (payload.get("userAccessID") == null) {
			return "userAccessID null";
		}
		if (payload.get("userID") == null) {
			return "userID null";
		}
		if (payload.get("medicalRecordID") == null) {
			return "medicalRecord null";
		}
		try {
			UserAccess userAccess = userAccessDao.getUserAccessByID((int)payload.get("userAccessID"));
			MedicalRecord medical = medicalRecordDao.getMedicalRecordByID((int)payload.get("medicalRecordID"));
			User user = userDao.getUserByID((int)payload.get("userID"));
			userAccess.setUser(user);
			userAccess.setMedicalRecord(medical);
			userAccess.setStartedDate(new Date());
			userAccess.setStatus((int)payload.get("status"));
			userAccessDao.updateUserAccess(userAccess);
			
			return "successful";
		} catch (Exception e) {
			return "update fail";
		}
	}
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Map<String, Object>> getUserAccessByMedicalRecordID(int medicalRecordID) {
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String,Object> message = new HashMap<>();
		if (medicalRecordID == 0) {
			message.put("message", "not have userAccess");
			return (List<Map<String, Object>>) message;
		}
		try {
			
			MedicalRecord medical = medicalRecordDao.getMedicalRecordByID(medicalRecordID);
			List<UserAccess> listUser = userAccessDao.getUserAccessByMedicalRecord(medical);
			
			for (UserAccess userAccess : listUser) {
				Map<String, Object> mapUser = new HashMap<>();
				
				mapUser.put("userID", userAccess.getUser().getUserID());
				mapUser.put("userName", userAccess.getUser().getFirstName());
				mapUser.put("status", userAccess.getStatus());
				mapUser.put("staredDate", userAccess.getStartedDate());
				mapUser.put("finishedDate",userAccess.getFinishedDate() );
				result.add(mapUser);
			}
			return result;
		}catch (Exception e) {
			message.put("message", "userAcces not have");
			return (List<Map<String, Object>>) message;
		}
	}
	@Override
	public List<Map<String, Object>> getUserAccessByUser() {
		Map<String, Object> listUserAccess = new HashMap<>();
		List<Map<String, Object>> result = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
	
		if (username.equals("")) {
			listUserAccess.put("message", "username null");
			result.add(listUserAccess);
			return result;
		}
		
		try {
			Account acc = accountDao.getAccountByEmail(username);
			User user = userDao.getUserByAccount(acc);
			List<UserAccess> userAccess = userAccessDao.getUserAccessByUser(user);
			for (UserAccess u : userAccess) {
				listUserAccess = new HashMap<>();
				listUserAccess.put("userAccessID", u.getUserAccessID());
				listUserAccess.put("permission ", u.getStatus());
				listUserAccess.put("medicalRecord", u.getMedicalRecord().getMedicalRecordID());
				listUserAccess.put("status", u.getMedicalRecord().getStatus());
				listUserAccess.put("manager", u.getUser().getFirstName());
				listUserAccess.put("createdDate", u.getMedicalRecord().getCreateDate());
				listUserAccess.put("modifiedDate", u.getMedicalRecord().getModifiedDate());
				result.add(listUserAccess);
				
			}
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listUserAccess.put("message", "get fail");
			result.add(listUserAccess);
			return result;
		}
	
	}
	

}