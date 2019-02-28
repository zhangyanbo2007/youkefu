package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmShare;

public abstract interface EkmShareRepository  extends JpaRepository<EkmShare, String>
{
	
	public abstract EkmShare findByIdAndOrgi(String id , String orgi );
	
	//分享给我的知识
	public abstract Page<EkmShare> findByDatastatusAndShareuserAndOrgi(boolean datastatus,String shareuser , String orgi ,Pageable pageable);
	
	//获取-我分享的知识
	public abstract Page<EkmShare> findByDatastatusAndCreaterAndOrgi(boolean datastatus,String creater , String orgi ,Pageable pageable);
	
	public abstract List<EkmShare> findByDatastatusAndKnowledgeidAndOrgi(boolean datastatus,String knowledgeid , String orgi);
}

