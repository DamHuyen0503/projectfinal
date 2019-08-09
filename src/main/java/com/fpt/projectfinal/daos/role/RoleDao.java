package com.fpt.projectfinal.daos.role;

import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;

public interface RoleDao {
	public Set<Role> getRoleByAcc(Account acc);
	
	public List<Role> getAllRole();
	
	public void updateRole(Role role);
	
	public Set<Role> getRoleByName(String roleName);
	

	
	public Role getRoleByID(int roleID);
	
}
