package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SalesPatter;

public abstract interface SalesPatterRepository extends JpaRepository<SalesPatter, String>{

  public abstract Page<SalesPatter> findByOrgi(String orgi ,Pageable paramPageable);

  public abstract List<SalesPatter> findByOrgi(String orgi);
  
  public abstract SalesPatter findByIdAndOrgi(String id , String orgi);
}
