package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityTemplate;

public abstract interface QualityTemplateRepository extends JpaRepository<QualityTemplate,String>{

	public abstract Page<QualityTemplate> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<QualityTemplate> findByOrgi(String orgi) ;
	
	public abstract QualityTemplate findByIdAndOrgi(String id, String orgi);
	
	public abstract QualityTemplate findByNameAndOrgi(String name, String orgi);
	
	public abstract List<QualityTemplate> findByTypeAndStatusAndOrgi(String type , String status, String orgi) ;
}
