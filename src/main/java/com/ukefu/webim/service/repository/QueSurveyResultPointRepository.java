package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyResultPoint;

public abstract interface QueSurveyResultPointRepository extends JpaRepository<QueSurveyResultPoint, String>{

  public abstract QueSurveyResultPoint findByIdAndOrgi(String id, String orgi);
  
}
