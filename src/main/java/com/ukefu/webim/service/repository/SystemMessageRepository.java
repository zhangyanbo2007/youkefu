package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SystemMessage;

public interface SystemMessageRepository   extends JpaRepository<SystemMessage, String>{
	
	public abstract SystemMessage findByIdAndOrgi(String id , String orgi);
	
	public abstract Page<SystemMessage> findByMsgtypeAndOrgi(String msgtype ,String orgi, Pageable paramPageable);
	
	public abstract List<SystemMessage> findByMsgtypeAndOrgi(String msgtype ,String orgi);
	
	public abstract List<SystemMessage> findByOrgi(String orgi);
}
