package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKnowledgePublish;

public interface EkmKnowledgePublishRepository extends JpaRepository<EkmKnowledgePublish,String>{
	
	public abstract EkmKnowledgePublish findByKnowidAndVersionAndOrgi(String knowid,int version,String orgi);

}
