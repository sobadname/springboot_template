package com.tykj.template.utils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtils extends org.springframework.util.StringUtils {

	public static void main(String[] args) {
		System.out.println(getUUIDWithOutLine());
	}

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static String getUUIDWithOutLine() {
		return getUUID().replaceAll("-", "");
	}

	public static String getLoginUrlFromEmail(String email) {
		return "mail." + email.substring(email.lastIndexOf("@") + 1);
	}

	public static boolean isTelecomMobile(String tel) {
		return Pattern.compile("^((133)|(153)|(177)|(18[0,9]))\\d{8}$").matcher(tel).matches();
	}

	public static String getFixdNumber(int num, int fix) {
		String result = "";
		int len = String.valueOf(num).length();
		if (len >= fix) {
			result = String.valueOf(num);
		} else {
			int num_fix = fix - len;
			for (int i = 0; i < num_fix; i++) {
				result += "0";
			}
			result += String.valueOf(num);
		}
		return result;
	}

	public static String getRandomString(int len) {
		final int maxNum = 36;
		int i = 0;
		int count = 0;
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer buff = new StringBuffer("");
		Random r = new Random();
		while (count < len) {
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				buff.append(str[i]);
				count++;
			}
		}
		return buff.toString();
	}

	public static boolean isArrContainsStr(String[] arr, String str) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		for (String s : arr) {
			if (s != null && s.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

	public static String getFileNameExt(String fileName) {
		if (fileName == null || !fileName.contains(".")) {
			return "";
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public static String removeFileNameExt(String fileName) {
		if (fileName == null || !fileName.contains(".")) {
			return fileName;
		}
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
}
