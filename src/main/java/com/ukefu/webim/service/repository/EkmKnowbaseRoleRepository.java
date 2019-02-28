package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKnowbaseRole;


public abstract interface EkmKnowbaseRoleRepository extends JpaRepository<EkmKnowbaseRole, String> {
	
	public abstract EkmKnowbaseRole findByIdAndOrgi(String id, String orgi);
	
	public abstract EkmKnowbaseRole findByRoleidAndKnowbaseidAndOrgi(String roleid,String knowbaseid, String orgi);

	public abstract List<EkmKnowbaseRole> findByKnowbaseidAndOrgi(String knowbaseid,String orgi) ;
	
	public abstract List<EkmKnowbaseRole> findByRoleidAndOrgi(String roleid,String orgi) ;
	
}
