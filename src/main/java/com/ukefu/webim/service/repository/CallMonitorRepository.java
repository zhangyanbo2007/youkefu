package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallMonitor;


/*
 * 坐席监控表 -- JPA接口
 */
public interface CallMonitorRepository extends JpaRepository<CallMonitor, String> {
	
	public abstract CallMonitor findByUseridAndOrgi(String userid,String orgi);
	
	public abstract CallMonitor findByOrgiAndAgentno(String orgi,String agentno);
	
	public abstract List<CallMonitor> findByOrgi(String orgi);

	public abstract List<CallMonitor> findByOrgiAndStatus(String orgi, String status);
	
	public abstract List<CallMonitor> findByOrgiAndCode(String orgi, String code);
	
	public abstract Long countByOrgiAndStatus(String orgi, String status);
	
	public abstract List<CallMonitor> findByOrgiAndOrgan(String orgi ,String organ);
	
	
	
}
