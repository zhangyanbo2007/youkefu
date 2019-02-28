package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AiSNSAccount;

public abstract interface AiSNSAccountRepository  extends JpaRepository<AiSNSAccount, String>
{
	
	public abstract List<AiSNSAccount> findByOrgiAndAiid(String orgi ,String aiid);
	
	public abstract List<AiSNSAccount> findByOrgiAndSnsid(String orgi ,String snsid);
	
	public abstract List<AiSNSAccount> findByOrgi(String orgi);
}

