package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.FormFilter;
import com.ukefu.webim.web.model.QualityFormFilter;

public abstract interface QualityFormFilterRepository extends JpaRepository<QualityFormFilter, String> {
	
	public QualityFormFilter findByIdAndOrgi(String id, String orgi);

	public Page<QualityFormFilter> findByOrgi(String orgi, Pageable page);
	
	public abstract List<QualityFormFilter> findAll(Specification<QualityFormFilter> spec) ;
	
	public abstract Page<QualityFormFilter> findAll(Specification<QualityFormFilter> spec, Pageable page) ;
	
}
