package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.AgentUserContacts;

public interface AgentUserContactsRepository extends JpaRepository<AgentUserContacts, String>{
	public List<AgentUserContacts> findByUseridAndOrgi(String userid , String orgi) ;
	
	public List<AgentUserContacts> findByContactsidAndOrgi(String contactsid, String orgi) ;
}
