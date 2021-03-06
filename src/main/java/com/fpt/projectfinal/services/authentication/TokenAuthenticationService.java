package com.fpt.projectfinal.services.authentication;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.auth0.json.mgmt.users.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.user.UserDaoImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class TokenAuthenticationService {
	
	@Autowired
	UserDao userDao ; 
	
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
    
    static final String SECRET = "ThisIsASecret";
     
    static final String TOKEN_PREFIX = "Bearer";
     
    static final String HEADER_STRING = "Authorization";
 
    public static void addAuthentication(HttpServletResponse res, String username, List<String> roles)
			throws IOException {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).claim("role", roles).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		res.setContentType("application/json");

	}

 
    public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();
			@SuppressWarnings("unchecked")
			List<String> r = (List<String>) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().get("role");
			
			List<GrantedAuthority> grantlist = new ArrayList<>();
			if (r != null) {
				for (String role : r) {
					// ROLE_USER, ROLE_ADMIN,..
					
					GrantedAuthority authority = new SimpleGrantedAuthority((String)role);
					grantlist.add(authority);

				}
			}

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, grantlist) : null;
		}
		return null;
	}
    
    @Autowired
    public TokenAuthenticationService() {
    }
}