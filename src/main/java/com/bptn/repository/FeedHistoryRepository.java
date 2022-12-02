package com.bptn.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bptn.models.History;
import com.bptn.models.UserID;

@Repository
public interface FeedHistoryRepository extends JpaRepository<History, String> {
	
	List<History> findByUserId (UserID userId);
	
	List <History> findByPostType (String postType);
	
	@Transactional
	void deleteByPostType (String postType);
	


} 
