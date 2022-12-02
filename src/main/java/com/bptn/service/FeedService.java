package com.bptn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.models.Post;
import com.bptn.models.UserID;
import com.bptn.repository.FeedRepository;

@Service
public class FeedService {
	@Autowired
	FeedRepository feedRepository;

	public List<Post> getPostsbyUsername(String username) {

		List<Post> posts = this.feedRepository.findByUserId(new UserID(username));

		posts = this.removeEmptyPosts(posts);

		return posts;
	}

	public Optional<Post> getPostsByPostID(String postID) {

		Optional<Post> posts = this.feedRepository.findById(postID);

		return posts;
	}

	public List<Post> getPostsByPostType(String postType) {

		List<Post> posts = this.feedRepository.findByPostType(postType);

		posts = this.removeEmptyPosts(posts);
		return posts;
	}

	List<Post> removeEmptyPosts(List<Post> posts) {

		posts.removeIf(p -> p.getPostType() == null || p.getPostType().isEmpty());
		return posts;
	}
}