package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.ServiceAi;

public abstract interface ServiceAiRepository extends JpaRepository<ServiceAi, String> {
	
	public abstract ServiceAi findByIdAndOrgi(String id, String orgi);
	
	public abstract ServiceAi findByCodeAndOrgi(String code, String orgi);

	public abstract List<ServiceAi> findByOrgi(String orgi) ;
	
	public abstract ServiceAi findByOrgiAndName(String orgi, String name);
	
	public abstract Page<ServiceAi> findByOrgi(String orgi, Pageable page) ;

}
