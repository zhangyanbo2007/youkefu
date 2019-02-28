package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ukefu.webim.web.model.EkmKnowledgeCollect;

public abstract interface EkmKnowledgeCollectESRepository
{
	
	public abstract List<EkmKnowledgeCollect> findByCreaterAndOrgi(String creater , String orgi );
	
	public abstract List<EkmKnowledgeCollect> findByIdAndOrgi(String id , String orgi );
	
	public abstract Page<EkmKnowledgeCollect> findByCreaterAndStatusAndOrgi(BoolQueryBuilder boolQuery,String creater , String status, String orgi, Pageable pageable );
	
	public abstract List<EkmKnowledgeCollect> findByStatusAndCreaterAndOrgi(String status , String creater, String orgi);
	
	public abstract Page<EkmKnowledgeCollect> findByKnowledgeowerAndStatusAndOrgi(BoolQueryBuilder boolQuery,String knowledgeower , String status, String orgi, Pageable pageable );
	
	public abstract List<EkmKnowledgeCollect> findByKnowledgeowerAndStatusAndOrgi(String knowledgeower , String status, String orgi);
	
	public abstract List<EkmKnowledgeCollect> findByCreaterAndStatusAndFolderidAndOrgi(String creater , String status,String folderid , String orgi);
	
	public abstract List<EkmKnowledgeCollect> findByCreaterAndStatusAndOrgi(String creater , String status, String orgi);
	
	public abstract EkmKnowledgeCollect findByCreaterAndKnowledgeidAndStatusAndOrgi(String creater , String knowledgeid, String status, String orgi );
	
	public abstract EkmKnowledgeCollect findByCreaterAndKbidAndStatusAndOrgi(String creater , String kbid, String status, String orgi );
	
	public abstract List<EkmKnowledgeCollect> findByKnowledgeidAndOrgi(String knowledgeid , String orgi );
	
	public void delete(List<EkmKnowledgeCollect> ekmKnowledgeCollectList) ;
	
	public abstract List<EkmKnowledgeCollect> findByOrgi(String orgi );
	
	public abstract List<EkmKnowledgeCollect> findByCreaterAndKnowledgeidAndOrgi(String creater, String knowledgeid, String orgi );
}

