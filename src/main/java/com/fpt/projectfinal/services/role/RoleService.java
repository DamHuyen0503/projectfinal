package com.fpt.projectfinal.services.role;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;

public interface RoleService {

	
	/*
	 * Get all roles in the database.
	 * データベース内のすべてのロールを取得します。
	 */
	public List<Role> getAllRole();
	
	/*
	 * Get all user role from the token.
	 * トークンからすべてのユーザーロールを取得します。
	 */
	public Set<String> getRoleByToken(String Token);
	
	/*
	 * update information of the role.
	 * ロールの情報を更新します。
	 */
	public String updateRole(Map<String, Object> payload);
	
	

	/*
	 * Get the role information by ID.
	 * IDでロール情報を取得します。
	 */
	public Set<Role> getRoleByID(String RoleName);
}
