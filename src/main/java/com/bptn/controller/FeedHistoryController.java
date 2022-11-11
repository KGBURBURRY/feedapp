package com.bptn.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.jpa.History;
import com.bptn.service.FeedHistoryService;

@RestController
public class FeedHistoryController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	FeedHistoryService feedHistoryService;
	
	@GetMapping("/History/username/{username}") 
	public List <History>getPostsByUsername(@PathVariable("username") String username) throws InvalidUserNameException{
		logger.debug("Executing getPostsByUsername API");
		List <History> postHistory = this.feedHistoryService.getPostsByUsername(username);
		return postHistory;
		
	}
	@GetMapping("/History/postID/{postID}")
	public Optional<History> getPostsByPostID(@PathVariable("postId") String postId){
		logger.debug("Executing getPostsByPostId API");
		Optional <History> postHistory = this.feedHistoryService.getPostsByPostId(postId);
		return postHistory;
		
	}
	@GetMapping("/History/postType/{postType}")
	public List<History> getPostsByPostType(@PathVariable("postType") String postType){
		logger.debug("Executing getPostsByPostType API");
		List<History> postHistory = this.feedHistoryService.getPostsByPostType(postType);
		return postHistory;
		
	}
	@DeleteMapping("/DeleteHistory/postType/{postType}")
	public String deleteHistoryByPostType(@PathVariable("postType") String postType){
		logger.debug("Executing deletePostsByPostType API");
		String postHistory = this.feedHistoryService.deleteHistoryByPostType(postType);
		return postHistory;
	}
}
