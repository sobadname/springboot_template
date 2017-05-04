package com.tykj.template.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tykj.template.security.utils.SecurityUtils;

@Aspect
@Order(10)
@Component
public class ServiceLogAspect {

	private Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.tykj.template.service..*.*(..))")
	public void servicelog() {

	}

	@Before("servicelog()")
	public void before(JoinPoint joinPoint) {
		startTime.set(System.currentTimeMillis());
	}

	@AfterThrowing(throwing = "e", pointcut = "servicelog()")
	public void afterThowing(JoinPoint joinPoint, Exception e) {
		logger.error((System.currentTimeMillis() - startTime.get().longValue()) + "ms, " + getUsername()
				+ " execute service: " + joinPoint.getSignature().toLongString() + "; args: "
				+ Arrays.toString(joinPoint.getArgs()) + " exception: " + e.getMessage());
	}

	@AfterReturning(returning = "ret", pointcut = "servicelog()")
	public void afterReturn(JoinPoint joinPoint, Object ret) {
		logger.info((System.currentTimeMillis() - startTime.get().longValue()) + "ms, " + getUsername()
				+ " execute service: " + joinPoint.getSignature().toLongString() + "; args: "
				+ Arrays.toString(joinPoint.getArgs()) + " return: " + ret);
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
