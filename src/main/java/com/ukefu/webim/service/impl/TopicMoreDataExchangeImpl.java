package com.ukefu.webim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.util.task.process.TopicProcess;
import com.ukefu.webim.service.repository.TopicItemRepository;
import com.ukefu.webim.web.model.TopicItem;

@Service("topicmore")
public class TopicMoreDataExchangeImpl implements DataExchangeInterface{
	@Autowired
	private TopicItemRepository topicItemRes ;
	
	private TopicProcess topicProcess = new TopicProcess();
	
	public TopicItem getDataByIdAndOrgi(String id, String orgi){
		return topicItemRes.findByIdAndOrgi(id, orgi) ;
	}

	@Override
	public List<TopicItem> getListDataByIdAndOrgi(String id , String creater, String orgi) {
		return null ;
	}
	
	public void process(Object data , String orgi) {
		topicProcess.process(data, orgi);
	}
}
