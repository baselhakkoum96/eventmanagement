package com.example.eventmanagment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.eventmanagment.domain.User;
import com.example.eventmanagment.domain.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	

	public UserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		
		// Find the user from the database by their username
		User currentuser = userRepository.findByUsername(username);
		// Create a new UserDetails object for the authenticated user, with their username, password, and role
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currentuser.getPassword(), 
        		AuthorityUtils.createAuthorityList(currentuser.getRole()));
        return user;
	}


	
}
	
	
	