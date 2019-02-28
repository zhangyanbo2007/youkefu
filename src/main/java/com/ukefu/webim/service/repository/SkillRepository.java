package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Skill;


/**
 * 
 * @author admin
 *
 */
public interface SkillRepository extends JpaRepository<Skill, String> {

    Skill findByIdAndOrgi(String id , String orgi);

    Page<Skill> findByOrgi(String orgi , Pageable pageable);
    
    List<Skill> findByOrgi(String orgi);

	Skill findByNameAndOrgi(String name , String orgi);
}
