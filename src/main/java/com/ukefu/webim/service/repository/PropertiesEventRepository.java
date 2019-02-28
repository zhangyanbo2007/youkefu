package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.PropertiesEvent;

public interface PropertiesEventRepository extends JpaRepository<PropertiesEvent, String>{
	
	public abstract List<PropertiesEvent> findByDataid(String dataid);
	
}
