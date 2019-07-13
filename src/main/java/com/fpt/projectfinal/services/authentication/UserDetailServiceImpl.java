package com.fpt.projectfinal.services.authentication;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.models.Account;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	AccountDao accountDAO;

	
	
	 
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account acc = this.accountDAO.getAccountByEmail(email);
		if (acc==  null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		
		  List<String> roleNames = new ArrayList<>();
		  roleNames.add("ROLE_ADMIN");
		  roleNames.add("ROLE_USER");
		  List<GrantedAuthority>grantlist = new ArrayList<>();
		  if (roleNames != null) {
	            for (String role : roleNames) {
	                // ROLE_USER, ROLE_ADMIN,..
	                GrantedAuthority authority = new SimpleGrantedAuthority(role);
	                grantlist.add(authority);
	            }
		  }
		  UserDetails userDetails = (UserDetails) new User(acc.getEmail(), //
	               this.passwordEncoder().encode(acc.getPassword()), grantlist);
		  return userDetails;
	}
	
}
