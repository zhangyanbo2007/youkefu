package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.FormFilter;
import com.ukefu.webim.web.model.Reporter;

public interface ReporterRepository extends  JpaRepository<Reporter, String> {
	
	public Page<Reporter> findByDataidAndOrgi(String dataid , String orgi , Pageable pageable) ;
	public Page<Reporter> findByOrgi(String orgi , Pageable pageable) ;
	public Reporter findByIdAndOrgi(String id ,String orgi ) ;
	public abstract Page<Reporter> findAll(Specification<Reporter> spec, Pageable page) ;
}
