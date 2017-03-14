package com.tykj.template.utils;

import java.io.File;

import com.tykj.template.security.utils.SecurityUtils;

public class Constants {

	public static final long USER_ADMIN_ID = 1;
	public static final int USER_STATUS_ENABLE = 1;
	public static final int USER_STATUS_DISABLE = 0;

	public static final int ROLE_DEFAULT_ID = 2;

	public static final String MAIL_PASSWORD_FIND_SUBJECT = "密码找回";
	public static final String MAIL_PASSWORD_FIND_TEMPLATELOCATION = "/mail/password_find_mail.ftl";
	public static final String MAIL_PASSWORD_FIND_LINKURL_PRI = "http://localhost:8080/sys/password/find";

	public static final String PATH_USER_PROFILE = "/static/images/profile";

	public static String getUserProfilePath() {
		String tmpDir = SecurityUtils.getRequest().getServletContext().getRealPath(PATH_USER_PROFILE);
		File file = new File(tmpDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}
}
