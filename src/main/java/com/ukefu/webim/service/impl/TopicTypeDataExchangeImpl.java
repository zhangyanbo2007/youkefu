package com.ukefu.webim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.repository.KnowledgeTypeRepository;
import com.ukefu.webim.web.model.KnowledgeType;

@Service("topictype")
public class TopicTypeDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private KnowledgeTypeRepository knowledgeTypeRes ;
	
	public KnowledgeType getDataByIdAndOrgi(String id, String orgi){
		return knowledgeTypeRes.findByIdAndOrgi(id, orgi) ;
	}

	@Override
	public List<KnowledgeType> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return knowledgeTypeRes.findByOrgi(orgi) ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
