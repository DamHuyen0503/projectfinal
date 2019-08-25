 package com.fpt.projectfinal.daos.medicalrecord;

import java.beans.Expression;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;
@Repository
@Transactional
public class MedicalRecordDaoImpl implements MedicalRecordDao{
	@Autowired
	SessionFactory session;
	
	@Override
	public void addMedicalRecord(MedicalRecord medicalRecord) {
		try {
			this.session.getCurrentSession().save(medicalRecord);	
		} catch (Exception e) {
			e.getMessage();
		}
		
	
	}

	
	@Override
	public boolean checkExitMedicalRecord(int medicalRecordID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
		Root<MedicalRecord> root = query.from(MedicalRecord.class);
		query.select(root).where(builder.equal(root.get("medicalRecordID"), medicalRecordID));
		List<MedicalRecord> medicalRecords = session.getCurrentSession().createQuery(query).getResultList();
		if (medicalRecords.size() > 0) {
			return true;
		} else
			
		
		return false;
	}

	@Override
	public MedicalRecord getMedicalRecordByID(int medicalRecordID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
		Root<MedicalRecord> root = query.from(MedicalRecord.class);
		query.select(root).where(builder.equal(root.get("medicalRecordID"), medicalRecordID));
		List<MedicalRecord> medicals = session.getCurrentSession().createQuery(query).getResultList();
		if(medicals.size()>0){
			
			return medicals.get(0);
		}
		return null;
	}

	@Override
	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		this.session.getCurrentSession().update(medicalRecord);
		
	}

	@Override
	public List<MedicalRecord> getMedicalRecordByDay(Date day, int userID) {
		
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
		Root<MedicalRecord> root = query.from(MedicalRecord.class);
		javax.persistence.criteria.Expression<Date> expression = root.get("createDate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		query.select(root).where(builder.between(expression, day, new Date(day.getTime()+ TimeUnit.DAYS.toMillis(1))));
	
		List<MedicalRecord> medicals = session.getCurrentSession().createQuery(query).getResultList();
		if(medicals.size()>0){
			System.out.println(medicals.size());
			return medicals;
		}
		return null;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalRecord> getMedicalRecordByClient( Client client) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(client.getMedicalRecord())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
			Root<MedicalRecord> root = query.from(MedicalRecord.class);
			query.select(root).where(builder.equal(root.get("client"), client));
			List<MedicalRecord> medicalRecords = session.getCurrentSession().createQuery(query).getResultList();
			return medicalRecords;
		}
		return (List<MedicalRecord>) client.getMedicalRecord();
	}


	@Override
	public void deleteMedical(MedicalRecord medicalRecord) {
		this.session.getCurrentSession().delete(medicalRecord);
		
	}


	@Override
	public List<MedicalRecord> getMedicalRecordByUserAccess(UserAccess userAccess) {
		List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(userAccess.getMedicalRecord())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
			Root<MedicalRecord> root = query.from(MedicalRecord.class);
			query.select(root).where(builder.equal(root.get("userAccess"), userAccess));
			 medicalRecords = session.getCurrentSession().createQuery(query).getResultList();
			return medicalRecords;
		}
		else {
			medicalRecords.add(userAccess.getMedicalRecord());
			return medicalRecords;
		}
	}


	@Override
	public List<MedicalRecord> getMedicalByNoteProcess(NoteProcess note) {
		List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(note.getMedicalRecord())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
			Root<MedicalRecord> root = query.from(MedicalRecord.class);
			query.select(root).where(builder.equal(root.get("noteProcess"), note));
			medicalRecords = session.getCurrentSession().createQuery(query).getResultList();
			return medicalRecords;
		}
		else {
			medicalRecords = new ArrayList<>();
			medicalRecords.add(note.getMedicalRecord());
			return medicalRecords;
		}
	}


	@Override
	public List<MedicalRecord> getAll() {
		return this.session.getCurrentSession().createQuery("from MedicalRecord").list();
	}

	

}
