package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.FormFilter;

public abstract interface FormFilterRepository extends JpaRepository<FormFilter, String> {
	
	public abstract FormFilter findByIdAndOrgi(String id, String orgi);
	
	public abstract List<FormFilter> findByNameAndOrgi(String name, String orgi);

	public abstract Page<FormFilter> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<FormFilter> findByOrgi(String orgi) ;
	
	public abstract Page<FormFilter> findAll(Specification<FormFilter> spec, Pageable page) ;
	
	public abstract List<FormFilter> findAll(Specification<FormFilter> spec) ;
}
