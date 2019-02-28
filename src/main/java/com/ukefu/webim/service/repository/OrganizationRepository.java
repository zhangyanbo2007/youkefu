package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Organization;

public abstract interface OrganizationRepository extends JpaRepository<Organization, String> {
	
	public abstract Organization findById(String id);
	
}
