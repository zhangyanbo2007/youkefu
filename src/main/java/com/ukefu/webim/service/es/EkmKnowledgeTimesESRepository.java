package com.ukefu.webim.service.es;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ukefu.webim.web.model.EkmKnowledgeTimes;
import com.ukefu.webim.web.model.User;

public abstract interface EkmKnowledgeTimesESRepository 
{
	
	public abstract List<EkmKnowledgeTimes> findByKbidAndVersionAndOrgi(String kbid ,int version , String orgi );
	
	public abstract List<EkmKnowledgeTimes> findByKbidAndOrgi(String kbid , String orgi );
	
	public abstract Page<EkmKnowledgeTimes> findByOrgi(String orgi ,Pageable pageable);
	
	public abstract Page<EkmKnowledgeTimes> findByOrgi(String orgi ,User user ,List<String>  ekmKnowledgeMasterid,Pageable pageable);
	
	public void delete(List<EkmKnowledgeTimes> ekmKnowledgeTimes) ;
	
}

