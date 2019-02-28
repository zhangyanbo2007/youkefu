package com.ukefu.webim.service.es;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.service.repository.EkmKnowbaseOrganRepository;
import com.ukefu.webim.service.repository.EkmKnowbaseRoleRepository;
import com.ukefu.webim.service.repository.UserRoleRepository;
import com.ukefu.webim.web.model.EkmKnowbaseOrgan;
import com.ukefu.webim.web.model.EkmKnowbaseRole;
import com.ukefu.webim.web.model.EkmKnowledgeMaster;
import com.ukefu.webim.web.model.User;
import com.ukefu.webim.web.model.UserRole;

@Component
public class EkmKnowledgeMasterRepositoryImpl implements EkmKnowledgeMasterESRepository{
	
	
	@Autowired
	private EkmKnowbaseOrganRepository ekmKnowbaseOrganRes ;	//查询知识库 - 部门授权
	
	@Autowired
	private UserRoleRepository userRoleRes ;	//用户- 角色关联
	
	@Autowired
	private EkmKnowbaseRoleRepository ekmKnowbaseRoleRes ;	//查询知识库 - 角色授权
	
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
		this.elasticsearchTemplate = elasticsearchTemplate;
    }

	@Override
	public EkmKnowledgeMaster findByTitleAndOrgi(String title, String orgi) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("title", title)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		
		return knowledgeList.getContent().get(0);
	}

	@Override
	public EkmKnowledgeMaster findByIdAndOrgi(String id, String orgi) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("id", id)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		if (knowledgeList!=null && knowledgeList.getContent().size()>0) {
			return knowledgeList.getContent().get(0);
		}else {
			return null ;
		}
	}

	@Override
	public Page<EkmKnowledgeMaster> findByDatastatusAndOrgi(boolean datastatus, String orgi,User user, Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		
		
		
		final List<String> knowbaseRoleList = new ArrayList<>();
		final List<String> knowbaseOrganList = new ArrayList<>();
		
		List<UserRole> userRoleList = userRoleRes.findByOrgiAndUser(orgi, user);
		for(UserRole userRole :userRoleList){
			List<EkmKnowbaseRole> tempRoleList = ekmKnowbaseRoleRes.findByRoleidAndOrgi(userRole.getRole().getId(), orgi);
			for(EkmKnowbaseRole knowbaseRole : tempRoleList){
				knowbaseRoleList.add(knowbaseRole.getKnowbaseid());
			}
		}
		
		List<EkmKnowbaseOrgan> tempOrganList = ekmKnowbaseOrganRes.findByOrganidAndOrgi(user.getOrgan(), orgi);
		for(EkmKnowbaseOrgan knowbaseOrgan : tempOrganList){
			knowbaseOrganList.add(knowbaseOrgan.getKnowbaseid());
		}
		
		if(knowbaseRoleList.size() > 0){
			for(String id : knowbaseRoleList){
				boolQueryBuilder1.should(termQuery("knowbaseid" , id));
			}
		}
		if(knowbaseOrganList.size() > 0){
			for(String id : knowbaseOrganList){
				boolQueryBuilder1.should(termQuery("knowbaseid" , id));
			}
		}
		
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public List<EkmKnowledgeMaster> findByOrgiAndDatastatus(String orgi, boolean datastatus) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("datastatus", datastatus)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withPageable(new PageRequest(0, 100000)) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	@Override
	public Page<EkmKnowledgeMaster> findByKnowbaseidAndKnowledgetypeidAndDatastatusAndOrgi(String knowbaseid,
			String knowledgetypeid, boolean datastatus, String orgi, BoolQueryBuilder ranyQueryBuilder, Pageable page) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(ranyQueryBuilder) ;
		//boolQueryBuilder.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		if(!StringUtils.isBlank(knowbaseid)){
			boolQueryBuilder.must(termQuery("knowbaseid" , knowbaseid)) ;
		}else{
			boolQueryBuilder.must(termQuery("knowbaseid" , UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
		}
		if(!StringUtils.isBlank(knowledgetypeid)){
//			boolQueryBuilder.must(termQuery("knowledgetypeid" , knowledgetypeid)) ;
			WildcardQueryBuilder wildcardqueryBuilder = QueryBuilders.wildcardQuery("attr", "*"+knowledgetypeid+"*");
			boolQueryBuilder.must(wildcardqueryBuilder) ;
		}else{
			boolQueryBuilder.must(termQuery("knowledgetypeid" , UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
		}
		return processQuery(boolQueryBuilder , page);
	}

	@Override
	public List<EkmKnowledgeMaster> findByKnowbaseidAndDatastatusAndOrgi(String knowbaseid, boolean datastatus, String orgi) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("datastatus", datastatus)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		if(!StringUtils.isBlank(knowbaseid)){
			bq.must(termQuery("knowbaseid" , knowbaseid)) ;
		}else{
			bq.must(termQuery("knowbaseid" , UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
		}
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withPageable(new PageRequest(0, 100000)) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	@Override
	public Page<EkmKnowledgeMaster> findByCreaterAndDatastatusAndOrgi(String creater, boolean datastatus, String orgi) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		bq.must(QueryBuilders.termQuery("datastatus", datastatus)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		
		return knowledgeList;
	}

	@Override
	public Page<EkmKnowledgeMaster> findByPubstatusAndDatastatusAndCreaterAndOrgi(String pubstatus, boolean datastatus,String creater, String orgi,
			Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder1.must(termQuery("pubstatus" , pubstatus)) ;
		boolQueryBuilder1.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(termQuery("creater" ,creater)) ;
		return processQuery(boolQueryBuilder , pageable);
	}
	
	@Override
	public Page<EkmKnowledgeMaster> getByDimenidAndDatastatusAndOrgi(String dimenid,
			boolean datastatus, String orgi, Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder1.must(QueryBuilders.wildcardQuery("dimenid", "*"+dimenid+"*"));
		boolQueryBuilder1.must(termQuery("datastatus" , datastatus));
		boolQueryBuilder.must(boolQueryBuilder1);
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public Page<EkmKnowledgeMaster> getByDimentypeidAndDatastatusAndOrgi(
			String dimentypeid, boolean datastatus, String orgi,
			Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder1.must(QueryBuilders.wildcardQuery("dimentypeid", "*"+dimentypeid+"*"));
		boolQueryBuilder1.must(termQuery("datastatus" , datastatus));
		boolQueryBuilder.must(boolQueryBuilder1);
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		return processQuery(boolQueryBuilder , pageable);
	}
	@SuppressWarnings("deprecation")
	private Page<EkmKnowledgeMaster> processQuery(BoolQueryBuilder boolQueryBuilder, Pageable page){
		
		//过滤掉已过期的知识
		QueryBuilder beginFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("begintime")).should(QueryBuilders.rangeQuery("begintime").to(new Date().getTime())) ;
		QueryBuilder endFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("endtime")).should(QueryBuilders.rangeQuery("endtime").from(new Date().getTime())) ;
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withFilter(QueryBuilders.boolQuery().must(beginFilter).must(endFilter)).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
		searchQueryBuilder.withHighlightFields(new Field("title") , new Field("content")) ;
		
		searchQueryBuilder.withPageable(page) ;
		
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class , new EKMResultMapper()) ;
		}
		
		return knowledgeList;
	}
	@SuppressWarnings("deprecation")
	private Page<EkmKnowledgeMaster> processQueryItem(BoolQueryBuilder boolQueryBuilder, Pageable page){
		
		//过滤掉已过期的知识
		//QueryBuilder beginFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("begintime")).should(QueryBuilders.rangeQuery("begintime").to(new Date().getTime())) ;
		//QueryBuilder endFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("endtime")).should(QueryBuilders.rangeQuery("endtime").from(new Date().getTime())) ;
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
		searchQueryBuilder.withHighlightFields(new Field("title") , new Field("content")) ;
		
		searchQueryBuilder.withPageable(page) ;
		
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class , new EKMResultMapper()) ;
		}
		
		return knowledgeList;
	}


	@Override
	public Page<EkmKnowledgeMaster> findByDatastatusAndKnowbaseidAndOrgi(boolean datastatus, String knowbaseid, String orgi, BoolQueryBuilder ranyQueryBuilder,
			Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(ranyQueryBuilder) ;
		//boolQueryBuilder.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		
		if(!StringUtils.isBlank(knowbaseid)){
			boolQueryBuilder.must(termQuery("knowbaseid" , knowbaseid)) ;
		}else{
			boolQueryBuilder.must(termQuery("knowbaseid" , UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
		}
		
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public Page<EkmKnowledgeMaster> findByKnowtypeidAuth(boolean datastatus, List<String> EkmKnowledgeMasterType,
			String knowbaseid, String orgi, BoolQueryBuilder ranyQueryBuilder, Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("knowbaseid" , knowbaseid)) ;
		for(String id : EkmKnowledgeMasterType){
			boolQueryBuilder1.should(termQuery("knowledgetypeid" ,id)) ;
		}
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(ranyQueryBuilder) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		//boolQueryBuilder.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public Page<EkmKnowledgeMaster> findByKnowledge(BoolQueryBuilder boolQueryBuilder,boolean datastatus, List<String> ktList, String orgi, User user,
			Pageable pageable) {
		BoolQueryBuilder bool = new BoolQueryBuilder();
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("orgi" , orgi)) ;
		if(user.isSuperuser() == true){
			boolQueryBuilder.must(bool) ;
		}else{
			 
			if(ktList.size() > 0){
				for(String id : ktList){
					bool.should(termQuery("knowledgetypeid" ,id)) ;
				}
			}else{
				bool.must(termQuery("knowledgetypeid" ,UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
			}
			boolQueryBuilder.must(bool) ;
		}
	
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public List<EkmKnowledgeMaster> findByOrganAndDatastatusAndOrgi(
			String organ, boolean datastatus, String orgi) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("organ", organ)) ;
		bq.must(QueryBuilders.termQuery("datastatus", datastatus)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withPageable(new PageRequest(0, 100000)) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	@Override
	public Page<EkmKnowledgeMaster> findByDatastatusAndOrgi(
			boolean datastatus, String orgi, Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		return processQueryItem(boolQueryBuilder , pageable);
	}

	@Override
	public List<EkmKnowledgeMaster> findByOrgi(String orgi) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withPageable(new PageRequest(0, 100000)) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	@Override
	public List<EkmKnowledgeMaster> findByDatastatusAndOrgi(BoolQueryBuilder boolQueryBuilder,boolean datastatus,
			String orgi) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("datastatus", datastatus)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withPageable(new PageRequest(0, 100000)) ;
		Page<EkmKnowledgeMaster> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeMaster.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeMaster.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	@Override
	public Page<EkmKnowledgeMaster> findBySearchKnowledge(boolean datastatus,
			String q,String knowbaseid, String knowledgetype, String orgi, Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		if(!StringUtils.isBlank(q)){
			q = q.replaceAll("(OR|AND|NOT|:|\\(|\\))", "") ;
			boolQueryBuilder1.must(QueryBuilders.boolQuery().must(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND))) ;
		}
		if (!StringUtils.isBlank(knowledgetype)) {
			boolQueryBuilder.must(termQuery("knowledgetype" , knowledgetype)) ;
		}
		if (!StringUtils.isBlank(knowbaseid)) {
			boolQueryBuilder.must(termQuery("knowbaseid" , knowbaseid)) ;
		}
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(boolQueryBuilder1) ;
		//boolQueryBuilder.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public Page<EkmKnowledgeMaster> findByAllKnowledge(boolean datastatus, String orgi, Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		
		return this.processQueryItem(boolQueryBuilder, pageable);
	}

}
