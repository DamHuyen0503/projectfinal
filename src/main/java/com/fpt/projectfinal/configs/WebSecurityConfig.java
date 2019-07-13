package com.fpt.projectfinal.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fpt.projectfinal.services.authentication.UserDetailServiceImpl;




@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailServiceImpl userDetailsService;
    
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests()
	                // No need authentication.
	                .antMatchers("/").permitAll() //
	                .antMatchers(HttpMethod.POST, "/login").permitAll() //
	                .antMatchers(HttpMethod.POST, "/addAccount").permitAll()
	                
	                // Need authentication.
	                .anyRequest().authenticated()
	                //
	                .and()
	                //
	                // Add Filter 1 - JWTLoginFilter
	                //
	                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                        UsernamePasswordAuthenticationFilter.class)
	                //
	                // Add Filter 2 - JWTAuthenticationFilter
	                //
	                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    }
	 
	   
	 
	  
	    
	  
}
