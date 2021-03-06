package com.tykj.template.service.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public void setValue(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	public void setValue(String key, String value, long seconds) {
		setValue(key, value);
		stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	public void deleteValue(String key) {
		stringRedisTemplate.delete(key);
	}

	public long incrementValue(String key) {
		return stringRedisTemplate.opsForValue().increment(key, 1);
	}

}
