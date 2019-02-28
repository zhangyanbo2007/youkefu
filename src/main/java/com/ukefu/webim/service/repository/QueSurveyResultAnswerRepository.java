package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyResultAnswer;

public abstract interface QueSurveyResultAnswerRepository extends JpaRepository<QueSurveyResultAnswer, String>{

  public abstract Page<QueSurveyResultAnswer> findByResultidAndProcessidAndOrgi(String resultid,String processid,String orgi ,Pageable paramPageable);

  public abstract QueSurveyResultAnswer findByIdAndOrgi(String id, String orgi);
  
  public abstract List<QueSurveyResultAnswer> findByResultidAndProcessidAndOrgi(String resultid,String processid,String orgi);
  
}
