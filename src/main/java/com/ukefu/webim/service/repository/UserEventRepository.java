package com.ukefu.webim.service.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukefu.webim.web.model.UserHistory;

/**
 * 
 * @author admin
 *
 */
public interface UserEventRepository extends JpaRepository<UserHistory, String> {
	
	@Query("select count(distinct ip) as ipnums, count(id) as pvnums from UserHistory where orgi = ?1 and createtime > ?2 and createtime < ?3")
	List<Object> findByOrgiAndCreatetimeRange(String orgi ,Date start , Date end);
	
	public Page<UserHistory> findBySessionidAndOrgi(String sessionid , String orgi , Pageable page) ;
}
