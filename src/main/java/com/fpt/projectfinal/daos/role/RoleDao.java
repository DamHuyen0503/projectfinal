package com.fpt.projectfinal.daos.role;

import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;

public interface RoleDao {
	public Set<Role> getRoleByAcc(Account acc);
	
	/*
	 * Get all roles in the database.
	 * データベース内のすべてのロールを取得します。
	 */
	public List<Role> getAllRole();
	
	/*
	 * update information of the role.
	 * ロールの情報を更新します。
	 */
	public void updateRole(Role role);
	
	/*
	 * Get the role information by name.
	 * 名前でロール情報を取得します。
	 */
	public Set<Role> getRoleByName(String roleName);
	

	/*
	 * Get the role information by ID.
	 * IDでロール情報を取得します。
	 */
	public Role getRoleByID(int roleID);
	
}
