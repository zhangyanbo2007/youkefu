package com.ukefu.webim.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.es.ContactsRepository;
import com.ukefu.webim.web.model.Contacts;

@Service("contacts")
public class ContactsDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private ContactsRepository contactsRes ;
	
	public Contacts getDataByIdAndOrgi(String id, String orgi){
		return contactsRes.findOne(id) ;
	}

	@Override
	public List<Serializable> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return null ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
