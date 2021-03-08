package com.ga.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		this.disableLocalConfigureAuthenticationBldr = true;
		auth.userDetailsService(userDetailsService);
	}
	
	
	// Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/author/add", "/article/add").hasAnyRole("ADMIN","USER")
			.antMatchers("/author/edit", "/article/edit").hasAnyRole("ADMIN","USER")
			.antMatchers("/author/delete", "/article/delete").hasRole("ADMIN")
			
			.and()
			.formLogin().loginPage("/login")
			
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true);
	}
	

}
