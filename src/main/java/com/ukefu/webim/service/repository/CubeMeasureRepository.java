package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CubeMeasure;

public abstract interface CubeMeasureRepository extends JpaRepository<CubeMeasure, String> {

	public List<CubeMeasure> findByCubeid(String id);

	public int countByTablename(String tablename);
	
	
}
