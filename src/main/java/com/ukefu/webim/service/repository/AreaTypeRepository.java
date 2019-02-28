package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AreaType;

public abstract interface AreaTypeRepository extends JpaRepository<AreaType, String> {
	
	public abstract AreaType findByIdAndOrgi(String id, String orgi);

	public abstract int countByNameAndOrgi(String name, String orgi);
	
	public List<AreaType> findByOrgi(String orgi);

}
