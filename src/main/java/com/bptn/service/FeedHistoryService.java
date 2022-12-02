package com.bptn.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.models.History;
import com.bptn.models.UserID;
import com.bptn.repository.FeedHistoryRepository;

@Service
public class FeedHistoryService {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;
	@Autowired
	FeedHistoryRepository feedHistoryRepository;

	public List<History> getPostsByUsername(String username)  throws InvalidUserNameException {

		List<History> postHistory = this.feedHistoryRepository.findByUserId(new UserID(username));

		postHistory = this.removeEmptyHistory(postHistory);

		return postHistory;

	}

	public Optional<History> getPostsByPostId(String PostID) {

		Optional<History> postHistory = this.feedHistoryRepository.findById(PostID);

		return postHistory;

	}

	public List<History> getPostsByPostType(String postType) {

		List<History> postHistory = this.feedHistoryRepository.findByPostType(postType);

		postHistory = this.removeEmptyHistory(postHistory);

		return postHistory;

	}

	public List<History> removeEmptyHistory(List<History> postHistory) {

		postHistory.removeIf(h -> h.getPostType() == null || h.getPostType().isEmpty());

		return postHistory;

	}

	public String deleteHistoryByPostType(String postType) {

		List<History> postHistory = getPostsByPostType(postType);

		if (postHistory.isEmpty()) {
			logger.debug("postType: '{}' does not exist.", postType);

			return "Post doesn't exist";
		}
		this.feedHistoryRepository.deleteByPostType(postType);
		return "Delete was successful";
	}

}
