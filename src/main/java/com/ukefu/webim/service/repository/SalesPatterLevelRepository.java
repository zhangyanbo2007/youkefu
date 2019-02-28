package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SalesPatterLevel;

public abstract interface SalesPatterLevelRepository extends JpaRepository<SalesPatterLevel, String>{

  public abstract Page<SalesPatterLevel> findByOrgi(String orgi ,Pageable paramPageable);

  public abstract List<SalesPatterLevel> findByOrgi(String orgi);
  
  public abstract SalesPatterLevel findByIdAndOrgi(String id , String orgi);
  
  public abstract List<SalesPatterLevel> findByProcessidAndOrgi(String processid , String orgi);
  
  public abstract List<SalesPatterLevel> findAll(Specification<SalesPatterLevel> spec) ;
}
