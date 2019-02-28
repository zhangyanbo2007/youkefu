package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SystemUpdatecon;

public abstract interface SystemUpdateconRepository  extends JpaRepository<SystemUpdatecon,String>{
	
	public abstract List<SystemUpdatecon> findByOrgi(String orgi);
	
	public abstract SystemUpdatecon findByIdAndOrgi(String id,String orgi);
}
