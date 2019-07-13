package com.fpt.projectfinal.controllers.authen;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.services.authentication.AuthenService;

@CrossOrigin
@RestController
@ComponentScan({"com.fpt.projectfinal.controllers"})
public class AuthenController {

	@Autowired
	AuthenService authenService;
	
	
	
	@RequestMapping(value = "/getAllAccount", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Account> getAllAccount() {
		List<Account> list = authenService.getAllAccount();
		return list;
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String addAccount(@RequestBody Map<String, Object> payload) {
		int result = authenService.addAccount(payload);
		if (result==0) {
			return "add fail";
		}
		return "susscessful";
	}
	
	@RequestMapping(value = "/getByEmail", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String getByEmail(@RequestBody Map<String, Object> payload) {
		Account acc = authenService.getAccountByEmail((String)payload.get("email"));
			return acc.getEmail();
	}
}
