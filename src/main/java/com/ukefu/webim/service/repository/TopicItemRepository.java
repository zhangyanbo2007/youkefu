package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.TopicItem;

public interface TopicItemRepository extends JpaRepository<TopicItem, String> {
	public abstract List<TopicItem> findByTopicid(String topicid);
	
	public abstract TopicItem findByIdAndOrgi(String id, String orgi);
	
}
