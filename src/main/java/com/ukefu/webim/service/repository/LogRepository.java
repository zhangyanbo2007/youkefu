package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Log;

public abstract interface LogRepository  extends JpaRepository<Log, String>{
	public abstract Page<Log> findByOrgi(String orgi, Pageable page);
	public abstract Page<Log> findByOrgiAndLevels(String orgi , String levels, Pageable page);
}
