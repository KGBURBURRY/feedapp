package com.bptn.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/ping")
	public String ping() {
	
		logger.debug("Ping Executed!");
		
		String str = "Feed App up and running";
		
		return str;
	}

}
