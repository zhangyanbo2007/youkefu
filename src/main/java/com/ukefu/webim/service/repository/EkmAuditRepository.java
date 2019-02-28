package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmAudit;

public interface EkmAuditRepository extends JpaRepository<EkmAudit,String>{
	
	public Page<EkmAudit> findAll(Specification<EkmAudit> spec, Pageable pageable);
	
	public abstract EkmAudit findByIdAndOrgi(String id,String orgi);
	
	public abstract List<EkmAudit> findByOrgi(String orgi);
	
	public abstract EkmAudit findByKnowidAndOrgi(String knowid,String orgi);
	
	public abstract EkmAudit findByKnowidAndDatastatusAndVersionAndOrgi(String knowid,boolean datastatus,int version,String orgi);
	
	public abstract EkmAudit findByIdAndDatastatusAndOrgi(String id,boolean datastatus,String orgi);
	
	public abstract List<EkmAudit> findByPubstatusAndAuditorAndOrgi(String pubstatus,String auditor,String orgi);
	
	public abstract List<EkmAudit> findByPubstatusAndDatastatusAndAuditorAndOrgi(String pubstatus,boolean datastatus,String auditor,String orgi);
	
	public abstract List<EkmAudit> findByPubstatusAndOrganAndOrgi(String pubstatus,String organ,String orgi);
}
