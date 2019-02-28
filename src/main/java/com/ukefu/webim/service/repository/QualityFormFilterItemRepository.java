package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityFormFilterItem;

public abstract interface QualityFormFilterItemRepository extends JpaRepository<QualityFormFilterItem, String> {
	
	public abstract QualityFormFilterItem findByIdAndOrgi(String id, String orgi);

	public abstract List<QualityFormFilterItem> findByOrgiAndQcformfilterid(String orgi , String qcformfilterid) ;

}
