package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Scene;

public interface SceneRepository extends JpaRepository<Scene,String>{
	
	public abstract Page<Scene> findByOrgiAndCate(String orgi ,String cate , Pageable paramPageable);
}
