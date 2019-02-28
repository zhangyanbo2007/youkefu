package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Media;

public interface MediaRepository extends JpaRepository<Media, String> {
	
	public Media findByIdAndOrgi(String id , String orgi);
	public List<Media> findByHostidAndOrgi(String hostid , String orgi);
	public int countByNameAndOrgi(String name, String orgi);
	
	public List<Media> findByOrgi(String orgi);
}
