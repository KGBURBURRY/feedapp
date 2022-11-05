package com.bptn.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidUserNameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4353037272103659942L;



	/**
	 * 
	 */




	String errorMessage;


	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public InvalidUserNameException() {
		super();
		logger.debug("Invalid Name. Please try again.");
		
	}
	
	public InvalidUserNameException(String errorMessage) { 
	    super(errorMessage);  
	    logger.debug("Invalid Name. Please try again.");
	
	
	}
	}   

