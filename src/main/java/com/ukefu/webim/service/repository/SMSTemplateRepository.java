package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SmsTemplate;

public interface SMSTemplateRepository extends JpaRepository<SmsTemplate, String> {
	
	public SmsTemplate findByIdAndOrgi(String id , String orgi);
	public List<SmsTemplate> findByTemplettypeAndOrgi(String templettype , String orgi);
	public List<SmsTemplate> findByOrgi(String orgi) ;
}
