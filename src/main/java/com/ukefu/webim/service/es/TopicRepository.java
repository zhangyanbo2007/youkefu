package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.web.model.Topic;

public interface TopicRepository extends  ElasticsearchRepository<Topic, String> , TopicEsCommonRepository {
	
}
