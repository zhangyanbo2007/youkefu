package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.DataProduct;

public abstract interface DataProductRepository extends JpaRepository<DataProduct, String> {
	
	public abstract DataProduct findByIdAndOrgi(String id, String orgi);

	public abstract Page<DataProduct> findByOrgi(String orgi , Pageable page) ;
	
	public abstract Page<DataProduct> findByOrgiAndNameLike(String orgi ,String name , Pageable page) ;
	
	public abstract int countByOrgiAndNameLike(String orgi ,String name) ;
}
