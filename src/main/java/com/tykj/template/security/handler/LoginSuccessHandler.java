package com.tykj.template.security.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.tykj.template.dto.PermissionDto;
import com.tykj.template.security.dto.SecurityUser;
import com.tykj.template.security.service.SecurityAuthorityService;
import com.tykj.template.security.utils.SecurityUtils;
import com.tykj.template.utils.WebUtils;

@Service
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Autowired
	private SecurityAuthorityService securityAuthorityService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		logger.info("security user [" + user.getLoginName() + "] login from ip: " + WebUtils.getIpAddress(request));
		Collection<PermissionDto> perms = securityAuthorityService.getUserPermissionByUserName(user.getLoginName());
		request.getSession().setAttribute(SecurityUtils.SESSION_USER_PERMISSION, perms);
		logger.info("security user [" + user.getLoginName() + "] permission list: " + perms);
		securityAuthorityService.updateUserLastLoginTime(user.getLoginName());
		logger.info("security user [" + user.getLoginName() + "] update last login time: " + perms);
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
