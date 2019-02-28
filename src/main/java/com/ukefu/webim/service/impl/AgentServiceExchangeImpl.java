package com.ukefu.webim.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.QcDataExchangeInterface;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.web.model.AgentService;

@Service("qcagentservice")
public class AgentServiceExchangeImpl implements QcDataExchangeInterface<AgentService>{
	@Autowired
	private AgentServiceRepository agentServiceRes ;
	
	public String getDataByIdAndOrgi(String id, String orgi){
		AgentService agentService = agentServiceRes.findByIdAndOrgi(id , orgi) ;
		return agentService!=null ? agentService.getId() : id;
	}

	@Override
	public List<Serializable> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return null ;
	}
	
	public void process(Object data , String orgi) {
		if(data instanceof AgentService && !StringUtils.isBlank(((AgentService)data).getId()) && !StringUtils.isBlank(((AgentService)data).getOrgi())) {
			agentServiceRes.save((AgentService)data) ;
		}
	}

	@Override
	public Page<AgentService> load(Object query, PageRequest pageRequest) {
		return null;
	}

	@Override
	public AgentService get(String id, String orgi) {
		return agentServiceRes.findByIdAndOrgi(id , orgi);
	}
}
