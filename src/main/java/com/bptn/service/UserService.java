package com.bptn.service;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.models.UserID;
import com.bptn.repository.UserRepository;

import ch.qos.logback.classic.Logger;

@Service

public class UserService {

	@Autowired
	UserRepository userRepository;

	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public void validateUserId(String username) throws InvalidUserNameException {

		//String message;

		Optional<UserID> opt = this.userRepository.findById(username);

		if (opt.isEmpty()) {
			logger.error("User does not exist", username);
			throw new InvalidUserNameException("User does not exist. Please try again");

		} else {
			logger.error("User Validated", username);

		}



	}

}
