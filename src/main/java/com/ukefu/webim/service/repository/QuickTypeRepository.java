package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QuickType;

public abstract interface QuickTypeRepository extends JpaRepository<QuickType, String> {
	
	public abstract QuickType findByIdAndOrgi(String id, String orgi);

	public abstract int countByNameAndOrgi(String name, String orgi);
	
	/**
	 * 获取所有的公共分类
	 * @param orgi
	 * @param quicktype
	 * @return
	 */
	public abstract List<QuickType> findByOrgiAndQuicktype(String orgi , String quicktype) ;
	
	/**
	 * 获取个人分类
	 * @param orgi
	 * @param quicktype
	 * @param creater
	 * @return
	 */
	public abstract List<QuickType> findByOrgiAndQuicktypeAndCreater(String orgi , String quicktype , String creater) ;
	
	public abstract int countByOrgiAndNameAndParentid(String orgi , String name , String parentid) ;

	public abstract QuickType findByOrgiAndName(String orgi, String name);

}
