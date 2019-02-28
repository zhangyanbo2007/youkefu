package com.ukefu.webim.service.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyProcess;

public abstract interface QueSurveyProcessRepository extends JpaRepository<QueSurveyProcess, String>{

  public abstract Page<QueSurveyProcess> findByOrgi(String orgi ,Pageable paramPageable);

  public abstract List<QueSurveyProcess> findByOrgi(String orgi);
  
  public abstract QueSurveyProcess findByIdAndOrgi(String id , String orgi);
}
