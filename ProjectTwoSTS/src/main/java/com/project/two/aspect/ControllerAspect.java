package com.project.two.aspect;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAspect {
	// Use advice for all methods in the controller layer that are not getters or setters
	private static final String TARGET =
			"execution(* com.project.two.controller.*.*(..)) && !(execution(* get*(..)) || execution(* set*(..)))";
	
	private static final Logger LOGGER = Logger.getLogger(ControllerAspect.class);
	
	@Before(TARGET)
	public void beforeTask(JoinPoint jp) {
		LOGGER.info("running " + jp.getSignature());
	}
	
	@AfterReturning(pointcut=TARGET)
	public void afterTaskReturn(JoinPoint jp) {
		LOGGER.info(jp.getSignature() + " ran successfully");
	}
	
	@AfterThrowing(pointcut=TARGET, throwing="exe")
	public void afterTaskThrow(JoinPoint jp, IOException exe) {
		LOGGER.error(jp.getSignature() + " threw an exception\n\t" + exe.getMessage());
	}
}
