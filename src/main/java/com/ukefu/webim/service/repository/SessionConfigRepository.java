package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SessionConfig;

public abstract interface SessionConfigRepository  extends JpaRepository<SessionConfig, String>
{
	public abstract SessionConfig findByOrgi(String orgi);
}

