package com.ukefu.webim.service.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.DataEvent;

public interface DataEventRepository extends JpaRepository<DataEvent, String>{
	
	public abstract Page<DataEvent> findByDataidAndOrgi(String dataid , String orgi , Pageable pageable);
	
	public abstract List<DataEvent> findByDataidAndOrgi(String dataid , String orgi);
}
