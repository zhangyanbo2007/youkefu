package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallOutFilter;
import com.ukefu.webim.web.model.QualityFilter;

public abstract interface QualityFilterRepository extends JpaRepository<QualityFilter, String> {
	
	public abstract QualityFilter findByIdAndOrgi(String id, String orgi);
	
	public abstract List<QualityFilter> findByNameAndOrgi(String name, String orgi);

	public abstract Page<QualityFilter> findByActidAndOrgi(String actid , String orgi , Pageable page) ;
	
	public abstract List<QualityFilter> findByActidAndOrgi(String actid , String orgi) ;
	
	public abstract Page<QualityFilter> findByOrgi(String orgi , Pageable page) ;
	
	public abstract Page<QualityFilter> findAll(Specification<QualityFilter> spec, Pageable pageable);  
}
