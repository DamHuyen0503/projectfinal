package com.fpt.projectfinal.services.role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.role.RoleDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;
	
	@Autowired
	AccountDao accDao;
	
	

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

		Set<String> roles = authentication.getAuthorities().stream()
				.map(r -> r.getAuthority()).collect(Collectors.toSet());
		return roles;
	}
	@Override
	public void updateRole() {
		
		
	}



}
