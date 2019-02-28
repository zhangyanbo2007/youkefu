package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.OrganRole;

public abstract interface OrganRoleRepository  extends JpaRepository<OrganRole, String>
{
	
	public abstract List<OrganRole> findByOrgi(String orgi);
	
	public abstract List<OrganRole> findByOrgiAndOrgan(String orgi ,Organ organ);
}

