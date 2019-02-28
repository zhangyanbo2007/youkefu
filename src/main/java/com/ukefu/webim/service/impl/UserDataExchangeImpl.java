package com.ukefu.webim.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.model.User;

@Service("userdata")
public class UserDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private UserRepository userRes ;
	
	public String getDataByIdAndOrgi(String id, String orgi){
		User user = userRes.findById(id); 
		return user!=null ? user.getUsername() : id;
	}

	@Override
	public List<Serializable> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return null ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
