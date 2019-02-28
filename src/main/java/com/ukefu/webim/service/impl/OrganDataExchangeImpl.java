package com.ukefu.webim.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.web.model.Organ;

@Service("organdata")
public class OrganDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private OrganRepository organRes ;
	
	public String getDataByIdAndOrgi(String id, String orgi){
		Organ organ = organRes.findByIdAndOrgi(id, orgi) ;
		return organ!=null ? organ.getName() : id;
	}

	@Override
	public List<Serializable> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return null ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
