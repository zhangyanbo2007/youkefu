package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.service.es.EntCustomerEsCommonRepository;
import com.ukefu.webim.web.model.EntCustomer;

public interface EntCustomerRepository extends  ElasticsearchRepository<EntCustomer, String> , EntCustomerEsCommonRepository {
}
