package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.OrgiSkillRel;


/**
 * 
 * @author admin
 *
 */
public interface OrgiSkillRelRepository extends JpaRepository<OrgiSkillRel, String> {

	List<OrgiSkillRel> findByOrgi(String orgi);

	List<OrgiSkillRel> findBySkillid(String skillid);
}
