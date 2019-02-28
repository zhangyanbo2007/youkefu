package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;

public abstract interface MetadataRepository extends JpaRepository<MetadataTable, String>{
	public abstract MetadataTable findById(String id);
	
	public abstract MetadataTable findByTablename(String tablename);

	public abstract List<MetadataTable> findByTablenameIn(List<String> tablename);
	
	public abstract Page<MetadataTable> findAll(Pageable paramPageable);
	
	public abstract int countByTablename(String tableName) ;

	public abstract List<MetadataTable> findByOrgi(String orgi);
	
	public abstract List<MetadataTable> findAll(Specification<MetadataTable> spec) ;
	
	public abstract Page<MetadataTable> findByPid(String pid , Pageable paramPageable);
	
	public abstract List<MetadataTable> findByPid(String pid );
}
