package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyAnswer;

public abstract interface QueSurveyAnswerRepository extends JpaRepository<QueSurveyAnswer, String>{

  public abstract Page<QueSurveyAnswer> findByOrgi(String orgi ,Pageable paramPageable);

  public abstract List<QueSurveyAnswer> findByOrgi(String orgi);
  
  public abstract List<QueSurveyAnswer> findByOrgiAndId(String orgi, String id);
  
  public abstract QueSurveyAnswer findById(String id);
  
  public abstract List<QueSurveyAnswer> findByOrgiAndProcessid(String orgi ,String processid);
  
  public abstract List<QueSurveyAnswer> findByOrgiAndProcessidAndAnstype(String orgi ,String processid,String anstype);
  
  public abstract List<QueSurveyAnswer> findByOrgiAndQuestionidAndAnstype(String orgi ,String questionid,String anstype);
  
  public abstract List<QueSurveyAnswer> findByOrgiAndQuestionid(String orgi, String questionid);
  
  public abstract List<QueSurveyAnswer> findAll(Specification<QueSurveyAnswer> spec) ;
  
}
