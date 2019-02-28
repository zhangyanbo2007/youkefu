package com.ukefu.webim.service.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKnowledgeType;

public abstract interface EkmKnowledgeTypeRepository  extends JpaRepository<EkmKnowledgeType, String>
{
	
	public abstract EkmKnowledgeType findByNameAndOrgi(String name , String orgi);
	
	public abstract EkmKnowledgeType findByIdAndOrgi(String id , String orgi);
	
	public abstract Page<EkmKnowledgeType> findAll(Specification<EkmKnowledgeType> spec, Pageable page) ;
	
	//public abstract Page<EkmExperts> findByOrgiAndBustypeOr(String orgi ,String bustype,String bus ,Pageable paramPageable);
	
	public abstract List<EkmKnowledgeType> findByOrgi(String orgi );
	
	public abstract List<EkmKnowledgeType> findByKnowbaseidAndOrgi(String knowbaseid,String orgi);
	
	public abstract List<EkmKnowledgeType> findByAuditerAndOrgi(String auditer,String orgi);
	
	public abstract List<EkmKnowledgeType> findByParentidAndOrgi(String parentid , String orgi);
	
	public abstract List<EkmKnowledgeType> findByKnowbaseidAndParentidAndOrgi(String knowbaseid,String parentid,String orgi);
	
	public abstract List<EkmKnowledgeType> findByKnowbaseidAndParentidAndNavshowAndOrgi(String knowbaseid,String parentid,boolean navshow,String orgi);
	
	public abstract List<EkmKnowledgeType> findByKnowbaseidAndParentidAndDeskshowAndOrgi(String knowbaseid,String parentid,boolean deskshow,String orgi);
	
	public abstract List<EkmKnowledgeType> findByKnowbaseidAndNavshowAndOrgi(String knowbaseid,boolean navshow,String orgi);
	
	public abstract List<EkmKnowledgeType> findByKnowbaseidAndDeskshowAndOrgi(String knowbaseid,boolean deskshow,String orgi);
	
}

