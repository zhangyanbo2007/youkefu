package com.ukefu.webim.service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.DataDic;

public interface DataDicRepository extends JpaRepository<DataDic, String>{

	public abstract DataDic findByIdAndOrgi(String id, String orgi);

	public abstract List<DataDic> findByOrgi(String orgi);

	public abstract List<DataDic> findByOrgiAndCode(String orgi, String code);
	
	public abstract List<DataDic> findByOrgiAndName(String orgi, String name);
	
	public abstract List<DataDic> findByOrgiAndNameAndIdNot(String orgi, String name ,String id);

	public List<DataDic> findByOrgiAndProjectid(String orgi , String projectid);
}
