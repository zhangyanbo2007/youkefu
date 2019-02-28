package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SkillExtention;

public interface SkillExtentionRepository extends JpaRepository<SkillExtention, String> {
	
	public SkillExtention findByIdAndOrgi(String id , String orgi);
	public List<SkillExtention> findByHostidAndOrgi(String hostid , String orgi);
	public List<SkillExtention> findByExtentionAndOrgi(String extention , String orgi);
	public List<SkillExtention> findBySkillidAndOrgi(String skillid,String orgi);
}
