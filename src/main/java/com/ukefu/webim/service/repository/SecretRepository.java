package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Secret;

public abstract interface SecretRepository  extends JpaRepository<Secret, String>{
	public abstract List<Secret> findByOrgi(String orgi);
}

