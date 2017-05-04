package com.tykj.template.aop.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tykj.template.aop.RequestLimitException;
import com.tykj.template.aop.annotation.RequestLimit;
import com.tykj.template.security.utils.SecurityUtils;
import com.tykj.template.service.redis.RedisService;

@Aspect
@Order(1)
@Component
public class RequestLimitAspect {

	private Logger logger = LoggerFactory.getLogger(RequestLimitAspect.class);

	@Autowired
	private RedisService redisService;

	@Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
	public void before(JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String ip = SecurityUtils.getIpAddress(request);
		String url = request.getRequestURL().toString();
		String key = "req_limit_" + url + "_" + ip;

		String value = redisService.getValue(key);
		if (StringUtils.hasText(value)) {
			long count=redisService.incrementValue(key);
			if (count >= limit.count()) {
				logger.info(getUsername() + " request url " + url + " limited, ip: " + ip);
				throw new RequestLimitException(limit.count());
			}
		} else {
			redisService.setValue(key, "1", limit.seconds());
		}
	}  

	private String getUsername() {
		UserDetails user = SecurityUtils.getUserDetails();
		if (user == null) {
			return "none";
		} else {
			return user.getUsername();
		}
	}

}
