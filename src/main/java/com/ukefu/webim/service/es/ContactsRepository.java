package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.web.model.Contacts;

public interface ContactsRepository extends  ElasticsearchRepository<Contacts, String> , ContactsEsCommonRepository {
	public int countByPhoneAndOrgi(String phone,String orgi);
}
