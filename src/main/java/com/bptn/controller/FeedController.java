package com.bptn.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/ping")
	public String ping() {

		logger.debug("Ping Executed!");

		String str = "Feed App up and running";

		return str;
	}@

	GetMapping("/calc/{num1}/{num2}/{operator}")
	public double calculator(@PathVariable("num1") double num1,
            @PathVariable("num2") double num2,
            @PathVariable("operator") String operator) {

				double result = 0.0;
				
				switch(operator) {
				case "+":
				result =  num1 + num2;
				break;
				case "-":
				result =  num1 - num2;
				break;
				case "*":
				result =  num1 * num2;
				break;
				case "/":
				result =  num1 / num2;
				break;
				default:
				throw new RuntimeException("Invalid Operation!!!");
				}
				
				return result;
				}
}
