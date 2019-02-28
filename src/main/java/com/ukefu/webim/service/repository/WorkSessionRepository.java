package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.WorkSession;

public abstract interface WorkSessionRepository extends JpaRepository<WorkSession, String> {
	
	public abstract WorkSession findByIdAndOrgi(String id, String orgi);
	
	public abstract Page<WorkSession> findByAgentAndOrgi(String agent, String orgi , Pageable paramPageable);

	public abstract List<WorkSession> findByOrgi(String orgi) ;

	public abstract List<WorkSession> findByOrgiAndClientid(String orgi ,String clientid) ;
	
	public Page<WorkSession> findAll(Specification<WorkSession> spec, Pageable pageable);  //分页按条件查询
	
	public abstract int countByAgentAndDatestrAndOrgi(String agent , String datestr , String orgi) ;
}
