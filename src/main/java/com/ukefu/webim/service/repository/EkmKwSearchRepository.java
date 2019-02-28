package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKwSearch;

public abstract interface EkmKwSearchRepository
  extends JpaRepository<EkmKwSearch, String>
{
  public abstract EkmKwSearch findByIdAndOrgi(String id, String orgi);
  
  public abstract Page<EkmKwSearch> findByOrgi(String orgi , Pageable paramPageable);
  
  public abstract  EkmKwSearch findByConditionsAndOrgi(String conditions ,String orgi);
  

  
}
