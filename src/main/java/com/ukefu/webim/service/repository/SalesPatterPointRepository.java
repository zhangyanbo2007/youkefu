package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SalesPatterPoint;

public abstract interface SalesPatterPointRepository extends JpaRepository<SalesPatterPoint, String>{

  public abstract Page<SalesPatterPoint> findByOrgi(String orgi ,Pageable paramPageable);

  public abstract List<SalesPatterPoint> findByOrgi(String orgi);
  
  public abstract SalesPatterPoint findByIdAndOrgi(String id , String orgi);
  
  public abstract List<SalesPatterPoint> findByQuestionidAndOrgi(String questionid , String orgi);
  
  public abstract List<SalesPatterPoint> findAll(Specification<SalesPatterPoint> spec) ;
}
