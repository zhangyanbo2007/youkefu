package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyResult;

public abstract interface QueSurveyResultRepository extends JpaRepository<QueSurveyResult, String>{

  //public abstract Page<QueSurveyResult> findByEventidAndProcessidAndOrgi(String eventid,String processid,String orgi ,Pageable paramPageable);
 
  public abstract QueSurveyResult findByEventidAndProcessidAndOrgi(String eventid,String processid,String orgi );

  public abstract QueSurveyResult findByIdAndOrgi(String id, String orgi);
  
  public abstract Page<QueSurveyResult> findAll(Specification<QueSurveyResult> spec, Pageable page) ;
  
}
