package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SaleStatusType;

public abstract interface SaleStatusTypeRepository extends JpaRepository<SaleStatusType, String> {
	
	public abstract SaleStatusType findByIdAndOrgi(String id, String orgi);
	
	public abstract SaleStatusType findByNameAndOrgi(String name, String orgi);

	public abstract int countByNameAndOrgi(String name, String orgi);
	
	public abstract List<SaleStatusType> findByOrgi(String orgi) ;
	
	public abstract List<SaleStatusType> findByOrgiAndActivityid(String orgi, String activityid) ;
	
	public abstract SaleStatusType findByIdAndOrgiAndActivityid(String id, String orgi, String Activityid);
	
	public abstract List<SaleStatusType> findByOrgiAndActivityidAndParentid(String orgi, String activityid, String parentid) ;
}
