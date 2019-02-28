package com.ukefu.webim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.repository.SessionTypeRepository;
import com.ukefu.webim.web.model.SessionType;

@Service("sessiontype")
public class SessionTypeExchangeImpl implements DataExchangeInterface{
	@Autowired
	private SessionTypeRepository sessionTypeRes ;
	
	public String getDataByIdAndOrgi(String id, String orgi){
		SessionType sessionType = sessionTypeRes.findOne(id)  ; 
		return  sessionType!=null ? sessionType.getName() : "";
	}

	@Override
	public List<SessionType> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return sessionTypeRes.findByIdAndOrgi(id , orgi) ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
