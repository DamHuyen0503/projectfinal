package com.fpt.projectfinal.daos.medicalrecord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.MedicalRecord;

public class MedicalRecordDaoImpl implements MedicalRecordDao{
	@Autowired
	SessionFactory session;
	
	@Override
	public void addMedicalRecord(MedicalRecord medicalRecord) {
		this.session.getCurrentSession().save(medicalRecord);
	
	}

	@Override
	public Map<String, Date> getMedicalRecordID(MedicalRecord medicalRecord) {
		
		return null;
	}

	

}
