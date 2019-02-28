package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyResultQuestion;

public abstract interface QueSurveyResultQuestionRepository extends JpaRepository<QueSurveyResultQuestion, String>{

  public abstract QueSurveyResultQuestion findByIdAndOrgi(String id, String orgi);
  
  public abstract Page<QueSurveyResultQuestion> findAll(Specification<QueSurveyResultQuestion> spec, Pageable page) ;
  
}
