package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Template;

public interface TemplateRepository extends JpaRepository<Template, String> {
	
	public Template findByIdAndOrgi(String id , String orgi);
	public List<Template> findByTemplettypeAndOrgi(String templettype , String orgi);
	public List<Template> findByOrgi(String orgi) ;
}
