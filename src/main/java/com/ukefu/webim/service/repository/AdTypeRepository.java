package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AdType;

public abstract interface AdTypeRepository extends JpaRepository<AdType, String> {
	
	public abstract AdType findByIdAndOrgi(String id, String orgi);

	public abstract int countByNameAndOrgi(String name, String orgi);
	
	public List<AdType> findByOrgi(String orgi);
	
	public List<AdType> findByAdposAndOrgi(String adpos , String orgi);
	
	public Page<AdType> findByAdposAndOrgi(String adpos , String orgi,Pageable page);
}
