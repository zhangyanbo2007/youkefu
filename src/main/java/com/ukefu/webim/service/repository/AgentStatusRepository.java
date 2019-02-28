package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AgentStatus;

public abstract interface AgentStatusRepository extends
		JpaRepository<AgentStatus, String> {
	public abstract AgentStatus findByIdAndOrgi(String paramString,String orgi);
	
	public abstract List<AgentStatus> findByAgentnoAndOrgi(String agentid, String orgi);
	
	public abstract Page<AgentStatus> findByOrgi(String orgi , Pageable page);
	
	public abstract List<AgentStatus> findByOrgi(String orgi);
}
