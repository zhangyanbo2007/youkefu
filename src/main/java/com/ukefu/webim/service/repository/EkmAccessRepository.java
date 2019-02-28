package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmAccess;

public abstract interface EkmAccessRepository  extends JpaRepository<EkmAccess, String>
{
	
	public abstract EkmAccess findByIdAndOrgi(String id , String orgi);
	
	public abstract Page<EkmAccess> findByDatastatusAndOrgi(boolean datastatus ,String orgi ,Pageable paramPageable);
	
	public abstract Page<EkmAccess> findByDatastatusAndKnowledgeowerAndOrgi(boolean datastatus ,String knowledgeower,String orgi ,Pageable paramPageable);
	
	public abstract Page<EkmAccess> findByDatastatusAndCreaterAndOrgi(boolean datastatus ,String creater,String orgi ,Pageable paramPageable);
	
	public abstract List<EkmAccess> findByKnowledgeidAndOrgi(String knowledgeid,String orgi );
	
	
}

