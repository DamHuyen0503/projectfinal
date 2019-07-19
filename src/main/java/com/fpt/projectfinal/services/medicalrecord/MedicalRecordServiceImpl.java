package com.fpt.projectfinal.services.medicalrecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.jni.Mmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.client.ClientDao;
import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.daos.noteProcess.NoteProcessDao;
import com.fpt.projectfinal.daos.user.UserDAO;
import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Result;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.utils.ConvertDateTime;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
	@Autowired
	MedicalRecordDao medicalRecordDao;

	@Autowired
	ClientDao clientDao;
	
	@Autowired
	NoteProcessDao noteProcessDao;

	@Override
	public Map<String, Object> addMedicalRecord(Map<String, Object> payload) {
		Map<String, Object> mapOutput = new HashMap<String, Object>();
		
		MedicalRecord medicalRecord = new MedicalRecord();

		medicalRecord.setMedicalRecordID((String) payload.get("medicalRecordID"));
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
		for (Object result : note_list) {
			Map<String, Object> resultmap = (Map) result;
			try {
				notes.add(new NoteProcess(ConvertDateTime.ConvertDate((String)resultmap.get("startTime")), ConvertDateTime.ConvertDate((String)resultmap.get("endTime")), (String)resultmap.get("content"), (int)resultmap.get("evaluation")));
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
	public Map<String, Object> getMedicalRecordByID(String id) {
		if (id == null) {
			return null;
		}
		MedicalRecord medicalRecord = medicalRecordDao.getMedicalRecordByID(id);
//		List<NoteProcess> noteP = noteProcessDao.getNoteProcessByMedicalRecord(medicalRecord);
		Map<String, Object> mapRecord = new HashMap<>();
//		medicalRecord.setNoteProcess(new HashSet(noteP));
		
		
		
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
		
//		Set<Client> client = clientDao.getClientByMedicalRecord(medicalRecord);
//		if (client.size() >0) {
//			mapRecord.put("clientID", ((Client) client).getClientID());
//		}
//		else {
//			System.out.println("client null");
//		}
		
//		List<Map<String, Object>> notes = new ArrayList<>();
//		for (NoteProcess note : medicalRecord.getNoteProcess()) {
//			Map<String, Object> mapNote = new HashMap();
//			mapNote.put("noteID", note.getNoteID());
//			mapNote.put("startTime", note.getStartTime());
//			mapNote.put("endTime", note.getEndTime());
//			mapNote.put("content", note.getContent());
//			mapNote.put("evaluation", note.getEvaluation());
//			notes.add(mapNote);
//		}
//		mapRecord.put("notes", notes);
		return mapRecord;
	}

}
