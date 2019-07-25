package com.fpt.projectfinal.daos.slider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Slider;
import com.fpt.projectfinal.models.Test;

@Repository
@Transactional
public class SliderDAOImpl implements SliderDAO {

	@Autowired
	private SessionFactory session;
	
	
	@Override
	public List<Slider> getAllSlider() {
		return this.session.getCurrentSession().createQuery("from Slider").list();
		
	}

	@Override
	public void updateSlider(Slider slider) {
		slider.setModifiedDate(new Date());
		this.session.getCurrentSession().update(slider);
	}

	@Override
	public void addSlider(Slider slider) throws NullPointerException {
		try {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Slider> query = builder.createQuery(Slider.class);
			Root<Slider> root = query.from(Slider.class);
			List<Slider> listSlider = session.getCurrentSession().createQuery(query).getResultList();
			if(listSlider.size() < 5) {
				if(slider.getImage( )== null || slider.getLink() == null || slider.getName() == null) {
					throw new NullPointerException("Image, Link, Name, not null");
				}else {
					slider.setCreatedDate(new Date());
					slider.setModifiedDate(new Date());
					this.session.getCurrentSession().save(slider);
				}
				
			}else {
				throw new NullPointerException("Slider save only 5");
			}
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public void deleteSlider(Integer sliderID) throws EntityNotFoundException {
			Object predelete = this.session.getCurrentSession().load(Slider.class, sliderID);
			this.session.getCurrentSession().delete((Slider)predelete);
	}

	@Override
	public Slider byId(Integer sliderID) {
		return (Slider)  this.session.getCurrentSession().load(Slider.class, sliderID);
	}

}
