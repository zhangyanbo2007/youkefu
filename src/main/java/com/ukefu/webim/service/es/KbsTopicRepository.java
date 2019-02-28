package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.web.model.KbsTopic;

public interface KbsTopicRepository extends  ElasticsearchRepository<KbsTopic, String> , KbsTopicEsCommonRepository {
	
}
