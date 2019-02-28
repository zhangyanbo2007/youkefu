package com.ukefu.webim.service.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukefu.webim.web.model.WxMpEvent;

public abstract interface WxMpEventRepository extends JpaRepository<WxMpEvent, String>{
	
	public abstract WxMpEvent findByIdAndOrgi(String id, String orgi);
	
	@Query("select event, count(id) as users from WxMpEvent where orgi = ?1 and appid = ?2 and createtime > ?3 and createtime < ?4 group by event")
	List<Object> findByOrgiAndCreatetimeRangeForClient(String orgi , String appid , Date start , Date end);

}
