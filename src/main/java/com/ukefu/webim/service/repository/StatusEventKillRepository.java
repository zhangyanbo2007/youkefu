package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.StatusEventKill;

public interface StatusEventKillRepository extends JpaRepository<StatusEventKill, String> {

	public StatusEventKill findById(String id);
	
	public Page<StatusEventKill> findByAni(String ani , Pageable page) ;
	
	public Page<StatusEventKill> findByOrgi(String orgi , Pageable page) ;
	
	public Page<StatusEventKill> findByMisscallAndOrgi(boolean misscall ,String orgi , Pageable page) ;
	
	public Page<StatusEventKill> findByRecordAndOrgi(boolean record ,String orgi , Pageable page) ;
	
	public Page<StatusEventKill> findByCalledAndOrgi(String voicemail ,String orgi , Pageable page) ;
	
	public Page<StatusEventKill> findAll(Specification<StatusEventKill> spec, Pageable pageable);  //分页按条件查询 
}
