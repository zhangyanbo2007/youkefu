package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.TableProperties;

public abstract interface TablePropertiesRepository extends JpaRepository<TableProperties, String>{
	
	public abstract TableProperties findById(String id);

	public abstract List<TableProperties> findByDbtableid(String dbtableid) ;
	
	public abstract List<TableProperties> findByTablename(String tablename) ;
	
	public abstract List<TableProperties> findBySecfield(boolean secfield) ;
	
	public abstract TableProperties findByTablenameAndFieldname(String tablename,String fieldname) ;
	
	public abstract List<TableProperties> findByFieldnameAndTablename(String fieldname,String tablename) ;
	
	public abstract List<TableProperties> findByName(String name) ;
	
	public abstract List<TableProperties> findByFieldnameAndDbtableid(String fieldname,String dbtableid) ;
}
