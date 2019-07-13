package com.fpt.projectfinal.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.fpt.projectfinal.services.authentication.TokenAuthenticationService;


public class JWTAuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 System.out.println("JWTAuthenticationFilter.doFilter");
         
	        Authentication authentication = TokenAuthenticationService
	                .getAuthentication((HttpServletRequest) request);
	         
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	         
	        chain.doFilter(request, response);
		
	}
}
