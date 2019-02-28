package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallOutTask;

public abstract interface CallOutTaskRepository extends JpaRepository<CallOutTask, String> {
	
	public abstract CallOutTask findByIdAndOrgi(String id, String orgi);
	
	public abstract List<CallOutTask> findByNameAndOrgi(String name, String orgi);

	public abstract Page<CallOutTask> findByActidAndOrgi(String actid , String orgi , Pageable page) ;
	
	public abstract List<CallOutTask> findByActidAndOrgi(String actid , String orgi) ;
	
	public abstract Page<CallOutTask> findAll(Specification<CallOutTask> spec, Pageable pageable);  
}
