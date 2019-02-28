package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AgentReport;

public abstract interface AgentReportRepository  extends JpaRepository<AgentReport, String>
{
  public abstract AgentReport findByIdAndOrgi(String id , String orgi);
  
  public abstract List<AgentReport> findByOrgi(String orgi);
}
