package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SessionType;

public abstract interface SessionTypeRepository  extends JpaRepository<SessionType, String>
{
	
	public abstract List<SessionType> findByOrgiAndDicid(String orgi,String dicid);
	
	public abstract Page<SessionType> findByOrgiAndParentid(String orgi ,String parentid, Pageable page);
	
	public abstract List<SessionType> findByOrgiAndParentid(String orgi ,String parentid);
	
	public abstract Page<SessionType> findByOrgiAndParentidAndCtype(String orgi ,String parentid, String ctype, Pageable page);
	
	public abstract List<SessionType> findByCodeOrName(String code , String name);
	
	public abstract List<SessionType> findByIdAndOrgi(String id , String orgi);

	public abstract SessionType findById(String id);
	
	public abstract List<SessionType> findByDicidAndName(String dicid , String name);
	
	public abstract List<SessionType> findByOrgiAndCtype(String orgi , String ctype);
}

