package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ukefu.webim.web.model.QuickReply;

public interface QuickReplyEsCommonRepository {
	
	public Page<QuickReply> getByOrgiAndCate(String orgi,String cate ,String q, Pageable page) ;
	
	public Page<QuickReply> getByQuicktype(String quicktype , int p, int ps) ;
	
	public Page<QuickReply> getByOrgiAndType(String orgi,String type, String q , Pageable page) ;
	
	public List<QuickReply> findByOrgiAndCreater(String orgi , String creater, String q) ;
	
	public Page<QuickReply> getByCateAndUser(String cate , String q ,String user , int p, int ps) ;
	
	public Page<QuickReply> getByCon(BoolQueryBuilder booleanQueryBuilder , int p, int ps) ;
	
	public void deleteByCate(String cate , String orgi) ;

	public List<QuickReply> getQuickReplyByOrgi(String orgi, String cate,String type, String q);
}
