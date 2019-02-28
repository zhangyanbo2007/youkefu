package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.IMGroup;

public abstract interface IMGroupRepository extends
		JpaRepository<IMGroup, String> {
	public abstract IMGroup findById(String id);

	public List<IMGroup> findByCreaterAndOrgi(String user , String orgi);
	
	public int countByNameAndOrgi(String name , String orgi);
}
