package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.NoticeTarget;

public abstract interface NoticeTargetRepository extends JpaRepository<NoticeTarget,String> {

	public abstract NoticeTarget findByIdAndOrgi(String id, String orgi);
	
	public abstract List<NoticeTarget> findByNameAndOrgi(String name, String orgi);
	
	public abstract List<NoticeTarget> findByNoticeidAndOrgi(String noticeid, String orgi);
	
	public abstract List<NoticeTarget> findByNoticeidAndCheckedAndOrgi(String noticeid,boolean checked, String orgi);
	
	public abstract List<NoticeTarget> findByNoticeidAndTargettypeAndOrgi(String noticeid, String targettype, String orgi);
	
	public abstract List<NoticeTarget> findByOrgi(String orgi) ;
	
	public abstract NoticeTarget findByNoticeidAndTargetAndOrgi(String noticeid, String target ,String orgi);
	
}
