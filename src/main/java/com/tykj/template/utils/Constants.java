package com.tykj.template.utils;

import java.io.File;

import com.tykj.template.security.utils.SecurityUtils;

public class Constants {

	public static final long USER_ADMIN_ID = 1;
	public static final int USER_STATUS_ENABLE = 1;
	public static final int USER_STATUS_DISABLE = 0;
	public static final int ROLE_STATUS_ENABLE = 1;
	public static final int ROLE_STATUS_DISABLE = 0;

	public static final int STATUS_ENABLE = 1;
	public static final int STATUS_DISABLE = 0;

	public static final int ROLE_DEFAULT_ID = 2;

	public static final String MAIL_PASSWORD_FIND_SUBJECT = "密码找回";
	public static final String MAIL_PASSWORD_FIND_TEMPLATELOCATION = "/mail/password_find_mail.ftl";
	public static final String MAIL_PASSWORD_FIND_LINKURL_PRI = "http://localhost:8080/sys/password/find";

	public static final String PATH_USER_PROFILE = "/static/images/profile";
	public static final String PATH_ITEM_ICON = "/static/images/dsyg/item_icon";
	public static final String PATH_ITEM_PIC_ICON = "/static/images/dsyg/item_pic";
	public static final String PATH_ITEM_CONTENT_ICON = "/static/images/dsyg/item_content_pic";

	public static String getPath(String path) {
		String tmpDir = SecurityUtils.getRequest().getServletContext().getRealPath(path);
		File file = new File(tmpDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}
}
