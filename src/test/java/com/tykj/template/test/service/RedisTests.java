package com.tykj.template.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tykj.template.service.redis.RedisService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RedisTests {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private RedisService redisService;

	@Test
	public void test() throws Exception {
		String key = UUID.randomUUID().toString();
		assertThat(key).isNotEmpty().hasSize(36);

		String value = redisService.getPasswordFindMail(key);
		assertThat(value).isNull();;

		redisService.setPasswordFindMail(key, "test1", 20);
		value = redisService.getPasswordFindMail(key);
		assertThat(value).isNotEmpty().isEqualTo("test1");

		redisService.setPasswordFindMail(key, "test2", 20);
		value = redisService.getPasswordFindMail(key);
		assertThat(value).isNotEmpty().isEqualTo("test2");

		Thread.sleep(22000);
		value = redisService.getPasswordFindMail(key);
		assertThat(value).isNull();
	}

}
