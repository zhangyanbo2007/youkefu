package com.ukefu.webim.service.es;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.Date;
import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.HasParentQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.EkmKnowledgeTimes;
import com.ukefu.webim.web.model.User;

@Component
public class EkmKnowledgeTimesRepositoryImpl implements EkmKnowledgeTimesESRepository{

	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
		this.elasticsearchTemplate = elasticsearchTemplate;
    }
	
	@Override
	public List<EkmKnowledgeTimes> findByKbidAndVersionAndOrgi(String kbid,
			int version, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("kbid", kbid)) ;
		bq.must(QueryBuilders.termQuery("version", version)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeTimes> knowledgeTimesList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeTimes.class)){
			knowledgeTimesList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeTimes.class ) ;
	    }
		
		return knowledgeTimesList.getContent();
	}

	@Override
	public Page<EkmKnowledgeTimes> findByOrgi(String orgi, Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		
		boolQueryBuilder.must(QueryBuilders.termQuery("orgi", orgi)) ;
		HasParentQueryBuilder hasParentQueryBuilder=QueryBuilders.hasParentQuery("uk_ekm_kb_master",QueryBuilders.termQuery("datastatus", false));
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("viewtimes").unmappedType("long").order(SortOrder.DESC)).withQuery(hasParentQueryBuilder);
		searchQueryBuilder.withPageable(pageable) ;
		Page<EkmKnowledgeTimes> knowledgeTimesList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeTimes.class)){
			knowledgeTimesList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeTimes.class ) ;
	    }
		
		return knowledgeTimesList;
	}
	
	public void delete(List<EkmKnowledgeTimes> ekmKnowledgeTimes){
		BulkRequestBuilder bulkRequest = elasticsearchTemplate.getClient().prepareBulk();  
	    if(ekmKnowledgeTimes != null && ekmKnowledgeTimes.size()>0) {
		    for(EkmKnowledgeTimes time : ekmKnowledgeTimes){  
		        bulkRequest.add(new DeleteRequest().index(UKDataContext.SYSTEM_INDEX).type("uk_ekm_kb_times").id(time.getId()).routing(time.getKbid()));  
		    }  
		    bulkRequest.get();  
	    }
	}

	@Override
	public List<EkmKnowledgeTimes> findByKbidAndOrgi(String kbid, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("kbid", kbid)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeTimes> knowledgeTimesList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeTimes.class)){
			knowledgeTimesList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeTimes.class ) ;
	    }
		
		return knowledgeTimesList.getContent();
	}

	@Override
	public Page<EkmKnowledgeTimes> findByOrgi(String orgi, User user,
			List<String> ekmKnowledgeMasterid, Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		if(user.isSuperuser() != true){
			if(ekmKnowledgeMasterid.size() > 0){
				for(String id : ekmKnowledgeMasterid){
					bq.should(termQuery("kbid" ,id)) ;
				}
			}else{
				bq.must(termQuery("kbid" ,UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
			}
		}
		boolQueryBuilder.must(bq) ;
		boolQueryBuilder.must(QueryBuilders.termQuery("orgi", orgi)) ;
		//过滤掉已过期的知识
		QueryBuilder beginFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("begintime")).should(QueryBuilders.rangeQuery("begintime").to(new Date().getTime())) ;
		QueryBuilder endFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("endtime")).should(QueryBuilders.rangeQuery("endtime").from(new Date().getTime())) ;
		BoolQueryBuilder parentbq = QueryBuilders.boolQuery();
		parentbq.must(beginFilter) ;
		parentbq.must(endFilter) ;
		parentbq.must(QueryBuilders.termQuery("datastatus", false)) ;
		HasParentQueryBuilder hasParentQueryBuilder=QueryBuilders.hasParentQuery("uk_ekm_kb_master",parentbq);
		boolQueryBuilder.must(hasParentQueryBuilder) ;
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("viewtimes").unmappedType("long").order(SortOrder.DESC));
		searchQueryBuilder.withPageable(pageable) ;
		Page<EkmKnowledgeTimes> knowledgeTimesList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeTimes.class)){
			knowledgeTimesList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeTimes.class ) ;
	    }
		
		return knowledgeTimesList;
	}
}
