package com.ukefu.webim.service.es;

import java.util.Date;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ukefu.webim.web.model.EntCustomer;

public interface EntCustomerEsCommonRepository {
	
	public Page<EntCustomer> findByCreaterAndSharesAndOrgi(String creater , String shares ,String orgi, boolean includeDeleteData , String q , Pageable page) ;
	
	public Page<EntCustomer> findByCreaterAndSharesAndOrgi(String creater , String shares,String orgi  , Date begin , Date end, boolean includeDeleteData , String q , Pageable page) ;

	public Page<EntCustomer> findByCreaterAndSharesAndOrgi(String creater, String shares,String orgi,Date begin, Date end, boolean includeDeleteData,BoolQueryBuilder boolQueryBuilder, String q, Pageable page);
}
