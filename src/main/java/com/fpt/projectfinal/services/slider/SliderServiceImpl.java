package com.fpt.projectfinal.services.slider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.projectfinal.daos.slider.SliderDAO;
import com.fpt.projectfinal.models.Slider;

@Service
public class SliderServiceImpl implements SliderService {
	
	@Autowired
	SliderDAO sliderDAO;
	
	@Override
	public void addSlider(Slider slider) throws Exception {
//			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
//			CriteriaQuery<Slider> query = builder.createQuery(Slider.class);
//			Root<Slider> root = query.from(Slider.class);
			List<Slider> listSlider = sliderDAO.getAllSlider();
			if(listSlider.size() < 5) {
				if(slider.getImage( )== null || slider.getLink() == null || slider.getName() == null) {
					throw new NullPointerException("Image, Link, Name, không được trống");
				}else {
					slider.setCreatedDate(new Date());
					slider.setModifiedDate(new Date());
					sliderDAO.addSlider(slider);
				}
	
			}else {
				throw new NullPointerException("Slider chỉ lưu được 5 cái");
			}
    }

	@Override
	public List<Slider> getAllSlider() {
		List<Slider> list = sliderDAO.getAllSlider();

		return list;
	}

	@Override
	public void updateSlider(Slider slider) {
		if(slider.getImage( )== null || slider.getLink() == null || slider.getName() == null) {
			throw new NullPointerException("Image, Link, Name, không được trống");
		}else {
			sliderDAO.updateSlider(slider);
		}
	}

	@Override
	public void deleteSlider(Integer sliderID) {
		sliderDAO.deleteSlider(sliderID);
	}

	@Override
	public Slider getSliderByID(Integer sliderID) {
		return this.sliderDAO.getSliderByID(sliderID);
	}

}
