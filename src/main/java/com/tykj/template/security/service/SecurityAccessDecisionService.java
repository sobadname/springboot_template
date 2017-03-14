package com.tykj.template.security.service;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class SecurityAccessDecisionService implements AccessDecisionManager {

	private Logger logger = LoggerFactory.getLogger(SecurityAccessDecisionService.class);

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		logger.info("security access decide, authentication: " + authentication + "; object: " + object
				+ "; config attributes: " + configAttributes);
		if (authentication == null) {
			throw new AccessDeniedException("security access denied");
		}
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> iter = configAttributes.iterator();
		while (iter.hasNext()) {
			ConfigAttribute configAttribute = iter.next();
			String needRole = configAttribute.getAttribute();
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (needRole.trim().equals(authority.getAuthority().trim())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("security access denied");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}