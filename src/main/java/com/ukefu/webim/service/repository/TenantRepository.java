package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Tenant;


public abstract interface TenantRepository extends JpaRepository<Tenant, String> {
	
	public abstract Tenant findById(String id);
	
	public abstract List<Tenant> findByOrgid(String orgid);

	public abstract Tenant findByOrgidAndTenantname(String orgid, String tenantname);

	
}
