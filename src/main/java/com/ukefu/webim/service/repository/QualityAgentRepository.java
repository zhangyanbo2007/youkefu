package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityAgent;

public abstract interface QualityAgentRepository extends JpaRepository<QualityAgent, String> {
	
	public abstract QualityAgent findByIdAndOrgi(String id, String orgi);
	
	public abstract List<QualityAgent> findByNameAndOrgi(String name, String orgi);

	public abstract Page<QualityAgent> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<QualityAgent> findByOrgi(String orgi) ;
	
	public abstract List<QualityAgent> findByActidAndOrgi(String actid ,String orgi) ;
	
	public abstract List<QualityAgent> findByOrgiAndActid(String orgi , String actid) ;
}
