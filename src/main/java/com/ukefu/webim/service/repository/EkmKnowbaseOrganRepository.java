package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKnowbaseOrgan;


public abstract interface EkmKnowbaseOrganRepository extends JpaRepository<EkmKnowbaseOrgan, String> {
	
	public abstract EkmKnowbaseOrgan findByIdAndOrgi(String id, String orgi);
	
	public abstract EkmKnowbaseOrgan findByOrganidAndKnowbaseidAndOrgi(String organid,String knowbaseid, String orgi);

	public abstract List<EkmKnowbaseOrgan> findByKnowbaseidAndOrgi(String knowbaseid,String orgi) ;
	
	public abstract List<EkmKnowbaseOrgan> findByOrganidAndOrgi(String organid,String orgi) ;
	
}
