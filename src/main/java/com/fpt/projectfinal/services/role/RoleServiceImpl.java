package com.fpt.projectfinal.services.role;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.role.RoleDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.User;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;
	
	@Autowired
	AccountDao accDao;
	
	@Autowired
	UserDao userDao;
	

	@Override
	public List<Role> getAllRole() {
		List<Role> listRole = roleDao.getAllRole();
		for(Role r: listRole) {
			r.setAccount(accDao.getAccountByRole(r));
		}
		return listRole;
	}
	@Override
	public Set<String> getRoleByToken(String Token) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = new HashSet<>();
		roles = authentication.getAuthorities().stream()
				.map(r -> r.getAuthority()).collect(Collectors.toSet());
		if (roles.isEmpty()) {
			roles.add("role not found");
			
		}
		return roles;
	}

	@Override
	public Set<Role> getRoleByID(String roleName) {
		@SuppressWarnings("unused")
		Set<Role> role = new HashSet<>();
		role = roleDao.getRoleByName(roleName);
		return null;
	}
	@Override
	public String updateRole(Map<String, Object> payload) {
		return null;
		
		}
	

}
