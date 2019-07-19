package com.fpt.projectfinal.controllers.role;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.services.role.RoleService;

@CrossOrigin
@RestController
@ComponentScan({"com.fpt.projectfinal.controllers"})
public class RoleController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	RoleService roleService; 
	
	@RequestMapping(value = "/getAllRole", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Role> getAllAccount() {
		List<Role> list = roleService.getAllRole();
		return list;
	}
	
	@RequestMapping(value = "/getRoleByToken", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getRoleByToken(){
		try {
			Set<String> map= roleService.getRoleByToken("");
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
}
