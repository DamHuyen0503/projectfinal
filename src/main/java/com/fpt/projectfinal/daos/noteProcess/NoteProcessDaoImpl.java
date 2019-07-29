package com.fpt.projectfinal.daos.noteProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.services.medicalrecord.MedicalRecordService;
@Repository
@Transactional
public class NoteProcessDaoImpl implements NoteProcessDao{
	@Autowired
	SessionFactory session;
	@Autowired 
	MedicalRecordDao medicalRecorDao;
	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<NoteProcess> getNoteProcessByMedicalRecord(MedicalRecord medicalRecord) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(medicalRecord.getNoteProcess())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<NoteProcess> query = builder.createQuery(NoteProcess.class);
			Root<NoteProcess> root = query.from(NoteProcess.class);
			query.select(root).where(builder.equal(root.get("medicalRecord"), medicalRecord));
			List<NoteProcess> notes =  session.getCurrentSession().createQuery(query).getResultList();
			return notes;
		}
		return   (List<NoteProcess>) medicalRecord.getNoteProcess();
	}

	@Override
	public List<NoteProcess> getNoteProcessByMedicalRecordID(int medicalRecordID) {
		MedicalRecord medicalRecord = medicalRecorDao.getMedicalRecordByID(medicalRecordID);
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(medicalRecord.getNoteProcess())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<NoteProcess> query = builder.createQuery(NoteProcess.class);
			Root<NoteProcess> root = query.from(NoteProcess.class);
			query.select(root).where(builder.equal(root.get("medicalRecord"), medicalRecord));
			List<NoteProcess> notes =  session.getCurrentSession().createQuery(query).getResultList();
			return  notes;
		}
		return    (List<NoteProcess>) medicalRecord.getNoteProcess();
	}

}
