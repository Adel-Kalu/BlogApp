package com.ga.blogapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ga.blogapp.dao.UserDao;
import com.ga.blogapp.model.User;
import com.ga.blogapp.model.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException{
		User user = dao.findByEmailAddress(emailAddress);
		
		if(user == null)
			throw new UsernameNotFoundException("Not found");
		
		UserDetailsImpl obj = new UserDetailsImpl(user);
		
		return obj;
	}
}
