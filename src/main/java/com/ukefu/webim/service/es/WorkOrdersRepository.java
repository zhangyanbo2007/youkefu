package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.web.model.WorkOrders;

public interface WorkOrdersRepository extends  ElasticsearchRepository<WorkOrders, String> , WorkOrdersEsCommonRepository {
}
