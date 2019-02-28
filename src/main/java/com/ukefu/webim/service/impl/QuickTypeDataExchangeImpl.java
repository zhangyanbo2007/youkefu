package com.ukefu.webim.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.repository.QuickTypeRepository;
import com.ukefu.webim.web.model.QuickType;

@Service("quicktypedata")
public class QuickTypeDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private QuickTypeRepository quickTypeRes ;
	
	public String getDataByIdAndOrgi(String id, String orgi){
		QuickType quickType = quickTypeRes.findByIdAndOrgi(id,orgi); 
		return quickType!=null ? quickType.getName() : id;
	}

	@Override
	public List<Serializable> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return null ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
