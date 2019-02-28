package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.web.model.KbsTopicComment;

public interface KbsTopicCommentRepository extends  ElasticsearchRepository<KbsTopicComment, String> , KbsTopicCommentEsCommonRepository {
}
