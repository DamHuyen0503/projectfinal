package com.fpt.projectfinal.controllers.slider;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Slider;
import com.fpt.projectfinal.services.slider.SliderService;

@CrossOrigin
@RestController
public class SliderController {
	final static Logger logger = LoggerFactory.getLogger(SliderController.class);

	@Autowired
	SliderService sliderService;

	@RequestMapping(value = "/getAllSlider", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllSlider() {
		try {
			List<Slider> list = sliderService.getAllSlider();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/addSlider", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Object> addSlider(@RequestBody Slider slider) {

		try {

			sliderService.addSlider(slider);
			return new ResponseEntity<>("Successful", HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updateSlider", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateSlider(@RequestBody Slider slider) {

		try {
			sliderService.updateSlider(slider);
			return new ResponseEntity<>("Successful", HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author thuy
	 * @param sliderID: Integer
	 * <h4>Thuy code cai nay de xoa slider, theo doc abc. Test case xyz</h4>
	 * @return String "Su..." Thanh cong
	 */
	@RequestMapping(value = "/deleteSlider/{sliderID}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> deleteSlider(@PathVariable("sliderID") Integer sliderID) {
		logger.info("Start delete slider: " + sliderID);
		try {
			sliderService.deleteSlider(sliderID);
			logger.info("Finished delete slider: " + sliderID + " Successful");
			return new ResponseEntity<>("Successful", HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn("Finished delete slider: " + e.getMessage(), e);
			return new ResponseEntity<>("Failed", HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>("No longer exist", HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getSliderByID/{sliderID}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getSliderByID(@PathVariable(value = "sliderID") Integer sliderID ) {
		
		try {
			Slider slider =sliderService.getSliderByID(sliderID);
	        return new ResponseEntity<>(slider, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
