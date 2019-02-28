package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmHelpDeskTimes;

public abstract interface EkmHelpDeskTimesRepository
  extends JpaRepository<EkmHelpDeskTimes, String>
{
  
  public abstract EkmHelpDeskTimes findByOrgiAndKnowidAndVersionAndCreater(String orgi, String knowledgeid, int version, String creater);
  

  
}
