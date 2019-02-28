package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Generation;

public abstract interface GenerationRepository  extends JpaRepository<Generation, String>{
	public abstract Generation findByOrgiAndModel(String orgi , String model);
	public abstract List<Generation> findByOrgi(String orgi);
}

