package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Cube;

public abstract interface CubeRepository extends JpaRepository<Cube, String> {

	public Page<Cube> getByOrgiAndTypeid(String orgi, String typeid, Pageable pageRequest);

	public Page<Cube> getByOrgi(String orgi, Pageable pageRequest);

	public List<Cube> findByOrgiAndTypeid(String orgi, String typeid);
	
	public List<Cube> findByOrgiAndId(String orgi, String id);
}
