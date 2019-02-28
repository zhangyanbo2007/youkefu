package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.InviteRecord;

public abstract interface InviteRecordRepository extends JpaRepository<InviteRecord, String> {
	
	public abstract InviteRecord findByUseridAndAgentnoAndOrgi(String userid, String agentno , String orgi);
	
	public abstract Page<InviteRecord> findByUseridAndOrgi(String userid, String orgi , Pageable page);

}
