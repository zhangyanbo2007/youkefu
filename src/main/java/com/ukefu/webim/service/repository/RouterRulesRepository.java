package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.RouterRules;

public interface RouterRulesRepository extends JpaRepository<RouterRules, String> {
	
	public RouterRules findByIdAndOrgi(String id , String orgi);
	public List<RouterRules> findByHostidAndOrgi(String hostid , String orgi);
	public int countByNameAndOrgi(String name, String orgi);
}
