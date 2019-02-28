package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AgentService;

public abstract interface AgentServiceRepository
  extends JpaRepository<AgentService, String>
{
  public abstract AgentService findByIdAndOrgi(String paramString , String orgi);
  
  public abstract List<AgentService> findByUseridAndOrgi(String paramString, String orgi);
  
  public abstract Page<AgentService> findByOrgi(String orgi, Pageable paramPageable);
  
  public abstract Page<AgentService> findByOrgiAndSatisfaction(String orgi , boolean satisfaction, Pageable paramPageable);
  
  public abstract Page<AgentService> findByOrgiAndStatus(String orgi ,String status , Pageable paramPageable);
  
  public abstract List<AgentService> findByAgentnoAndStatusAndOrgi(String agentno, String status , String orgi);
  
  public abstract int countByUseridAndOrgiAndStatus(String userid, String orgi, String status);
  
  public abstract List<AgentService> findByUseridAndOrgiAndStatus(String userid, String orgi, String status);
  
  public Page<AgentService> findAll(Specification<AgentService> spec, Pageable pageable);  //分页按条件查询 
  
  public abstract List<AgentService> findByTemplateidAndQualitystatus(String templateid,String qualitystatus ) ;
  
  public List<AgentService> findAll(Specification<AgentService> spec);
}
