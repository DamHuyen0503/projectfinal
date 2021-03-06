package com.fpt.projectfinal.controllers.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.services.client.ClientService;
import com.fpt.projectfinal.services.role.RoleService;

@CrossOrigin
@RestController
public class ClientController {
	final String ROLEADMIN = "ADMIN";
	final String ROLEEXPERT = "EXPERT";
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RoleService roleService;
	
	/*
	 * Create new client. 
	 * 新しいクライアントを作成します。
	 */
	@RequestMapping(value = "/addClient",method = RequestMethod.POST,
			
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> createClient(@RequestBody Map<String, Object> payload,UriComponentsBuilder builder) {
		
		try {
//			clientService.addClient(client);
	        return new ResponseEntity<>(clientService.addClient(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
    }


	/*
	 * Update information customers.
	 * 情報の顧客を更新します。
	 */
	@RequestMapping(value = "/updateClient",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateClient(@RequestBody Client client) {
		try {
			clientService.updateClient(client);
	        return new ResponseEntity<>(client, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	/*
	 * Get information client by clientID.
	 * clientIDで情報クライアントを取得します。
	 */
	@RequestMapping(value = "/getClientByID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getClientByID(@RequestParam  int clientID){
		
		try {
			Map<String, Object> map= clientService.getClientByID(clientID);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	/*
	 * get all customers in the database
	 * データベース内のすべての顧客を取得します。
	 */
	@RequestMapping(value = "/getAllClient", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllClientByAdmin(@RequestParam String sort, String order, int page, String searchString, int status, int expert) {
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			Set<String> roles = roleService.getRoleByToken("");
			for(String role : roles) {
				System.out.println("role:" +role);
				if (role.equals(ROLEADMIN)) {
					return new ResponseEntity<>(clientService.getAllClient(sort, order, page, searchString, status, expert), HttpStatus.OK);
				}
				if (role.equals(ROLEEXPERT)) {
					String username = SecurityContextHolder.getContext().getAuthentication().getName();
					
					
					return new ResponseEntity<>(clientService.getAllClient(sort, order, page, searchString, status, username), HttpStatus.OK);
				}
			}	
			return new ResponseEntity<> ("not found", HttpStatus.BAD_REQUEST);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getAll(@RequestParam String sort, String order, int page, String searchString){
		
		try {
	        return new ResponseEntity<>(clientService.getAll(sort, order, page, searchString), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/countClient", method = RequestMethod.GET,

			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> countClient() {

		try {
			
			return new ResponseEntity<>(clientService.countClient(), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
