package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.service.es.EkmKnowledgeESRepository;
import com.ukefu.webim.web.model.EkmKnowledge;

public abstract interface EkmKnowledgeRepository  extends  ElasticsearchRepository<EkmKnowledge, String> , EkmKnowledgeESRepository {
	

	
}

