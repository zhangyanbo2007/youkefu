package com.ukefu.webim.service.es;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
import com.ukefu.webim.service.repository.EkmKnowbaseOrganRepository;
import com.ukefu.webim.service.repository.EkmKnowbaseRoleRepository;
import com.ukefu.webim.service.repository.UserRoleRepository;
import com.ukefu.webim.web.model.EkmKnowledge;
import com.ukefu.webim.web.model.User;

@Component
public class EkmKnowledgeRepositoryImpl implements EkmKnowledgeESRepository{
	
	
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
	public EkmKnowledge findByTitleAndOrgi(String title, String orgi) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("title", title)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledge> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledge.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledge.class ) ;
	    }
		
		return knowledgeList.getContent().get(0);
	}

	@Override
	public EkmKnowledge findByIdAndOrgi(String id, String orgi) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("id", id)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledge> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledge.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledge.class ) ;
	    }
		if (knowledgeList!=null && knowledgeList.getContent().size()>0) {
			return knowledgeList.getContent().get(0);
		}else {
			return null ;
		}
		
	}


	@Override
	public List<EkmKnowledge> findByOrgiAndDatastatus(String orgi, boolean datastatus) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("datastatus", datastatus)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledge> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledge.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledge.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	@Override
	public Page<EkmKnowledge> findByKnowbaseidAndKnowledgetypeidAndDatastatusAndOrgi(String knowbaseid,
			String knowledgetypeid, boolean datastatus, String orgi, Pageable page) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder1.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		//boolQueryBuilder.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		if(!StringUtils.isBlank(knowbaseid)){
			boolQueryBuilder.must(termQuery("knowbaseid" , knowbaseid)) ;
		}else{
			boolQueryBuilder.must(termQuery("knowbaseid" , UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
		}
		if(!StringUtils.isBlank(knowledgetypeid)){
			boolQueryBuilder.must(termQuery("knowledgetypeid" , knowledgetypeid)) ;
		}else{
			boolQueryBuilder.must(termQuery("knowledgetypeid" , UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
		}
		return processQuery(boolQueryBuilder , page);
	}

	@Override
	public List<EkmKnowledge> findByKnowbaseidAndDatastatusAndOrgi(String knowbaseid, boolean datastatus, String orgi) {
		
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
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledge> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledge.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledge.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	@Override
	public List<EkmKnowledge> findByCreaterAndDatastatusAndOrgi(String creater, boolean datastatus, String orgi) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		bq.must(QueryBuilders.termQuery("datastatus", datastatus)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledge> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledge.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledge.class ) ;
	    }
		
		return knowledgeList.getContent();
	}

	
	@Override
	public Page<EkmKnowledge> getByDimenidAndDatastatusAndOrgi(String dimenid,
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
	public Page<EkmKnowledge> getByDimentypeidAndDatastatusAndOrgi(
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
	
	private Page<EkmKnowledge> processQuery(BoolQueryBuilder boolQueryBuilder, Pageable page){
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
		
		searchQueryBuilder.withPageable(page) ;
		
		Page<EkmKnowledge> knowledgeList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledge.class)){
			knowledgeList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledge.class) ;
		}
		return knowledgeList;
	}


	@Override
	public Page<EkmKnowledge> findByDatastatusAndKnowbaseidAndOrgi(boolean datastatus, String knowbaseid, String orgi,
			Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder1.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		//boolQueryBuilder.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		
		if(!StringUtils.isBlank(knowbaseid)){
			boolQueryBuilder.must(termQuery("knowbaseid" , knowbaseid)) ;
		}else{
			boolQueryBuilder.must(termQuery("knowbaseid" , UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
		}
		
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public Page<EkmKnowledge> findByKnowtypeidAuth(boolean datastatus, List<String> ekmKnowledgeType,
			String knowbaseid, String orgi, Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("knowbaseid" , knowbaseid)) ;
		for(String id : ekmKnowledgeType){
			boolQueryBuilder1.should(termQuery("knowledgetypeid" ,id)) ;
		}
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		//boolQueryBuilder.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public Page<EkmKnowledge> findByKnowledge(BoolQueryBuilder boolQueryBuilder,boolean datastatus, List<String> ekmKnowledgeType, String orgi, User user,
			Pageable pageable) {
		BoolQueryBuilder boolQueryBuild = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		
		/*final List<String> knowbaseRoleList = new ArrayList<>();
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
		}*/
		if(user.isSuperuser() == true){
			boolQueryBuild.must(boolQueryBuilder1) ;
		}else{
			 
			if(ekmKnowledgeType.size() > 0){
				for(String id : ekmKnowledgeType){
					boolQueryBuilder1.should(termQuery("knowledgetypeid" ,id)) ;
				}
			}else{
				boolQueryBuilder1.must(termQuery("knowledgetypeid" ,UKDataContext.UKEFU_SYSTEM_NO_DAT)) ;
			}
		}
		
		
		boolQueryBuild.must(boolQueryBuilder1) ;
		boolQueryBuild.must(boolQueryBuilder) ;
		boolQueryBuild.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuild.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuild.must(termQuery("pubstatus" , UKDataContext.PubStatusEnum.PASS.toString())) ;//审核通过
		
		return processQuery(boolQueryBuild , pageable);
	}

	@Override
	public Page<EkmKnowledge> findBySearchKnowledge(boolean datastatus, String q, String tag, String knowledgetype,
			String orgi, User user, Date begin, Date end, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<EkmKnowledge> findByDatastatusAndOrgiAndOwn(boolean datastatus,
			String orgi, User user, String own, Pageable pageable) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		
		
		
		/*final List<String> knowbaseRoleList = new ArrayList<>();
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
		}*/
		
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(termQuery("own" ,own)) ;
		boolQueryBuilder.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(termQuery("creater" , user.getId())) ;
		return processQuery(boolQueryBuilder , pageable);
	}

	@Override
	public Page<EkmKnowledge> findByPubstatusAndDatastatusAndOrgiAndOwn(
			String pubstatus, boolean datastatus, String orgi, String own,User user,
			Pageable pageable) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
		boolQueryBuilder1.must(termQuery("pubstatus" , pubstatus)) ;
		boolQueryBuilder1.must(termQuery("datastatus" , datastatus)) ;
		boolQueryBuilder.must(boolQueryBuilder1) ;
		boolQueryBuilder.must(termQuery("orgi" ,orgi)) ;
		boolQueryBuilder.must(termQuery("own" ,own)) ;
		boolQueryBuilder.must(termQuery("creater" , user.getId())) ;
		return processQuery(boolQueryBuilder , pageable);
	}


}
