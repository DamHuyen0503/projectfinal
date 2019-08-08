package com.fpt.projectfinal.services.noteprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.daos.noteProcess.NoteProcessDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.utils.ConvertDateTime;
import com.fpt.projectfinal.utils.ConvertTimestamp;
@Service
public class NoteProcessServiceImpl  implements NoteProcessServices{

	@Autowired
	NoteProcessDao noteprocessDao;
	
	@Autowired 
	MedicalRecordDao medicalRecorDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public Map<String, Object> getNoteProcessByMedicalRecordID(int medicalRecordID) {
	
		List<NoteProcess> notes =   noteprocessDao.getNoteProcessByMedicalRecordID(medicalRecordID);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<>();
		for(NoteProcess note:notes) {
			Map<String, Object> mapNote = new HashMap<>();
			mapNote.put("content",note.getContent());
			mapNote.put("endTime", note.getEndTime());
			mapNote.put("evaluation", note.getEvaluation());
			mapNote.put("noteID", note.getNoteID());
			mapNote.put("startTime", note.getStartTime());
			mapNote.put("endTime", note.getEndTime());
			result.add(mapNote);
		}
		map.put("notes",result);
		return map;
	}

	@Override
	public Map<String, Object> addNoteProcess(Map<String, Object> payload) {
		Map<String, Object> result = new HashMap<>();
		
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (username == null) {
			result.put("error", "token null");
			return result;
		}
		Account account = accountDao.getAccountByEmail(username);
		User user = userDao.getUserByAccount(account);
		NoteProcess noteProcess = new NoteProcess();
		if (payload.get("medicalRecordID") == null) {
			result.put("error", "medicalRecordID null");
			return result;
		}
		try {
			MedicalRecord medical = medicalRecorDao.getMedicalRecordByID((int)payload.get("medicalRecordID"));
			noteProcess.setContent((String)payload.get("content"));
			noteProcess.setEndTime(ConvertTimestamp.ConvertDateTime((String) payload.get("endTime")));
			if (payload.get("evaluation") != null) {
				noteProcess.setEvaluation((int)payload.get("evaluation"));
			}
			
			noteProcess.setMedicalRecord(medical);
			noteProcess.setStartTime(ConvertTimestamp.ConvertDateTime((String) payload.get("startTime")));
			noteProcess.setUser(user);
			noteprocessDao.addNoteProcess(noteProcess);
			result.put("noteProcessID", noteProcess.getNoteID());
			result.put("content", noteProcess.getContent());
			result.put("endTime", noteProcess.getEndTime());
			result.put("evaluation", noteProcess.getEvaluation());
			result.put("startTime", noteProcess.getStartTime());
			
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("error", "add fail");
			return result;
		}
	}

	@Override
	public String updateNoteProcess(Map<String, Object> payload) {
		if (payload.get("noteProcessID") == null) {
			return "noteProcessID null";
		}
		
		MedicalRecord medical = new MedicalRecord();
		try {
			NoteProcess noteProcess = noteprocessDao.getNoteProcessByID((int)payload.get("noteProcessID"));
			if (payload.get("medicalRecordID") != null) {
				medical = medicalRecorDao.getMedicalRecordByID((int)payload.get("medicalRecordID"));
			}
			noteProcess.setContent((String)payload.get("content"));
			noteProcess.setStartTime(ConvertTimestamp.ConvertDateTime((String) payload.get("startTime")));
			if (payload.get("evaluation") != null) {
				noteProcess.setEvaluation((int)payload.get("evaluation"));
			}
			noteProcess.setMedicalRecord(medical);
			noteProcess.setEndTime(ConvertTimestamp.ConvertDateTime((String) payload.get("endTime")));
			noteprocessDao.updateNoteProcess(noteProcess);
			return "successful";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
	}

	@Override
	public List<Map<String, Object>> getNoteProcessByUserSendRequest() {
		
		List<Map<String, Object>> result = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (username == null) {
			Map<String, Object> mapNote = new HashMap<>();
			mapNote.put("error", "token null");
			result.add(mapNote);
			return result;
		}
		Account account = accountDao.getAccountByEmail(username);
		User user = userDao.getUserByAccount(account);
		List<NoteProcess> listNoteProcess = noteprocessDao.getNoteProcessByUserSendRequest(user);
		for(NoteProcess noteProcess : listNoteProcess) {
			Map<String, Object> mapNote = new HashMap<>();
			mapNote.put("content", noteProcess.getContent());
			mapNote.put("endTime", noteProcess.getEndTime());
			mapNote.put("evaluation", noteProcess.getEvaluation());
			mapNote.put("noteID", noteProcess.getNoteID());
			mapNote.put("startTime", noteProcess.getStartTime());
		
			result.add(mapNote);
		}
		return result;
	}

}
