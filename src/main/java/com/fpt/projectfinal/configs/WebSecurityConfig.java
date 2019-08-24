package com.fpt.projectfinal.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fpt.projectfinal.services.authentication.UserDetailServiceImpl;



@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailServiceImpl userDetailsService;
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addExposedHeader("Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                "Content-Type, Access-Control-Request-Method, Custom-Filter-Header");
        config.addAllowedHeader("*");
        config.addExposedHeader(HttpHeaders.AUTHORIZATION);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
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
		 http.cors().and().csrf().disable().authorizeRequests()
	                // No need authentication.
	                // .antMatchers("/").permitAll()
	                .antMatchers(HttpMethod.POST,"/login").permitAll() 
	                .antMatchers(HttpMethod.GET, "/uploadOneFile/**").permitAll() 
	                .antMatchers(HttpMethod.POST,"/addAccount").permitAll()//
	                .antMatchers(HttpMethod.GET, "/getDetailPost/**").permitAll()
	                .antMatchers(HttpMethod.GET, "/CountCategory").permitAll()
	                .antMatchers(HttpMethod.GET, "/getAllSlider").permitAll()
	                .antMatchers(HttpMethod.GET, "/getAllCategory").permitAll()
	                .antMatchers(HttpMethod.GET, "/getAllTag").permitAll()
	                .antMatchers(HttpMethod.POST,"/addSubscriber").permitAll() 
	                .antMatchers(HttpMethod.GET, "/getPostNew").permitAll()
	                .antMatchers(HttpMethod.GET, "/addContact").permitAll()// bug
	                .antMatchers(HttpMethod.GET, "/getPostsByString/**").permitAll()
	                .antMatchers(HttpMethod.GET, "/getPostsByCategory/**").permitAll()
	                .antMatchers(HttpMethod.GET, "/getPostsByAuthor/**").permitAll()
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
