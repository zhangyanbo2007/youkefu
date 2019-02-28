package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;

import com.ukefu.webim.web.model.KbsTopic;

public interface KbsTopicEsCommonRepository {
	public Page<KbsTopic> getTopicByCate(String cate ,String q, int p, int ps) ;
	
	public Page<KbsTopic> getTopicByTop(boolean top , int p, int ps) ;
	
	public List<KbsTopic> getTopicByOrgi(String orgi, String type , String q) ;
	
	public Page<KbsTopic> getTopicByCateAndUser(String cate , String q ,String user , int p, int ps) ;
	
	public Page<KbsTopic> getTopicByCon(BoolQueryBuilder booleanQueryBuilder , int p, int ps) ;
}
