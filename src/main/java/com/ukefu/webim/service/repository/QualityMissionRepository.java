package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.QualityMission;

public abstract interface QualityMissionRepository extends JpaRepository<QualityMission, String>{
	
	public Page<QualityMission> findByOrgi(String orgi, Pageable page);
	
	public QualityMission findByIdAndOrgi(String id ,String orgi);
	
	public abstract Page<QualityMission> findAll(Specification<QualityMission> spec, Pageable page) ;
}
