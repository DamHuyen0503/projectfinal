package com.fpt.projectfinal.services.role;

import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;

public interface RoleService {

	
	
	public List<Role> getAllRole();
	
	public Set<String> getRoleByToken(String Token);
	
	public void updateRole();
}
