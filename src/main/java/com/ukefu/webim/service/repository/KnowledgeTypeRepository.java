package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.KnowledgeType;

public abstract interface KnowledgeTypeRepository extends
		JpaRepository<KnowledgeType, String> {
	
	public abstract KnowledgeType findByIdAndOrgi(String id, String orgi);

	public abstract int countByNameAndOrgiAndIdNot(String name, String orgi , String id);
	
	public abstract int countByNameAndOrgiAndParentidNot(String name, String orgi , String parentid);
	
	public abstract List<KnowledgeType> findByOrgi(String orgi) ;
	
	public abstract List<KnowledgeType> findByOrgiAndTypeid(String orgi ,String typeid) ;

	public abstract KnowledgeType findByNameAndOrgi(String name, String orgi);

	public abstract List<KnowledgeType> findByNameAndOrgiAndTypeid(String name,String orgi ,String typeid) ;
	
	public abstract List<KnowledgeType> findByNameAndOrgiAndTypeidAndIdNot(String name,String orgi ,String typeid, String id) ;
	
	public abstract KnowledgeType findByNameAndOrgiAndIdNot(String name, String orgi, String id);
	
	public Page<KnowledgeType> findAll(Specification<KnowledgeType> spec, Pageable pageable);  //分页按条件查询 
}
