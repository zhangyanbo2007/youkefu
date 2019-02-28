package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CubeType;

public abstract interface CubeTypeRepository extends JpaRepository<CubeType, String> {
	
	public abstract CubeType findByIdAndOrgi(String id, String orgi);

	public abstract List<CubeType> findByOrgi(String orgi) ;
	
	public abstract CubeType findByOrgiAndName(String orgi, String name);

}
