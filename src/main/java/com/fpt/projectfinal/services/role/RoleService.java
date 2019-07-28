package com.fpt.projectfinal.services.role;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;

public interface RoleService {

	
	
	public List<Role> getAllRole();
	
	public Set<String> getRoleByToken(String Token);
	
	public String updateRole(Map<String, Object> payload);
	
	public Set<Role> getRoleByID(String RoleName);
}
