package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Dimension;

public abstract interface DimensionRepository extends JpaRepository<Dimension, String> {

	public List<Dimension> findByCubeid(String cubeid);

	public Dimension findByIdAndOrgi(String id,String orgi);
	
	public int countByFktable(String fktable);
	
	
}
