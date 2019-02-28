package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.RequestLog;

public abstract interface RequestLogRepository  extends JpaRepository<RequestLog, String>{
	public abstract Page<RequestLog> findByOrgi(String orgi, Pageable page);
	public abstract Page<RequestLog> findByOrgiAndUsername(String orgi , String username, Pageable page);
}
