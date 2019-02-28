package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.WorkMonitor;

public abstract interface WorkMonitorRepository extends JpaRepository<WorkMonitor, String> {
	
	public abstract WorkMonitor findByIdAndOrgi(String id, String orgi);
	
	public abstract Page<WorkMonitor> findByAgentAndOrgi(String agent, String orgi , Pageable paramPageable);

	public abstract List<WorkMonitor> findByOrgi(String orgi) ;
	
	public abstract int countByAgentAndDatestrAndStatusAndOrgi(String agent , String datestr , String status,String orgi) ;

	public abstract List<WorkMonitor> findByOrgiAndAgentAndDatestrAndFirsttime(String orgi ,String agent,String datestr,boolean firsttime) ;
	
	public Page<WorkMonitor> findAll(Specification<WorkMonitor> spec, Pageable pageable);  //分页按条件查询
	
	
	
}
