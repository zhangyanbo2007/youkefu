package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.LeaveMsg;

public abstract interface LeaveMsgRepository  extends JpaRepository<LeaveMsg, String>
{
	public abstract Page<LeaveMsg> findByOrgi(String orgi , Pageable page);
	
	public List<LeaveMsg> findByOrgiAndMobile(String orgi , String mobile) ;
	
	public List<LeaveMsg> findByOrgiAndUserid(String orgi , String userid) ;
}

