package com.ukefu.webim.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.repository.TagRepository;
import com.ukefu.webim.web.model.Tag;

@Service("servicedata")
public class ServiceDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private TagRepository tagRes ;
	
	public String getDataByIdAndOrgi(String id, String orgi){
		Tag tag = tagRes.findByOrgiAndId(orgi, id) ;
		return tag!=null ? tag.getTag() : id;
	}

	@Override
	public List<Serializable> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return null ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
