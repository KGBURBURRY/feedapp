package com.bptn.handler;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ch.qos.logback.classic.Logger;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {

		logger.debug(ex.getMessage(), ex);

		return ResponseEntity.badRequest().body(ex.getMessage());

	}

}
