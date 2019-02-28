package com.ukefu.webim.service.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.BlackEntity;

public abstract interface BlackListRepository  extends JpaRepository<BlackEntity, String>{
	
	public abstract BlackEntity findByIdAndOrgi(String id ,String orgi);
	
	public abstract BlackEntity findByUseridAndOrgi(String userid , String orgi);
	
	public abstract Page<BlackEntity> findByOrgi(String orgi , Pageable page);
	
	public abstract Page<BlackEntity> findByEndtimeLessThan(Date endtime , Pageable page);
	
	public abstract List<BlackEntity> findByOrgi(String orgi);
	
	public int countByPhoneAndOrgi(String phone , String orgi) ;
}

