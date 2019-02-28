package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyQuestion;

public abstract interface QueSurveyQuestionRepository extends JpaRepository<QueSurveyQuestion, String>{

  public abstract Page<QueSurveyQuestion> findByProcessidAndOrgi(String processid,String orgi ,Pageable paramPageable);

  public abstract List<QueSurveyQuestion> findByOrgi(String orgi);
  
  public abstract QueSurveyQuestion findByOrgiAndId(String orgi, String id);
  
  public abstract QueSurveyQuestion findById(String id);
  
  public abstract List<QueSurveyQuestion> findByOrgiAndProcessid(String orgi ,String processid);
  
}
