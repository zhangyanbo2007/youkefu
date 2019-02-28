package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallOutRole;


public abstract interface CallOutRoleRepository extends JpaRepository<CallOutRole, String> {
	
	public abstract CallOutRole findByIdAndOrgi(String id, String orgi);

	public abstract Page<CallOutRole> findByOrgi(String orgi, Pageable paramPageable) ;
	public abstract List<CallOutRole> findByOrgi(String orgi) ;
	public abstract CallOutRole findByOrgiAndRoleid(String orgi,String roleid) ;
	
}
