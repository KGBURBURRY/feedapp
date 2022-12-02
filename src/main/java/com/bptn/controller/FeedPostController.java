package com.bptn.controller;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bptn.exceptions.InvalidRequestException;
import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.models.Post;
import com.bptn.service.FeedPostService;
import com.bptn.service.UserService;

import ch.qos.logback.classic.Logger;
import request.FeedPostRequest;

@RestController

public class FeedPostController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	FeedPostService feedPostService;

	@Autowired
	UserService userService;

	@PostMapping("/posts/feed/create")
	public ResponseEntity<?> saveFeed(@RequestBody FeedPostRequest request) {

		logger.debug("Executing saveFeed API");

		try {
			this.validateRequest(request);

			this.userService.userValidation(request.getUsername());

			Post feed = this.feedPostService.getPostFormNewsAndSavePost(request);

			logger.debug("Post Sucessfully Saved.");

			return new ResponseEntity<>(feed, HttpStatus.OK);
		} catch (InvalidRequestException | InvalidUserNameException ex) {

			logger.error(Arrays.toString(ex.getStackTrace()));

			return ResponseEntity.badRequest().body(ex.getMessage());

		}

	}

	private void validateRequest(FeedPostRequest request) throws InvalidRequestException {

		if (!StringUtils.hasText(request.getFromDate()) || !StringUtils.hasText(request.getToDate())) {
			throw new InvalidRequestException("Invalid Request: FromDate and ToDate are required, boss.");
		}

	}
}
