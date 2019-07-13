package com.fpt.projectfinal.services.medicalrecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.client.ClientDao;
import com.fpt.projectfinal.daos.user.UserDAO;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
	@Autowired
	UserDAO userDao;

	@Autowired
	ClientDao clientDao;

	@Override
	public void addMedicalRecord(Map<String, Object> payload) {
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		User u = userDao.getUserByEmail(username) ;
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
		for (Object note : note_list) {
			Map<String, Object> notemap = (Map) note;

			

//			notes.add(new NoteProcess(startTime, endTime, (String) notemap.get("content"),
//					(int) notemap.get("evaluation")));
		}
		medicalRecord.setNodeProcess(notes);

	}

}
