package com.ukefu.webim.service.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AgentUserTask;

public abstract interface AgentUserTaskRepository  extends JpaRepository<AgentUserTask, String>{
	
	public List<AgentUserTask> findByIdAndOrgi(String id , String orgi);
	
	public List<AgentUserTask> findByLastmessageLessThanAndStatusAndOrgi(Date start , String status , String orgi) ;
	
	public List<AgentUserTask> findByLastgetmessageLessThanAndStatusAndOrgi(Date start , String status , String orgi) ;
	
	public List<AgentUserTask> findByLogindateLessThanAndStatusAndOrgi(Date start , String status , String orgi) ;
}

