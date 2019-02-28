package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmDimensionType;

public interface EkmDimensionTypeRepository extends JpaRepository<EkmDimensionType,String>{
	
	public abstract EkmDimensionType findByIdAndOrgi(String id,String orgi);
	
	public abstract List<EkmDimensionType> findByParentAndDatastatusAndOrgi(String parent,boolean datastatus,String orgi);
	
	public abstract List<EkmDimensionType> findByDatastatusAndOrgi(boolean datastatus,String orgi);
	
	public abstract List<EkmDimensionType> findByDimensionidAndDatastatusAndOrgi(String dimensionid,boolean datastatus,String orgi);
	
	public abstract EkmDimensionType findByNameAndDimensionidAndDatastatusAndOrgi(String Name,String dimensionid,boolean datastatus,String orgi);

}
