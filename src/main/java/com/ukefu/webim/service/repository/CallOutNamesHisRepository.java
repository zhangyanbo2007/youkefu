package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallOutNamesHis;

public abstract interface CallOutNamesHisRepository extends JpaRepository<CallOutNamesHis, String> {
	
	public abstract CallOutNamesHis findByIdAndOrgi(String id, String orgi);
	
	public abstract List<CallOutNamesHis> findByNameAndOrgi(String name, String orgi);

	public abstract Page<CallOutNamesHis> findByActidAndOrgi(String actid , String orgi , Pageable page) ;
	
	public abstract List<CallOutNamesHis> findByActidAndOrgi(String actid , String orgi) ;
	
	public abstract List<CallOutNamesHis> findByDataidAndOrgi(String dataid , String orgi) ;
	
	public abstract List<CallOutNamesHis> findByDataidAndCreaterAndOrgi(String dataid ,String creater, String orgi) ;
	
	public abstract Page<CallOutNamesHis> findAll(Specification<CallOutNamesHis> spec, Pageable pageable);
	
	public abstract Page<CallOutNamesHis> findByCreaterAndOrgi(String creater , String orgi , Pageable page) ;
	
	public abstract Page<CallOutNamesHis> findByOrganAndOrgi(String organ , String orgi , Pageable page) ;
	
	public abstract Page<CallOutNamesHis> findByOrgi(String orgi , Pageable page) ;
	
	public abstract Page<CallOutNamesHis> findByOwneruserAndOrgi(String owneruser , String orgi , Pageable page) ;
	
	public abstract Page<CallOutNamesHis> findByOrgiAndDataidAndActidAndStatusNot(String orgi , String dataid ,  String actid ,String status, Pageable page) ;
	
}
