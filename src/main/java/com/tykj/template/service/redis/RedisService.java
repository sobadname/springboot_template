package com.tykj.template.service.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private String getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	private void setValue(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	private void deleteValue(String key) {
		stringRedisTemplate.delete(key);
	}

	public void setPasswordFindMail(String key, String value, long seconds) {
		setValue(key, value);
		stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	public String getPasswordFindMail(String key) {
		return getValue(key);
	}

	public void deletePassowrdFindMail(String key) {
		deleteValue(key);
	}

}
