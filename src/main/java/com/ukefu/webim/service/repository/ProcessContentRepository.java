package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.ProcessContent;

public abstract interface ProcessContentRepository extends JpaRepository<ProcessContent, String> {
	
	public abstract ProcessContent findByIdAndOrgi(String id, String orgi);
	
	public abstract ProcessContent findByProcessidAndOrgi(String processid, String orgi);

	public abstract int countByNameAndOrgi(String name, String orgi);
	
	public abstract Page<ProcessContent> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<ProcessContent> findByProcesstypeAndOrgi(String processtype , String orgi) ;

}
