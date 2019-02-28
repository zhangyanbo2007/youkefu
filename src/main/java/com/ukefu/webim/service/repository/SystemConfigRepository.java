package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SystemConfig;

public abstract interface SystemConfigRepository  extends JpaRepository<SystemConfig, String>
{
	public abstract SystemConfig findByOrgi(String orgi);
}

