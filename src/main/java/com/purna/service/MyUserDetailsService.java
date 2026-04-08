package com.purna.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.purna.model.UserObj;
import com.purna.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserObj user = userRepository.findByEmail(email);
		if(user == null) throw new UsernameNotFoundException("no such user");
		
		return User.builder()
		.username(email)
		.password(user.getPassword())
		.roles("USER").build();
	}
}
