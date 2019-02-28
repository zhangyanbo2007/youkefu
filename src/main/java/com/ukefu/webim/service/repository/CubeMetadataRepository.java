package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CubeMetadata;
import com.ukefu.webim.web.model.MetadataTable;

public abstract interface CubeMetadataRepository extends JpaRepository<CubeMetadata, String> {

	public List<CubeMetadata> findByCubeid(String cubeid); 
	
	public List<CubeMetadata> findByCubeidAndMtype(String cubeid, String mtype);

	public List<CubeMetadata> findByCubeidAndMtypeNot(String cubeid, String mtype);

	public int countByTbAndCubeid(MetadataTable tb, String cubeid); 
	
	public CubeMetadata findById(String id); 
}
