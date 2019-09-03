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
import com.fpt.projectfinal.models.User;
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
	 try {
		 Map<String, Object> mapOutput = new HashMap<String, Object>();
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			
			Account acc = accountDao.getAccountByEmail(username);
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setCreateDate(new Date());
			if (payload.get("status") == null) {
				mapOutput.put("error", "status null");
				return mapOutput;
			}
			if ((int)payload.get("status") <0 ||(int)payload.get("status") >4 ) {
				mapOutput.put("error", "status invalid");
				return mapOutput;
			}
			if (payload.get("clientID") == null) {
				mapOutput.put("error", "clientID null");
				return mapOutput;
			}
			Client client = clientDao.getClientByID((int) payload.get("clientID"));
			if (client == null) {
				mapOutput.put("error", "client not found");
				return mapOutput;
			}
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
			
			
			medicalRecord.setClient(client);
			Set<NoteProcess> notes = new HashSet<>();
			ArrayList<Object> note_list = (ArrayList<Object>) payload.get("notes");
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
					e.printStackTrace();
				}
			}
			medicalRecord.setNoteProcess(notes);
				medicalRecordDao.addMedicalRecord(medicalRecord);
				mapOutput.put("medicalRecordID", medicalRecord.getMedicalRecordID());
				mapOutput.put("createdDate", medicalRecord.getCreateDate());
			return mapOutput;
	 } catch (Exception e) {
		 Map<String, Object> mapOutput = new HashMap<String, Object>();
		 mapOutput.put("error", e.getMessage());
			return mapOutput;
	}
		
	}

	@Override
	public Map<String, Object> getMedicalRecordByID(int id) {
		Map<String, Object> mapRecord = new HashMap<>();
		if (id <=0) {
			mapRecord.put("error", "id invalid");
			return mapRecord;
		}
		
		try {
			MedicalRecord medicalRecord = medicalRecordDao.getMedicalRecordByID(id);
			if (medicalRecord == null) {
				mapRecord.put("message", "not found");
				return mapRecord;
			}
		
			if (id == 0) {
				mapRecord.put("error", "medicalRecord ID null");
				return null;
			}
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
			mapRecord.put("status", medicalRecord.getStatus());
			mapRecord.put("clientID", medicalRecord.getClient().getClientID());
			return mapRecord;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			mapRecord.put("error", e.getMessage());
			return mapRecord;
		}
		
	}

	@Override
	public String updateMedicalRecord(Map<String, Object> payload) throws Exception {
		@SuppressWarnings("unused")
		Map<String, Object> mapOutput = new HashMap<String, Object>();
		
	
		try {
			if (payload.get("medicalRecordID") == null) {
				
				return "medicalRecordID null";
			}
			if ((int) payload.get("medicalRecordID") == 0) {
				return "medicalRecordID invalidate";
			}
			MedicalRecord medicalRecord = medicalRecordDao.getMedicalRecordByID((int) payload.get("medicalRecordID"));
			if (medicalRecord == null)
			{
				return "medicalRecord not found";
			}
			medicalRecord.setModifiedDate(new Date());
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

			if (payload.get("clientID") == null) {
				return "clientID null";
			}
			Client client = clientDao.getClientByID((int) payload.get("clientID"));
			if (client == null) {
				return "client not found";
			}
			medicalRecord.setClient(client);
			
			
			Set<NoteProcess> notes = new HashSet<>();
			ArrayList<Object> note_list = (ArrayList<Object>) payload.get("notes");
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account acc = accountDao.getAccountByEmail(username);
			for (Object result : note_list) {
				Map<String, Object> resultmap = (Map) result;
				
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
				
				
			}
			medicalRecord.setNoteProcess(notes);
			medicalRecordDao.updateMedicalRecord(medicalRecord);
			return "successful";
			
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
		
	}

	@Override
	public List<Map<String, Object>> getMedicalRecordByDay(Date day) {
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String,  Object> mapMedical = new HashMap<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (username == null) {
			mapMedical.put("error", "token null");
			return result;
		}
		Account account = accountDao.getAccountByEmail(username);
		User user = userDao.getUserByAccount(account);
		
		if (day == null) {
			mapMedical.put("error", "date null");
			result.add(mapMedical);
			return result;
		}
		List<MedicalRecord> medicalRecords = medicalRecordDao.getMedicalRecordByDay(day, user.getUserID());
		if (medicalRecords == null) {
			mapMedical.put("message", "not have medicalRecord in date");
			result.add(mapMedical);
			return result;
		}
		try {
			for (MedicalRecord medicalRecord : medicalRecords) {
				List<UserAccess> userAccess = userAccessDao.getUserAccessByMedicalRecord(medicalRecord);
				if (user.getUserID() == userAccess.get(0).getUser().getUserID()) {
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
				
			}
			
			return result;
		}
		catch (Exception e) {
			Map<String,  Object> mapError = new HashMap<>();
			mapError.put("error", e.getMessage());
			result.add(mapMedical);
			return result;
		}
		
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
		if (clientID <= 0) {
			medicalRecord.put("message", "client invalidate");
			result.add(medicalRecord);
			return result;
		}
		
		Client client = clientDao.getClientByID(clientID);
		if (client == null) {
			medicalRecord.put("message", "client null");
			result.add(medicalRecord);
			return result;
		}
		
		List<MedicalRecord> listMedical = medicalRecordDao.getMedicalRecordByClient(client);
		if (listMedical == null) {
//			medicalRecord.put("message", "medicalRecord not found");
			result.add(medicalRecord);
			return result;
		}
		for (MedicalRecord medical : listMedical) {
			Map<String, Object> listUser = new HashMap<>();
			List<Object> listManager = new ArrayList<>();
			List<UserAccess> userAccess = userAccessDao.getUserAccessByMedicalRecord(medical);
			medicalRecord = new HashMap<>();
			medicalRecord.put("medicalRecordID", medical.getMedicalRecordID());
			medicalRecord.put("createdDate", medical.getCreateDate());
			medicalRecord.put("modifiedDate", medical.getModifiedDate());
			medicalRecord.put("status", medical.getStatus());
			
			for (UserAccess u : userAccess) {
				listUser = new HashMap<>();
				listUser.put("fullName", client.getFullName());
				listUser.put("userAccessID", u.getUserAccessID());
				listUser.put("finishedDate", u.getFinishedDate());
				listUser.put("startedDate", u.getStartedDate());
				listUser.put("status", u.getStatus());
				listManager.add(listUser);
			}
			medicalRecord.put("manager", listManager);
			result.add(medicalRecord);
		}
		return result;
	}

	@Override
	public List<MedicalRecord> getMedicalRecordByUserAccess(UserAccess userAccess) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
