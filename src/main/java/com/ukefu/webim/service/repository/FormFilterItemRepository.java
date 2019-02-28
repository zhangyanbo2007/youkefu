package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.FormFilterItem;

public abstract interface FormFilterItemRepository extends JpaRepository<FormFilterItem, String> {
	
	public abstract FormFilterItem findByIdAndOrgi(String id, String orgi);

	public abstract List<FormFilterItem> findByOrgiAndFormfilterid(String orgi , String formfilterid) ;

}
