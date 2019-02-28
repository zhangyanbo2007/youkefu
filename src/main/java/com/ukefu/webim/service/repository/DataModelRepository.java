package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.DataModel;

public abstract interface DataModelRepository extends JpaRepository<DataModel, String> {
	
	public abstract DataModel findByIdAndOrgi(String id, String orgi);

	public abstract List<DataModel> findByProidAndOrgi(String proid , String orgi ) ;
}
