package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallAgent;

public abstract interface CallAgentRepository extends JpaRepository<CallAgent, String> {
	
	public abstract CallAgent findByIdAndOrgi(String id, String orgi);
	
	public abstract List<CallAgent> findByNameAndOrgi(String name, String orgi);

	public abstract Page<CallAgent> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<CallAgent> findByOrgi(String orgi) ;
	
	public abstract List<CallAgent> findByActidAndOrgi(String actid ,String orgi) ;
	
	public abstract List<CallAgent> findByOrgiAndActid(String orgi , String actid) ;
}
