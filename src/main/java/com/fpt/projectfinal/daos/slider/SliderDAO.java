package com.fpt.projectfinal.daos.slider;

import java.util.List;

import com.fpt.projectfinal.models.Slider;

public interface SliderDAO {
	
	public void addSlider(Slider slider) throws Exception;

	public List<Slider> getAllSlider();
	
	public void updateSlider(Slider slider);
	
	public void deleteSlider(Integer sliderID);
	
	public Slider byId(Integer sliderID);
	
	public Slider getSliderByID(Integer sliderID);
}
