package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityResult;

public abstract interface QualityResultRepository extends JpaRepository<QualityResult,String>{
	
	public abstract Page<QualityResult> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<QualityResult> findByOrgi(String orgi) ;
	
	public abstract QualityResult findByIdAndOrgi(String id, String orgi);
	
	public abstract QualityResult findByDataidAndOrgi(String dataid, String orgi);
	
}
