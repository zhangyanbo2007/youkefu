package com.ukefu.util.task.process;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.service.es.TopicRepository;
import com.ukefu.webim.service.repository.TopicItemRepository;
import com.ukefu.webim.web.model.Topic;
import com.ukefu.webim.web.model.TopicItem;

public class TopicProcess implements JPAProcess{
	
	private TopicRepository topicRes ;
	
	public TopicProcess(TopicRepository topicRes){
		this.topicRes = topicRes ;
	}
	
	public TopicProcess(){}

	@Override
	public void process(Object data) {
		Topic topic = (Topic) data ;
		if(topic != null && StringUtils.isNotBlank(topic.getTitle())) {
			topicRes.save(topic) ;
			this.process(data, topic.getOrgi());
		}
	}
	/**
	 * 只处理 类似问题
	 * @param data
	 * @param orgi
	 */
	public void process(Object data , String orgi) {
		Topic topic = (Topic) data ;
		if(topic.getSilimar()!=null && topic.getSilimar().size() > 0) {
			TopicItemRepository topicItemRes = UKDataContext.getContext().getBean(TopicItemRepository.class) ;
			List<TopicItem> topicItemList = topicItemRes.findByTopicid(topic.getId()) ;
			if(topicItemList!=null && topicItemList.size() > 0) {
				topicItemRes.delete(topicItemList);
			}
			topicItemList.clear(); 
			for(String item : topic.getSilimar()) {
				TopicItem topicItem = new TopicItem();
				topicItem.setTitle(item);
				topicItem.setTopicid(topic.getId());
				topicItem.setOrgi(topic.getOrgi());
				topicItem.setCreater(topic.getCreater());
				topicItem.setCreatetime(new Date());
				topicItemList.add(topicItem) ;
			}
			if(topicItemList.size() > 0) {
				topicItemRes.save(topicItemList) ;
			}
		}
	}

	@Override
	public void end() {
		
	}
}
