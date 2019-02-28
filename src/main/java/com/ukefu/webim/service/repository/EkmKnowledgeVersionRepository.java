package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKnowledgeVersion;

public interface EkmKnowledgeVersionRepository extends JpaRepository<EkmKnowledgeVersion,String>{
	
	public abstract List<EkmKnowledgeVersion> findByKnowidAndVersion(String knowid,int version);
	
	public abstract EkmKnowledgeVersion findByIdAndOrgi(String id,String orgi);
	
	public Page<EkmKnowledgeVersion> findAll(Specification<EkmKnowledgeVersion> spec,Pageable pageable);
	
	public abstract List<EkmKnowledgeVersion> findByOrgi(String orgi);

}
