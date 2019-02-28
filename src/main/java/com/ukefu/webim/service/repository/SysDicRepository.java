package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SysDic;

public abstract interface SysDicRepository extends JpaRepository<SysDic, String>{
	
	
	public abstract SysDic findById(String id);
	
	public abstract SysDic findByCode(String code);

	public abstract Page<SysDic> findAll(Pageable paramPageable);

	public abstract List<SysDic> findByCodeOrName(String code , String name);
	
	public abstract List<SysDic> findByDicidAndName(String dicid , String name);
	
	public abstract List<SysDic> findByParentidAndName(String parentid , String name);
	
	public abstract Page<SysDic> findByParentid(String type , Pageable paramPageable);
	
	public abstract List<SysDic> findByParentid(String type);

	public abstract List<SysDic> findByDicid(String id);
	public abstract Page<SysDic> findByDicid(String id, Pageable paramPageable);
	
	public int countByName(String name);
}
