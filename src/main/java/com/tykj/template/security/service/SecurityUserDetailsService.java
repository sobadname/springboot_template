package com.tykj.template.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tykj.template.domain.User;
import com.tykj.template.security.dto.SecurityUser;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

	@Autowired
	private SecurityAuthorityService securityAuthorityService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = securityAuthorityService.getUserByUserName(username);
		logger.info("security query user by username: " + username + ", result: " + user);
		if (user == null) {
			throw new UsernameNotFoundException("security user not found, username: " + username);
		}
		return new SecurityUser(user);
	}

}
