package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.PublishedCube;

public abstract interface PublishedCubeRepository extends JpaRepository<PublishedCube, String> {

	public List<PublishedCube> findByOrgiAndDataidOrderByDataversionDesc(String orgi, String dataid);

	public Page<PublishedCube> getByOrgiAndTypeid(String orgi, String typeid, Pageable pageRequest);

	public List<PublishedCube> findByIdAndOrgi(String id, String orgi);
	
	public List<PublishedCube> findById(String id);
	
	public Page<PublishedCube> getByOrgi(String orgi, Pageable pageRequest);
	
}
