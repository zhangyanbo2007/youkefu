package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Acl;

public interface AclRepository extends JpaRepository<Acl, String> {
	
	public Acl findByIdAndOrgi(String id , String orgi);
	public List<Acl> findByHostidAndOrgi(String hostid , String orgi);
	public int countByNameAndOrgi(String name, String orgi);
}
