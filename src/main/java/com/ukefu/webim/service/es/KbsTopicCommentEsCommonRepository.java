package com.ukefu.webim.service.es;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.ukefu.webim.web.model.KbsTopicComment;


public interface KbsTopicCommentEsCommonRepository {
	public Page<KbsTopicComment> findByDataid(String id , int p , int ps) ;
	
	public List<KbsTopicComment> findByOptimal(String dataid) ;
	
	public Page<KbsTopicComment> findByCon(NativeSearchQueryBuilder searchQueryBuilder, String q, int p,int ps);
	
	public Page<KbsTopicComment> findByCon(NativeSearchQueryBuilder searchQueryBuilder, String field , String aggname, String q, int p,int ps);
	
	public Page<KbsTopicComment> countByCon(NativeSearchQueryBuilder searchQuery,String q, int p, int ps);
}
