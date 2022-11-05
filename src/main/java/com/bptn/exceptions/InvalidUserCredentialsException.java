package com.bptn.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidUserCredentialsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6556299050202913447L;



	/**
	 * 
	 */




	String errorMessage;


	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public InvalidUserCredentialsException() {
		super();
		logger.debug("Invalid Name. Please try again.");
		
	}
	
	public InvalidUserCredentialsException(String errorMessage) { 
	    super(errorMessage);  
	    logger.debug("Invalid Name. Please try again.");
	
	
	}
	}   

