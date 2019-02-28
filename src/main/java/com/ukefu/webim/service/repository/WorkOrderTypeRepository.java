package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.WorkOrderType;

public abstract interface WorkOrderTypeRepository extends JpaRepository<WorkOrderType, String> {
	
	public abstract WorkOrderType findByIdAndOrgi(String id, String orgi);

	public abstract int countByNameAndOrgi(String name, String orgi);
	
	public abstract List<WorkOrderType> findByOrgi(String orgi) ;
	
	public abstract Page<WorkOrderType> findByOrgi(String orgi, Pageable page) ;

}
