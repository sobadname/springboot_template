package com.tykj.template.security.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityUtils {

	public static final String SESSION_USER_PERMISSION = "user_permission";

	public static final Long PERMISSION_ROOT_ID = 0L;

	public static PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	public static String encoderPassword(String str) {
		return getPasswordEncoder().encode(str);
	}

	public static boolean matchPassword(String user_password, String db_password) {
		return getPasswordEncoder().matches(user_password, db_password);
	}

	public static HttpSession getSession() {
		if (getRequest() == null) {
			return null;
		}
		return getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		return request;
	}
}
