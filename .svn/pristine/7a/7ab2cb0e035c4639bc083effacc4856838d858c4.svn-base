package com.tykj.template.test.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tykj.template.service.mail.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MailTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private MailService mailService;

	@Test
	public void test() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "liao");
		map.put("submit_time", new Date());
		map.put("link_url", "http://locahost:8080/passowrd/find/");
		mailService.sendTemplateMail("350942755@qq.com", "test mail", "/mail/password_find_mail.ftl", map);
		Thread.sleep(10000L);
	}

}
