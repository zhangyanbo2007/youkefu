package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallOutConfig;

public abstract interface CallOutConfigRepository extends JpaRepository<CallOutConfig, String> {
	
	public abstract CallOutConfig findByIdAndOrgi(String id, String orgi);
	
	public abstract List<CallOutConfig> findByOrgi(String orgi);
	
	public abstract List<CallOutConfig> findByDataidAndOrgi(String dataid,String orgi);
}
