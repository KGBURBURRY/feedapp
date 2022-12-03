package com.bptn.service;

import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bptn.models.Post;
import com.bptn.repository.FeedPostRepository;
import com.bptn.request.FeedPostRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class FeedPostService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.base.url}")
    private String apiBaseUrl;
    
    private static final String POST_TYPE = "news";

    @Autowired
    private FeedPostRepository feedPostRepository;

    public Post getPostFromNewsAndSavePost(FeedPostRequest feedPostRequest) {
        String url = buildApiUrl(feedPostRequest);
        LOGGER.debug("News Feed URL = {}", url);
        String feedResult = getFeedFromNewsAPI(url);
        return storeFeed(feedResult, feedPostRequest);
    }

    private Post storeFeed(String feedResult, FeedPostRequest feedPostRequest) {
        Post feed = new Post();
        feed.setPost(feedResult);
        feed.setPostType(POST_TYPE);
        feed.setUsernameKey(feedPostRequest.getUsername());
        LOGGER.debug("Feed to be stored : {}", feed);
        return feedPostRepository.save(feed);
    }

    private String generatePostId(FeedPostRequest feedPostRequest) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder postIdBuilder = new StringBuilder();
        postIdBuilder.append(random.nextInt());
        postIdBuilder.append(Objects.hashCode(feedPostRequest.getUsername() + " " + feedPostRequest.getQueryKeyword()));
        String postId = postIdBuilder.toString();
        if (postId.startsWith("-")) {
            return postId.substring(1);
        }
        return postId;
    }

    private String getFeedFromNewsAPI(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(result);
        String postDescription =
                jsonElement.getAsJsonObject().get("articles").getAsJsonArray().get(0).getAsJsonObject().get("description").toString();
        return postDescription;
    }

    private String buildApiUrl(FeedPostRequest feedPostRequest) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(apiBaseUrl);
        urlBuilder.append("?q=").append(feedPostRequest.getQueryKeyword());
        if (feedPostRequest.getFromDate() != null && !feedPostRequest.getFromDate().isEmpty()) {
            urlBuilder.append("&from=").append(feedPostRequest.getFromDate());
        }
        if (feedPostRequest.getToDate() != null && !feedPostRequest.getToDate().isEmpty()) {
            urlBuilder.append("&to=").append(feedPostRequest.getToDate());
        }
        urlBuilder.append("&apiKey=").append(apiKey);
        return urlBuilder.toString();
    }
}












//package com.bptn.service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.Random;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.bptn.exceptions.InvalidPostException;
//import com.bptn.models.Post;
//import com.bptn.models.UserID;
//import com.bptn.repository.FeedPostRepository;
//
//import request.FeedPostRequest;
//
//@Service
//public class FeedPostService {
//	
//	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass()); 
//	
//	
//	@Value ( "${api.key}")
//	String apiKey;
//	
//	@Value ( "${api.base.url}")
//	String apiBaseUrl; 
//	
//	@Autowired  
//	FeedPostRepository feedPostRepository; 
//	
//	@Autowired
//	RestTemplate restTemplate; 
//	
//	
//	/*  // need an object and that object will be from the controller 
//	 {
//	    "fromDate": "2022-10-13",
//	    "toDate": "2022-11-07", 
//	    "queryKeyword": "newyork"
//	    "username": "carlos"
//	 }
//	 */
//
//
//	public Post getPostFormNewsAndSavePost (FeedPostRequest request) {
//	
//		//Post feed  = new Post(); ( commeted out, anne question)
//		
//		/* 1. build the url of the request for newspapaer */
//		// buildAPurl
//		String url = this.buildAPIUrl(request); 
//		
//		/*2. send ther equest ot he newsapi and receive resposnce */
//		//getFeedFromNewsAPI (step 7)
//		
//		String feedResult = getFeedFromNewsAPI(url);
//		
//		/* 3. store thed ata received in step 2 in postgressl
//		 ///storefeed() (8)
//		 */
//		Post feed = storeFeed(feedResult, request); 
//		return feed;  
//	}
//	
//	private String buildAPIUrl(FeedPostRequest request) {
//		StringBuilder urlBuilder = new StringBuilder();
//		
//		// https://newsapi.org/v2/everything
//		//?q= tesla
//		//&from=2022-10-07&
//		//sortBy=publishedAt&apiKey=a970454c5837435a92d5313490c3152d
//		
//		urlBuilder.append(apiBaseUrl);
//		urlBuilder.append("?q="); 
//		urlBuilder.append(request.getQueryKeyword()); 
//		
//		
//		if (request.getFromDate()!= null && !request.getFromDate().isEmpty()) {
//			urlBuilder.append("&from=").append(request.getFromDate()); 
//		}
//		if (request.getToDate()!= null && !request.getToDate().isEmpty()) {
//			urlBuilder.append("&to=").append(request.getToDate()); 
//		}
//		
//		urlBuilder.append("&apikey=").append(apiKey);
//		return urlBuilder.toString(); 
//	}
//
//	private String getFeedFromNewsAPI (String url) {
//		String result = this.restTemplate.getForObject(url, String.class);
//		return result; 
//	}
//	
//	private Post storeFeed(String feedResult,FeedPostRequest request) {
//		
//		UserID userId = new UserID(request.getUsername()); 
//		Post feed = new Post();
//		
//		//feed.setPost("Hello"); 
//		feed.setPostType(feedResult); 
//		//feed.setUserID(userId); - disabled this because of working on imagemetadata
//		feed.setPostID(this.generatePostId(request)); 
//		
//		logger.debug("Feed to be stored: {}", feed);
//		return this.feedPostRepository.save(feed); 
//		
//	}
//	
//	private String generatePostId ( FeedPostRequest request) {
//		Random random = new Random( System.currentTimeMillis());
//		StringBuilder postIdBuilder = new StringBuilder();
//		postIdBuilder.append(random.nextInt());
//		postIdBuilder.append(Objects.hashCode(request.getUsername()+ " " + request.getQueryKeyword())); 
//		
//		String postId = postIdBuilder.toString();
//		if (postId.startsWith("_")) {
//			postId = postId.substring(1);
//		}
//		return postId; 
//	}
//	
////	public void validatePostID(String postID)throws InvalidPostException{
////		Optional<Post> opt = this.feedPostRepository.findById(postID);
////		if (opt.isEmpty()) {
////			logger.debug("PostID={} does not exist", postID); 
////			throw new InvalidPostException ("This postID does not exist");
////		} else {
////			logger.debug("PostID={} validated",postID);
////		}
////		
////	}
////	
////	public void validatePostType ( String postType) throws InvalidPostException{
////		List<Post> opt = this.feedPostRepository.findBypostType(postType);
////		if (opt.isEmpty()) {
////			logger.debug("PostType={} does not exist", postType); 
////			throw new InvalidPostException ("This PostType does not exist");
////		}else {
////			logger.debug("PostType={} validated", postType); 
////		}
////		
////	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}