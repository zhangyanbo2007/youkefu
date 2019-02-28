package com.ukefu.webim.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.UserTraceHistory;

/**
 * 
 * @author admin
 *
 */
public interface UserTraceRepository extends JpaRepository<UserTraceHistory, String> {
	
}
