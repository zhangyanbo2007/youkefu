package com.ukefu.webim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.es.TopicRepository;
import com.ukefu.webim.web.model.Topic;

@Service("topic")
public class TopicDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private TopicRepository topicRes ;
	
	public Topic getDataByIdAndOrgi(String id, String orgi){
		return topicRes.findOne(id) ;
	}

	@Override
	public List<Topic> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return topicRes.getTopicByTopAndOrgi(true,orgi , id , 0, 10).getContent() ;
	}
	
	public void process(Object data , String orgi) {
		
	}
}
