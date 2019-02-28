package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallOutFilter;

public abstract interface CallOutFilterRepository extends JpaRepository<CallOutFilter, String> {
	
	public abstract CallOutFilter findByIdAndOrgi(String id, String orgi);
	
	public abstract List<CallOutFilter> findByNameAndOrgi(String name, String orgi);

	public abstract Page<CallOutFilter> findByActidAndOrgi(String actid , String orgi , Pageable page) ;
	
	public abstract List<CallOutFilter> findByActidAndOrgi(String actid , String orgi) ;
	
	public abstract Page<CallOutFilter> findByOrgi(String orgi , Pageable page) ;
	
	public abstract Page<CallOutFilter> findAll(Specification<CallOutFilter> spec, Pageable pageable);  
}
