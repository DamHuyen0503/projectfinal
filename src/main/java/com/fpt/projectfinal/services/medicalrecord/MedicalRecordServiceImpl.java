package com.fpt.projectfinal.services.medicalrecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.client.ClientDao;
import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.daos.noteProcess.NoteProcessDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.useraccess.UserAccessDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.UserAccess;
import com.fpt.projectfinal.utils.ConvertDateTime;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
	@Autowired
	MedicalRecordDao medicalRecordDao;

	@Autowired
	ClientDao clientDao;
	
	@Autowired
	NoteProcessDao noteProcessDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	AccountDao accountDao;
	
	@Autowired
	UserAccessDao userAccessDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> addMedicalRecord(Map<String, Object> payload) {
		Map<String, Object> mapOutput = new HashMap<String, Object>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account acc = accountDao.getAccountByEmail(username);
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setCreateDate(new Date());
		medicalRecord.setStatus((int) payload.get("status"));
		medicalRecord.setPyschologyType((String) payload.get("pshychologyType"));
		medicalRecord.setJob((String) payload.get("job"));
		medicalRecord.setCurrentAddress((String) payload.get("currentAddress"));
		medicalRecord.setRelativesPhone((String) payload.get("relativesPhone"));
		medicalRecord.setPyschologyType((String) payload.get("pshychologyType"));
		medicalRecord.setOtherStatus((String) payload.get("otherStatus"));
		medicalRecord.setRelativesName((String) payload.get("relativesName"));
		medicalRecord.setNoteOfRelatives((String) payload.get("noteOfRelatives"));

		medicalRecord.setRelativesRelationship((String) payload.get("relativesRelationship"));
		medicalRecord.setHealth((String) payload.get("health"));
		medicalRecord.setLearningResult((String) payload.get("learningResult"));
		medicalRecord.setFriendStatus((String) payload.get("friendStatus"));
		medicalRecord.setFamilyStatus((String) payload.get("familyStatus"));
		medicalRecord.setAroundReact((String) payload.get("aroundReact"));
		medicalRecord.setFeelingHistory((String) payload.get("feelingHistory"));
		medicalRecord.setReason((String) payload.get("reason"));

		medicalRecord.setSolutionInPast((String) payload.get("solutionInPast"));
		medicalRecord.setProblemProcess((String) payload.get("problemProcess"));
		medicalRecord.setCurrentProblem((String) payload.get("currentProblem"));

		medicalRecord.setAppearanceOfProblem((String) payload.get("appearanceOfProblem"));
		medicalRecord.setReactHistory((String) payload.get("reactHistory"));
		medicalRecord.setStrength((String) payload.get("strength"));
		medicalRecord.setResource((String) payload.get("resource"));
		medicalRecord.setObstacle((String) payload.get("obstacle"));
		medicalRecord.setResult((String) payload.get("result"));
		medicalRecord.setProcessing((String) payload.get("processing"));
		medicalRecord.setCause((String) payload.get("cause"));
		medicalRecord.setMethod((String) payload.get("method"));
		medicalRecord.setProblem((String) payload.get("problem"));

		Client client = clientDao.getClientByID((int) payload.get("clientID"));
		medicalRecord.setClient(client);
		
		
		Set<NoteProcess> notes = new HashSet<>();
		ArrayList<Object> note_list = (ArrayList<Object>) payload.get("notes");
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		Account acc = accountDao.getAccountByEmail(username);
		for (Object result : note_list) {
			Map<String, Object> resultmap = (Map) result;
			try {
				NoteProcess note = new NoteProcess(
						ConvertDateTime.ConvertDate(
								(String)resultmap.get("startTime")
								), 
						ConvertDateTime.ConvertDate(
								(String)resultmap.get("endTime")
								), 
						(String)resultmap.get("content"), 
						(int)resultmap.get("evaluation")
						);
				
				note.setMedicalRecord(medicalRecord);
				note.setUser(acc.getUser());
				notes.add(note);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		medicalRecord.setNoteProcess(notes);
		
		if (!medicalRecordDao.checkExitMedicalRecord(medicalRecord.getMedicalRecordID())) {
			medicalRecordDao.addMedicalRecord(medicalRecord);
			mapOutput.put("medicalRecordID", medicalRecord.getMedicalRecordID());
			mapOutput.put("createdDate", medicalRecord.getCreateDate());
		} else
			mapOutput.put("error", "duplicate medicalRecordID");
		return mapOutput;
	}

	@Override
	public Map<String, Object> getMedicalRecordByID(int id) {
		if (id == 0) {
			return null;
		}
		MedicalRecord medicalRecord = medicalRecordDao.getMedicalRecordByID(id);
		Map<String, Object> mapRecord = new HashMap<>();
		mapRecord.put("medicalRecordID", medicalRecord.getMedicalRecordID());
		mapRecord.put("createdDate", medicalRecord.getCreateDate());
		mapRecord.put("pshychologyType", medicalRecord.getPyschologyType());
		mapRecord.put("modifiedDate", new Date());
		mapRecord.put("job", medicalRecord.getJob());
		mapRecord.put("currentAddress", medicalRecord.getCurrentAddress());
		mapRecord.put("relativesPhone", medicalRecord.getRelativesPhone());
		mapRecord.put("otherStatus", medicalRecord.getObstacle());
		mapRecord.put("relativesName", medicalRecord.getRelativesName());
		mapRecord.put("noteOfRelatives", medicalRecord.getNoteOfRelatives());
		mapRecord.put("relativesRelationship", medicalRecord.getRelativesRelationship());
		mapRecord.put("health", medicalRecord.getHealth());
		mapRecord.put("learningResult", medicalRecord.getLearningResult());
		mapRecord.put("friendStatus", medicalRecord.getFriendStatus());
		mapRecord.put("familyStatus", medicalRecord.getFamilyStatus());
		mapRecord.put("aroundReact", medicalRecord.getAroundReact());
		mapRecord.put("feelingHistory", medicalRecord.getFeelingHistory());
		mapRecord.put("reason", medicalRecord.getReason());
		mapRecord.put("solutionInPast", medicalRecord.getSolutionInPast());
		mapRecord.put("problemProcess", medicalRecord.getProblemProcess());	
		mapRecord.put("currentProblem", medicalRecord.getCurrentProblem());
		mapRecord.put("appearanceOfProblem", medicalRecord.getAppearanceOfProblem());
		mapRecord.put("reactHistory", medicalRecord.getReactHistory());
		mapRecord.put("strength", medicalRecord.getStrength());
		mapRecord.put("resource", medicalRecord.getReason());
		mapRecord.put("obstacle", medicalRecord.getObstacle());
		mapRecord.put("result", medicalRecord.getResult());
		mapRecord.put("processing", medicalRecord.getProcessing());
		mapRecord.put("cause", medicalRecord.getCause());
		mapRecord.put("method", medicalRecord.getMethod());
		mapRecord.put("problem", medicalRecord.getProblem());
		mapRecord.put("clientID", medicalRecord.getClient().getClientID());
		return mapRecord;
	}

	@Override
	public void updateMedicalRecord(Map<String, Object> payload) {
		@SuppressWarnings("unused")
		Map<String, Object> mapOutput = new HashMap<String, Object>();
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setMedicalRecordID((int) payload.get("medicalRecordID"));
		medicalRecord.setCreateDate(new Date());
		medicalRecord.setStatus((int) payload.get("status"));
		medicalRecord.setPyschologyType((String) payload.get("pshychologyType"));
		medicalRecord.setJob((String) payload.get("job"));
		medicalRecord.setCurrentAddress((String) payload.get("currentAddress"));
		medicalRecord.setRelativesPhone((String) payload.get("relativesPhone"));
		medicalRecord.setPyschologyType((String) payload.get("pshychologyType"));
		medicalRecord.setOtherStatus((String) payload.get("otherStatus"));
		medicalRecord.setRelativesName((String) payload.get("relativesName"));
		medicalRecord.setNoteOfRelatives((String) payload.get("noteOfRelatives"));

		medicalRecord.setRelativesRelationship((String) payload.get("relativesRelationship"));
		medicalRecord.setHealth((String) payload.get("health"));
		medicalRecord.setLearningResult((String) payload.get("learningResult"));
		medicalRecord.setFriendStatus((String) payload.get("friendStatus"));
		medicalRecord.setFamilyStatus((String) payload.get("familyStatus"));
		medicalRecord.setAroundReact((String) payload.get("aroundReact"));
		medicalRecord.setFeelingHistory((String) payload.get("feelingHistory"));
		medicalRecord.setReason((String) payload.get("reason"));

		medicalRecord.setSolutionInPast((String) payload.get("solutionInPast"));
		medicalRecord.setProblemProcess((String) payload.get("problemProcess"));
		medicalRecord.setCurrentProblem((String) payload.get("currentProblem"));

		medicalRecord.setAppearanceOfProblem((String) payload.get("appearanceOfProblem"));
		medicalRecord.setReactHistory((String) payload.get("reactHistory"));
		medicalRecord.setStrength((String) payload.get("strength"));
		medicalRecord.setResource((String) payload.get("resource"));
		medicalRecord.setObstacle((String) payload.get("obstacle"));
		medicalRecord.setResult((String) payload.get("result"));
		medicalRecord.setProcessing((String) payload.get("processing"));
		medicalRecord.setCause((String) payload.get("cause"));
		medicalRecord.setMethod((String) payload.get("method"));
		medicalRecord.setProblem((String) payload.get("problem"));

		Client client = clientDao.getClientByID((int) payload.get("clientID"));
		medicalRecord.setClient(client);
		
		
		Set<NoteProcess> notes = new HashSet<>();
		ArrayList<Object> note_list = (ArrayList<Object>) payload.get("notes");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account acc = accountDao.getAccountByEmail(username);
		for (Object result : note_list) {
			Map<String, Object> resultmap = (Map) result;
			try {
				NoteProcess note = new NoteProcess(
						ConvertDateTime.ConvertDate(
								(String)resultmap.get("startTime")
								), 
						ConvertDateTime.ConvertDate(
								(String)resultmap.get("endTime")
								), 
						(String)resultmap.get("content"), 
						(int)resultmap.get("evaluation")
						);
				
				note.setMedicalRecord(medicalRecord);
				note.setUser(acc.getUser());
				notes.add(note);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		medicalRecord.setNoteProcess(notes);
		medicalRecordDao.updateMedicalRecord(medicalRecord);
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordByDay(Date day) {
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String,  Object> mapMedical = new HashMap<>();
		
		List<MedicalRecord> medicalRecords = medicalRecordDao.getMedicalRecordByDay(day);
		for (MedicalRecord medicalRecord : medicalRecords) {
			Map<String, Object> mapRecord = new HashMap<>();
			mapRecord.put("medicalRecordID", medicalRecord.getMedicalRecordID());
			mapRecord.put("createdDate", medicalRecord.getCreateDate());
			mapRecord.put("pshychologyType", medicalRecord.getPyschologyType());
			mapRecord.put("modifiedDate", new Date());
			mapRecord.put("job", medicalRecord.getJob());
			mapRecord.put("currentAddress", medicalRecord.getCurrentAddress());
			mapRecord.put("relativesPhone", medicalRecord.getRelativesPhone());
			mapRecord.put("otherStatus", medicalRecord.getObstacle());
			mapRecord.put("relativesName", medicalRecord.getRelativesName());
			mapRecord.put("noteOfRelatives", medicalRecord.getNoteOfRelatives());
			mapRecord.put("relativesRelationship", medicalRecord.getRelativesRelationship());
			mapRecord.put("health", medicalRecord.getHealth());
			mapRecord.put("learningResult", medicalRecord.getLearningResult());
			mapRecord.put("friendStatus", medicalRecord.getFriendStatus());
			mapRecord.put("familyStatus", medicalRecord.getFamilyStatus());
			mapRecord.put("aroundReact", medicalRecord.getAroundReact());
			mapRecord.put("feelingHistory", medicalRecord.getFeelingHistory());
			mapRecord.put("reason", medicalRecord.getReason());
			mapRecord.put("solutionInPast", medicalRecord.getSolutionInPast());
			mapRecord.put("problemProcess", medicalRecord.getProblemProcess());	
			mapRecord.put("currentProblem", medicalRecord.getCurrentProblem());
			mapRecord.put("appearanceOfProblem", medicalRecord.getAppearanceOfProblem());
			mapRecord.put("reactHistory", medicalRecord.getReactHistory());
			mapRecord.put("strength", medicalRecord.getStrength());
			mapRecord.put("resource", medicalRecord.getReason());
			mapRecord.put("obstacle", medicalRecord.getObstacle());
			mapRecord.put("result", medicalRecord.getResult());
			mapRecord.put("processing", medicalRecord.getProcessing());
			mapRecord.put("cause", medicalRecord.getCause());
			mapRecord.put("method", medicalRecord.getMethod());
			mapRecord.put("problem", medicalRecord.getProblem());
			mapRecord.put("clientID", medicalRecord.getClient().getClientID());
			result.add(mapRecord);
		}
		
		return result;
	}

	@Override
	public List<MedicalRecord> deleteMedicalRecord(int medicalRecordID) {
		List<MedicalRecord> result = new ArrayList<>();
		MedicalRecord medical = medicalRecordDao.getMedicalRecordByID(medicalRecordID);
		Set<Client> clients = clientDao.getClientByMedicalRecord(medical);
		medicalRecordDao.deleteMedical(medical);
		for(Client client : clients) {
			 result.add((MedicalRecord) medicalRecordDao.getMedicalRecordByClient(client));
		}
	
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordByClient(int clientID) {
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> medicalRecord = new HashMap<>();
		Client client = clientDao.getClientByID(clientID);
		if (client == null) {
			medicalRecord.put("message", "client null");
			result.add(medicalRecord);
			return result;
		}
		List<MedicalRecord> listMedical = medicalRecordDao.getMedicalRecordByClient(client);
		for (MedicalRecord medical : listMedical) {
			List<String> listUser = new ArrayList<>();
			List<UserAccess> userAccess = userAccessDao.getUserAccessByMedicalRecord(medical);
			medicalRecord = new HashMap<>();
			medicalRecord.put("medicalRecordID", medical.getMedicalRecordID());
			medicalRecord.put("createdDate", medical.getCreateDate());
			medicalRecord.put("modifiedDate", medical.getModifiedDate());
			medicalRecord.put("status", medical.getStatus());
			
			
			for (UserAccess u : userAccess) {
				listUser.add(u.getUser().getFirstName());
			}
			medicalRecord.put("manager", listUser);
			result.add(medicalRecord);
		}
		return result;
	}

	

}
