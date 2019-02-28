package com.ukefu.webim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.webim.service.repository.AgentUserRepository;
import com.ukefu.webim.web.model.AgentUser;

@Service
public class AgentUserService {
	
	@Autowired
	private AgentUserRepository agentUserRepository;
	
	public AgentUser findByUseridAndOrgi(String userid , String orgi){
		List<AgentUser> agentUsers = agentUserRepository.findByUseridAndOrgi(userid , orgi)  ;
		return agentUsers.size()> 0 ? agentUsers.get(0) : null ;
	}
	
	public void save(AgentUser agentUser){
		agentUserRepository.save(agentUser) ;
	}
	
	public void delete(String id){
		agentUserRepository.delete(id);
	}
}
