package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.KbsType;

public abstract interface KbsTypeRepository extends JpaRepository<KbsType, String> {
	
	public abstract KbsType findByIdAndOrgi(String id, String orgi);

	public abstract int countByNameAndOrgi(String name, String orgi);
	
	public abstract List<KbsType> findByOrgi(String orgi) ;
	
	public abstract int countByOrgiAndNameAndParentid(String orgi , String name , String parentid) ;

}
