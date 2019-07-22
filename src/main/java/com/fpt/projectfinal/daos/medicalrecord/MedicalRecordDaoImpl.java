package com.fpt.projectfinal.daos.medicalrecord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.Test;
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
	public Map<String, Date> getMedicalRecordID(MedicalRecord medicalRecord) {
		
		return null;
	}

	@Override
	public boolean checkExitMedicalRecord(String medicalRecordID) {
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
	public MedicalRecord getMedicalRecordByID(String medicalRecordID) {
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
	public List<MedicalRecord> getMedicalRecordByDay(Date day) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
		Root<MedicalRecord> root = query.from(MedicalRecord.class);
		query.select(root).where(builder.equal(root.get("createDate"), day));
		List<MedicalRecord> medicals = session.getCurrentSession().createQuery(query).getResultList();
		if(medicals.size()>0){
			
			return medicals;
		}
		return null;
	}

	

}
