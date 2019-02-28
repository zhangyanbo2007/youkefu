package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AgentServiceSatis;

public abstract interface AgentServiceSatisRepository extends JpaRepository<AgentServiceSatis, String>{
	public int countById(String id) ;
}
