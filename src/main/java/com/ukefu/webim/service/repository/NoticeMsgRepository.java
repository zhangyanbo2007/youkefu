package com.ukefu.webim.service.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.NoticeMsg;

public abstract interface NoticeMsgRepository extends JpaRepository<NoticeMsg,String>{

	public abstract NoticeMsg findByIdAndOrgi(String id, String orgi);
	
	public abstract List<NoticeMsg> findByNameAndOrgi(String Name, String orgi);

	public abstract Page<NoticeMsg> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<NoticeMsg> findByOrgi(String orgi) ;
	
	public abstract Page<NoticeMsg> findAll(Specification<NoticeMsg> spec, Pageable page) ;
	
	public abstract int countByTargetAndStatusAndDatastatusAndOrgi(String target , boolean status, boolean datastatus , String orgi);
	
	public abstract List<NoticeMsg> findByTerracetypeAndCreatetimeBefore(String terracetype  , Date createtime) ;
	
}
