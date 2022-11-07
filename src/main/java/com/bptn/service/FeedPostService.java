package com.bptn.service;

import java.util.Objects;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;
import com.bptn.repository.FeedPostRepository;

import ch.qos.logback.classic.Logger;
import request.FeedPostRequest;

@Service
public class FeedPostService {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Value("${api.key}")
	String apiKey;

	@Value("${api.base.url}")
	String apiBaseUrl;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	FeedPostRepository feedPostRepository;

	{
		// "fromDate":"2022-10-13",
		// "toDate": "2022-11-07",
		// "queryKeyword": "newyork",
		// "username": "Karim"
	}

	public Post getPostFormNewsAndSavePost(FeedPostRequest request) {

		// Post feed = new Post();

		// 1) BUILD URL FOR THE NEWS API REQUEST
		// buildAPIUrl() ---STEP 9

		String url = this.buildAPIUrl(request);
		// 2) SEND THE REQUEST TO THE NEWS API + RECIEVE RESPONSE
		// getFeedFromNewsApi (String Url) ---STEP 7

		String feedResult = getFeedFormNewsAPI(url);

		// 3) STORE RECIEVED DATA IN STEP 2 IN POSTGRESQL
		// storeFeed()---STEP 8

		Post feed = storeFeed(feedResult, request);
		return feed;
	}

	private String buildAPIUrl(FeedPostRequest request) {

		StringBuilder urlBuilder = new StringBuilder(); // Mutable & non-synchronized, Not thread safe

		urlBuilder.append(apiBaseUrl);
		urlBuilder.append("?q=");
		urlBuilder.append(request.getQueryKeyword());

		if (request.getFromDate() != null && !request.getFromDate().isEmpty())
			;
		{
			urlBuilder.append("&from=").append(request.getFromDate());
		}
		if (request.getToDate() != null && !request.getToDate().isEmpty())
			;
		{
			urlBuilder.append("&from=").append(request.getToDate());
		}
		urlBuilder.append("&apiKey=").append(apiKey);
		return urlBuilder.toString();
	}

	private String getFeedFormNewsAPI(String url) {

		String result = this.restTemplate.getForObject(url, String.class);

		return result;

	}

	private Post storeFeed(String feedResult, FeedPostRequest request) {

		UserID userId = new UserID(request.getUsername());
		Post feed = new Post();

		feed.setPostType(feedResult);

		feed.setUserId(userId);
		feed.setPostID(this.generatePostId(request));
		logger.debug("Feed to be stored: {}", feed);

		return this.feedPostRepository.save(feed);
	}

	private String generatePostId(FeedPostRequest request) {

		Random random = new Random(System.currentTimeMillis());

		StringBuilder postIdBuilder = new StringBuilder();

		postIdBuilder.append(random.nextInt());
		postIdBuilder.append(Objects.hashCode(request.getUsername() + " " + request.getQueryKeyword()));

		String postId = postIdBuilder.toString();
		if (postId.startsWith("-")) {
			postId = postId.substring(1);
		}

		return postId;
	}
}
