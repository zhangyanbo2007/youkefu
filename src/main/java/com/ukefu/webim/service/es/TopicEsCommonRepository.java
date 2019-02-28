package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;

import com.ukefu.webim.web.model.Topic;

public interface TopicEsCommonRepository {
	public Page<Topic> getTopicByCateAndOrgi(String cate ,String orgi,String q, int p, int ps) ;
	
	public Page<Topic> getTopicByTopAndOrgi(boolean top ,String orgi , String aiid, int p, int ps) ;
	
	public List<Topic> getTopicByOrgi(String orgi, String type , String q) ;
	
	public Page<Topic> getTopicByCateAndUser(String cate , String q ,String user , int p, int ps) ;
	
	public Page<Topic> getTopicByCon(BoolQueryBuilder booleanQueryBuilder , int p, int ps) ;
}
