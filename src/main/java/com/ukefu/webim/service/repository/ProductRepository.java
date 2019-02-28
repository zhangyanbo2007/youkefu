package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Product;

public abstract interface ProductRepository extends JpaRepository<Product, String> {
	
	public abstract Product findByIdAndOrgi(String id, String orgi);

	public abstract int countByTitleAndOrgi(String title, String orgi);
	
	public abstract List<Product> findByOrgi(String cate) ;
	
	public abstract List<Product> findByOrgiAndCate(String orgi, String cate) ;
	
	public abstract Product findByOrgiAndTitle(String orgi, String title) ;
	
	public abstract Page<Product> findByOrgiAndCate(String orgi, String cate, Pageable page) ;

}
