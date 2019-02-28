package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallCenterSkill;

public interface CallCenterSkillRepository extends JpaRepository<CallCenterSkill, String> {
	
	public CallCenterSkill findByIdAndOrgi(String id , String orgi);
	public List<CallCenterSkill> findByHostidAndOrgi(String hostid , String orgi);
	
	public List<CallCenterSkill> findByOrgi(String orgi);
	
	public int countBySkillAndOrgi(String skill, String orgi) ;
	
	public int countByNameAndOrgiAndIdNot(String name, String orgi,String id) ;
	
	public int countByNameAndOrgi(String name, String orgi) ;
	
}
