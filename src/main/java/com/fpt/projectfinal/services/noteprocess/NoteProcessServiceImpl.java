package com.fpt.projectfinal.services.noteprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.daos.noteProcess.NoteProcessDao;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
@Service
public class NoteProcessServiceImpl  implements NoteProcessServices{

	@Autowired
	NoteProcessDao noteprocessDao;
	
	@Autowired 
	MedicalRecordDao medicalRecorDao;
	
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
			mapNote.put("endTime", note.getUser().getUserID());
			result.add(mapNote);
		}
		map.put("notes",result);
		return map;
	}

}
