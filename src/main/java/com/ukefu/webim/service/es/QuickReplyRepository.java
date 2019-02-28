package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.service.es.QuickReplyEsCommonRepository;
import com.ukefu.webim.web.model.QuickReply;

public interface QuickReplyRepository extends  ElasticsearchRepository<QuickReply, String> , QuickReplyEsCommonRepository {
	
}
