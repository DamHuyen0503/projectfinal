package com.fpt.projectfinal.services.slider;

import java.util.List;

import com.fpt.projectfinal.models.Slider;

public interface SliderService {
	
	/*
	 * create a slider.
	 * スライダーを作成します。
	 */
	public void addSlider(Slider slider) throws Exception;

	/*
	 * Get all the sliders.
	 * すべてのスライダーを取得します。
	 */
	public List<Slider> getAllSlider();
	
	/*
	 * update a slider.
	 * スライダーを更新します。
	 */
	public void updateSlider(Slider slider);
	
	/*
	 * delete a slider.
	 * スライダーを削除します。
	 */
	public void deleteSlider(Integer sliderID);
	
	/*
	 * get the slider with ID
	 * IDのスライダーを取得します
	 */
	public Slider getSliderByID(Integer sliderID);
}
