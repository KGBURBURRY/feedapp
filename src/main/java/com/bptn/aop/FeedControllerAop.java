package com.bptn.aop;

//This is an Intercepter for handling requests for FeedController

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



// We use the @Aspect declaration
@Aspect
//allows Spring to automatically detect custom Beans
@Component 
public class FeedControllerAop {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HttpServletRequest request;

	// Here, we tell our aspect that its execution will happen @Around the time of
	// the joinpoint given in the following line.
	@Around("execution(* com.bptn.controller.FeedController.*(..))")
	public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {

		Map<String, String[]> map = request.getParameterMap();

		logger.debug("Controller Executing : " + joinPoint.getSignature());

		if (!map.isEmpty()) {
			logger.debug("Parameters Received:");
			map.forEach((k, v) -> logger.debug("{}-> {}", k, v));
		}

		Object[] args = joinPoint.getArgs();

		if (args.length > 0) {

			logger.debug("Arguments Received: {}", Arrays.toString(args));
		}

		Object value = null;
		try {

			value = joinPoint.proceed();

			if (value instanceof List) {

				List<?> list = (List<?>) value;

				// Limit the size of list to 20 element max to avoid flooding the console.
				logger.debug("Return Value: {}", list.stream().limit(20).toList());
			} else {
				logger.debug("Return Value: {}", value);
			}

		} finally {
			// Do Something useful If you have to
		}

		logger.debug("Controller Ended Execution : " + joinPoint.getSignature());

		return value;
	}

}
