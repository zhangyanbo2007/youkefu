package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.KbsExpert;
import com.ukefu.webim.web.model.User;

public abstract interface KbsExpertRepository  extends JpaRepository<KbsExpert, String>
{
	
	public abstract Page<KbsExpert> findByOrgiAndKbstype(String orgi ,String kbstype,Pageable paramPageable);
	
	public abstract List<KbsExpert> findByOrgiAndKbstype(String orgi ,String kbstype);
	
	public abstract List<KbsExpert> findByOrgiAndUser(String orgi ,User user);
}

