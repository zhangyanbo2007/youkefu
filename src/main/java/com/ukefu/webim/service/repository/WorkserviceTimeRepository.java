package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.WorkserviceTime;

public abstract interface WorkserviceTimeRepository
  extends JpaRepository<WorkserviceTime, String>
{
  public abstract WorkserviceTime findByIdAndOrgi(String id, String orgi);
  
  public abstract Page<WorkserviceTime> findByOrgi(String orgi , Pageable page);

  
  public abstract List<WorkserviceTime> findByOrgi(String orgi);
  
}
