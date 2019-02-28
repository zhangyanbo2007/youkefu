package com.ukefu.webim.service.es;

import java.util.Date;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ukefu.webim.web.model.EkmKnowledge;
import com.ukefu.webim.web.model.User;

public abstract interface EkmKnowledgeESRepository  
{
	
	public abstract EkmKnowledge findByTitleAndOrgi(String title , String orgi );
	
	public abstract EkmKnowledge findByIdAndOrgi(String id , String orgi);
	
//	public abstract Page<EkmKnowledge> findByDatastatusAndOrgi(boolean datastatus, String orgi,User user,Pageable pageable);
	
	public abstract Page<EkmKnowledge> findByDatastatusAndOrgiAndOwn(boolean datastatus, String orgi,User user,String own,Pageable pageable);
	
	public abstract List<EkmKnowledge> findByOrgiAndDatastatus(String orgi,boolean datastatus);
	
	public abstract Page<EkmKnowledge> findByKnowbaseidAndKnowledgetypeidAndDatastatusAndOrgi(String knowbaseid,String knowledgetypeid,boolean datastatus ,String orgi,Pageable page);
	
	public abstract List<EkmKnowledge> findByKnowbaseidAndDatastatusAndOrgi(String knowbaseid,boolean datastatus ,String orgi);
	
	public abstract Page<EkmKnowledge> findByDatastatusAndKnowbaseidAndOrgi(boolean datastatus ,String knowbaseid,String orgi,Pageable pageable);
	
	public abstract List<EkmKnowledge> findByCreaterAndDatastatusAndOrgi(String creater,boolean datastatus ,String orgi);
	
//	public abstract Page<EkmKnowledge> findByPubstatusAndDatastatusAndOrgi(String pubstatus, boolean datastatus ,String orgi, Pageable pageable);
	
	public abstract Page<EkmKnowledge> findByPubstatusAndDatastatusAndOrgiAndOwn(String pubstatus, boolean datastatus ,String orgi,String own,User user, Pageable pageable);

	public abstract Page<EkmKnowledge> getByDimenidAndDatastatusAndOrgi(String dimenid, boolean datastatus ,String orgi, Pageable pageable);
	
	public abstract Page<EkmKnowledge> getByDimentypeidAndDatastatusAndOrgi(String dimentypeid, boolean datastatus ,String orgi, Pageable pageable);

	public abstract Page<EkmKnowledge> findByKnowtypeidAuth(boolean datastatus,List<String>  ekmKnowledgeType,String knowbaseid,String orgi,Pageable pageable);
	
	public abstract Page<EkmKnowledge> findByKnowledge(BoolQueryBuilder boolQueryBuilder,boolean datastatus,List<String>  ekmKnowledgeType,String orgi, User user,Pageable pageable);
	
	//知识地图查询
	public abstract Page<EkmKnowledge> findBySearchKnowledge(boolean datastatus,String  q , String tag, String knowledgetype,String orgi, User user, Date begin, Date end,Pageable pageable);
	
}

