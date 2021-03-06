package com.fpt.projectfinal.configs;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fpt.projectfinal.services.authentication.TokenAuthenticationService;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
	 String username = request.getParameter("email");
    String password = request.getParameter("password");
    
    System.out.printf("JWTLoginFilter.attemptAuthentication: username/password= %s,%s", username, password);
    System.out.println();

    return getAuthenticationManager()
            .authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		System.out.println("JWTLoginFilter.successfulAuthentication:");
		List<String> roles = authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		// Write Authorization to Headers of Response.
		TokenAuthenticationService.addAuthentication(response, authResult.getName(), roles);

		String authorizationString = response.getHeader("Authorization");
		
	
		
		System.out.println("Authorization String=" + authorizationString);
	}
}

