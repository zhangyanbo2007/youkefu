package com.ukefu.webim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;
import org.elasticsearch.search.aggregations.bucket.missing.InternalMissing;
import org.elasticsearch.search.aggregations.bucket.range.InternalRange;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.es.EkmDataBean;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.webim.service.repository.CallOutTaskRepository;
import com.ukefu.webim.service.repository.EkmDimensionRepository;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.model.CallOutTask;
import com.ukefu.webim.web.model.EkmDimension;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.TableProperties;
import com.ukefu.webim.web.model.User;

@Repository("esdataservice")
public class ESDataExchangeImpl{

	@Autowired
	private UserRepository userRes ;
	
	@Autowired
	private CallOutTaskRepository taskRes ;
	
	@Autowired
	private OrganRepository organRes ;
	
	@Autowired
	private EkmDimensionRepository ekmDimensionRes ;
	
	@Autowired
	private JobDetailRepository jobDetailRes ;
	
	public void saveIObject(UKDataBean dataBean) throws Exception {
		if(dataBean.getId() == null) {
			dataBean.setId((String) dataBean.getValues().get("id"));
		}
		this.saveBulk(dataBean).execute().actionGet() ;
	}
	/**
	 * 实时刷新，仅限于 回收到部门和回收到 公共使用，其他地方禁用
	 * @param dataBean
	 * @param refresh
	 * @throws Exception
	 */
	public void saveIObject(UKDataBean dataBean , boolean refresh) throws Exception {
		if(dataBean.getId() == null) {
			dataBean.setId((String) dataBean.getValues().get("id"));
		}
		this.saveBulk(dataBean).setRefresh(refresh).execute().actionGet() ;
	}
	/**
	 * @param dataBean
	 * @return
	 * @throws Exception
	 */
	public IndexRequestBuilder saveBulk(UKDataBean dataBean) throws Exception {
		IndexRequestBuilder indexRequestBuilder ;
		if(dataBean.getId() == null) {
			dataBean.setId((String) dataBean.getValues().get("id"));
		}
		if(!StringUtils.isBlank(dataBean.getType())) {
			indexRequestBuilder = UKDataContext.getTemplet().getClient().prepareIndex(UKDataContext.CALLOUT_INDEX,
					dataBean.getType(), dataBean.getId())
			.setSource(processValues(dataBean));
		}else {
			indexRequestBuilder = UKDataContext.getTemplet().getClient().prepareIndex(UKDataContext.CALLOUT_INDEX,
						dataBean.getTable().getTablename(), dataBean.getId())
				.setSource(processValues(dataBean));
		}
		return indexRequestBuilder ;
	}
	/**
	 * 处理数据，包含 自然语言处理算法计算 智能处理字段
	 * @param dataBean
	 * @return
	 * @throws Exception 
	 */
	private Map<String , Object> processValues(UKDataBean dataBean) throws Exception{
		Map<String , Object> values = new HashMap<String , Object>() ;
		if(dataBean.getTable()!=null) {
			for(TableProperties tp : dataBean.getTable().getTableproperty()){
				if(dataBean.getValues().get(tp.getFieldname())!=null){
					values.put(tp.getFieldname(), dataBean.getValues().get(tp.getFieldname())) ;
				}else if(tp.getDatatypename().equals("nlp") && dataBean.getValues()!=null){
					//智能处理， 需要计算过滤HTML内容，自动获取关键词、摘要、实体识别、情感分析、信息指纹 等功能
					values.put(tp.getFieldname(), dataBean.getValues().get(tp.getFieldname())) ;
				}else{
					values.put(tp.getFieldname(), dataBean.getValues().get(tp.getFieldname())) ;
				}
			}
		}else {
			values.putAll(dataBean.getValues());
		}
		return values ;
	}

	public void deleteIObject(UKDataBean dataBean ) throws Exception {
		if(dataBean.getTable()!=null){
			UKDataContext.getTemplet().getClient().prepareDelete(UKDataContext.CALLOUT_INDEX, dataBean.getTable().getTablename(), dataBean.getId()).setRefresh(true).execute().actionGet();
		}
	}
	/**
	 * 批量删除，单次最大删除 10000条
	 * @param query
	 * @param index
	 * @param type
	 * @throws Exception
	 */
	public void deleteByCon(QueryBuilder query ,String type) throws Exception {
		BulkRequestBuilder bulkRequest = UKDataContext.getTemplet().getClient().prepareBulk();  
	    SearchResponse response = UKDataContext.getTemplet().getClient().prepareSearch(UKDataContext.CALLOUT_INDEX).setTypes(type)  
	            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)  
	            .setQuery(query)  
	            .setFrom(0).setSize(10000).setExplain(true).execute().actionGet();  
	    if(response.getHits().getTotalHits() > 0) {
		    for(SearchHit hit : response.getHits()){  
		        String id = hit.getId();  
		        bulkRequest.add(UKDataContext.getTemplet().getClient().prepareDelete(UKDataContext.CALLOUT_INDEX, type, id).request());  
		    }  
		    bulkRequest.get();  
	    }
	}

	public void deleteById(String type , String id){
		if(!StringUtils.isBlank(type) && !StringUtils.isBlank(id)){
			UKDataContext.getTemplet().getClient()
			.prepareDelete(UKDataContext.CALLOUT_INDEX, type, id).execute().actionGet();
		}
	}
	
	
	public UKDataBean getIObjectByPK(UKDataBean dataBean , String id) {
		if(dataBean.getTable()!=null){
			GetResponse getResponse = UKDataContext.getTemplet().getClient()
					.prepareGet(UKDataContext.CALLOUT_INDEX,
							dataBean.getTable().getTablename(), dataBean.getId())
					.execute().actionGet();
			dataBean.setValues(getResponse.getSource());
			dataBean.setType(getResponse.getType());
		}else{
			dataBean.setValues(new HashMap<String,Object>());
		}
		
		return processDate(dataBean);
	}
	
	public UKDataBean getIObjectByPK(String type , String id) {
		UKDataBean dataBean = new UKDataBean() ;
		if(!StringUtils.isBlank(type)){
			GetResponse getResponse = UKDataContext.getTemplet().getClient()
					.prepareGet(UKDataContext.CALLOUT_INDEX,
							type, id)
					.execute().actionGet();
			dataBean.setValues(getResponse.getSource());
			dataBean.setType(getResponse.getType());
			dataBean.setId(getResponse.getId());
		}else{
			dataBean.setValues(new HashMap<String,Object>());
		}
		return dataBean;
	}
	
	public void updateIObject(UKDataBean dataBean) throws Exception {
		if(dataBean.getId() == null) {
			dataBean.setId((String) dataBean.getValues().get("id"));
		}
		UKDataBean oldDataBean = (UKDataBean) this.getIObjectByPK(dataBean , dataBean.getId()) ;
		
		for(TableProperties tp : dataBean.getTable().getTableproperty()){
			if(oldDataBean.getValues()!=null&&oldDataBean.getValues().get(tp.getFieldname())!=null){
				if(dataBean.getValues().get(tp.getFieldname())==null){
					dataBean.getValues().put(tp.getFieldname(), oldDataBean.getValues().get(tp.getFieldname())) ;
				}
			}
		}
		UKDataContext.getTemplet().getClient()
				.prepareUpdate(UKDataContext.CALLOUT_INDEX,
						dataBean.getTable().getTablename(), dataBean.getId()).setDoc(processValues(dataBean)).execute().actionGet();
	}

	/**
	 * 
	 * @param dataBean
	 * @param ps
	 * @param start
	 * @return
	 */
	public PageImpl<UKDataBean> findPageResult(QueryBuilder query,String index ,MetadataTable metadata, Pageable page , boolean loadRef) {
		return findAllPageResult(query, index, metadata, page, loadRef, metadata!=null ? metadata.getTablename() : null);
	}
	
	/**
	 * 
	 * @param dataBean
	 * @param ps
	 * @param start
	 * @return
	 */
	public PageImpl<UKDataBean> findAllPageResult(QueryBuilder query,String index ,MetadataTable metadata, Pageable page , boolean loadRef , String types) {
		List<UKDataBean> dataBeanList = new ArrayList<UKDataBean>() ;
		SearchRequestBuilder searchBuilder = UKDataContext.getTemplet().getClient().prepareSearch(UKDataContext.CALLOUT_INDEX);
		if(!StringUtils.isBlank(types)) {
			searchBuilder.setTypes(types) ;
		}
		
		int start = page.getPageSize() * page.getPageNumber();
		searchBuilder.setFrom(start).setSize(page.getPageSize());
		if(page!=null && page.getSort()!=null) {
			Iterator<Order> iterator = page.getSort().iterator();
			while(iterator.hasNext()) {
				Order order = iterator.next() ;
				searchBuilder.addSort(new FieldSortBuilder(order.getProperty()).unmappedType(order.getProperty().equals("createtime")? "long" : "string").order( order.isDescending() ? SortOrder.DESC : SortOrder.ASC)) ;
			}
		}
		SearchResponse response = searchBuilder.setQuery(query).execute().actionGet();
		List<String> users = new ArrayList<String>() , organs = new ArrayList<String>() , taskList = new ArrayList<String>(),batchList = new ArrayList<String>(),activityList = new ArrayList<String>();
		for(SearchHit hit : response.getHits().getHits()){
			UKDataBean temp = new UKDataBean() ;
			temp.setType(hit.getType());
			temp.setTable(metadata);
			temp.setValues(hit.getSource());
			temp.setId(hit.getId());
			dataBeanList.add(processDate(temp)) ;
			
			
			if(loadRef == true) {
				if(!StringUtils.isBlank((String)temp.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT))) {
					users.add((String)temp.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT)) ;
				}
				if(!StringUtils.isBlank((String)temp.getValues().get(UKDataContext.UKEFU_SYSTEM_ASSUSER))) {
					users.add((String)temp.getValues().get(UKDataContext.UKEFU_SYSTEM_ASSUSER)) ;
				}
				if(!StringUtils.isBlank((String)temp.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN))) {
					organs.add((String)temp.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN)) ;
				}
				if(!StringUtils.isBlank((String)temp.getValues().get("taskid"))) {
					taskList.add((String)temp.getValues().get("taskid")) ;
				}
				if(!StringUtils.isBlank((String)temp.getValues().get("batid"))) {
					batchList.add((String)temp.getValues().get("batid")) ;
				}
				if(!StringUtils.isBlank((String)temp.getValues().get("actid"))) {
					activityList.add((String)temp.getValues().get("actid")) ;
				}
			}
		}
		if(loadRef) {
			if(users.size() > 0) {
				List<User> userList = userRes.findAll(users) ;
				for(UKDataBean dataBean : dataBeanList) {
					String userid = (String)dataBean.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) ;
					if(!StringUtils.isBlank(userid)) {
						for(User user : userList) {
							if(user.getId().equals(userid)) {
								dataBean.setUser(user);
								break ;
							}
						}
					}
					String assuer = (String)dataBean.getValues().get(UKDataContext.UKEFU_SYSTEM_ASSUSER) ;
					if(!StringUtils.isBlank(assuer)) {
						for(User user : userList) {
							if(user.getId().equals(assuer)) {
								dataBean.setAssuser(user);
								break ;
							}
						}
					}
				}
			}
			if(organs.size() > 0) {
				List<Organ> organList = organRes.findAll(organs) ;
				for(UKDataBean dataBean : dataBeanList) {
					String organid = (String)dataBean.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) ;
					if(!StringUtils.isBlank(organid)) {
						for(Organ organ : organList) {
							if(organ.getId().equals(organid)) {
								dataBean.setOrgan(organ);
								break ;
							}
						}
					}
				}
			}
			if(taskList.size() > 0) {
				List<CallOutTask> callOutTaskList = taskRes.findAll(taskList) ;
				for(UKDataBean dataBean : dataBeanList) {
					String taskid = (String)dataBean.getValues().get("taskid") ;
					if(!StringUtils.isBlank(taskid)) {
						for(CallOutTask task : callOutTaskList) {
							if(task.getId().equals(taskid)) {
								dataBean.setTask(task);
								break ;
							}
						}
					}
				}
			}
		}
		return new PageImpl<UKDataBean>(dataBeanList,page , (int)response.getHits().getTotalHits());
	}
	
	
	/**
	 * 
	 * @param dataBean
	 * @param ps
	 * @param start
	 * @return
	 */
	public PageImpl<UKDataBean> findAllPageAggResult(QueryBuilder query,String aggField,Pageable page , boolean loadRef , String types) {
		List<UKDataBean> dataBeanList = new ArrayList<UKDataBean>() ;
		SearchRequestBuilder searchBuilder = UKDataContext.getTemplet().getClient().prepareSearch(UKDataContext.CALLOUT_INDEX);
		if(!StringUtils.isBlank(types)) {
			searchBuilder.setTypes(types) ;
		}
		
		int size = page.getPageSize() * (page.getPageNumber() + 1);
		searchBuilder.setFrom(0).setSize(0);
		
		AggregationBuilder<?> aggregition = AggregationBuilders.terms(aggField).field(aggField).size(size) ;
		aggregition.subAggregation(AggregationBuilders.terms("apstatus").field("apstatus")) ;
		aggregition.subAggregation(AggregationBuilders.terms("callstatus").field("callstatus")) ;
		aggregition.subAggregation(AggregationBuilders.range("incall.one").field("incall").addRange(0, 10000)) ;
		aggregition.subAggregation(AggregationBuilders.range("incall.two").field("incall").addRange(10000, 60000)) ;
		aggregition.subAggregation(AggregationBuilders.range("incall.three").field("incall").addRange(60000, 120000)) ;
		aggregition.subAggregation(AggregationBuilders.range("incall.four").field("incall").addRange(120000, Integer.MAX_VALUE)) ;
		
		aggregition.subAggregation(AggregationBuilders.range("calltimes.one").field("calltimes").addRange(0, 5)) ;
		aggregition.subAggregation(AggregationBuilders.range("calltimes.two").field("calltimes").addRange(5, 10)) ;
		aggregition.subAggregation(AggregationBuilders.range("calltimes.three").field("calltimes").addRange(10, Integer.MAX_VALUE)) ;

		aggregition.subAggregation(AggregationBuilders.terms("level").field("level")) ;
		
		aggregition.subAggregation(AggregationBuilders.range("focustimes.one").field("focustimes").addRange(0, 5)) ;
		aggregition.subAggregation(AggregationBuilders.range("focustimes.two").field("focustimes").addRange(5, 10)) ;
		aggregition.subAggregation(AggregationBuilders.range("focustimes.three").field("focustimes").addRange(10, 20)) ;
		aggregition.subAggregation(AggregationBuilders.range("focustimes.four").field("focustimes").addRange(20, Integer.MAX_VALUE)) ;
		
		//振铃时长
		aggregition.subAggregation(AggregationBuilders.range("ringtime.one").field("ringtime").addRange(0, 10000)) ;
		aggregition.subAggregation(AggregationBuilders.range("ringtime.two").field("ringtime").addRange(10000, 30000)) ;
		aggregition.subAggregation(AggregationBuilders.range("ringtime.three").field("ringtime").addRange(30000, 60000)) ;
		aggregition.subAggregation(AggregationBuilders.range("ringtime.four").field("ringtime").addRange(60000, Integer.MAX_VALUE)) ;
		
		
		//后处理时长
		aggregition.subAggregation(AggregationBuilders.missing("afterprocesstime.missing").field("afterprocesstime")) ;
		aggregition.subAggregation(AggregationBuilders.range("afterprocesstime.one").field("afterprocesstime").addRange(0, 10000)) ;
		aggregition.subAggregation(AggregationBuilders.range("afterprocesstime.two").field("afterprocesstime").addRange(10000, 30000)) ;
		aggregition.subAggregation(AggregationBuilders.range("afterprocesstime.three").field("afterprocesstime").addRange(30000, 60000)) ;
		aggregition.subAggregation(AggregationBuilders.range("afterprocesstime.four").field("afterprocesstime").addRange(60000, Integer.MAX_VALUE)) ;
				
		aggregition.subAggregation(AggregationBuilders.avg("incall.avg").field("incall")) ;
		
		aggregition.subAggregation(AggregationBuilders.max("incall.max").field("incall")) ;
		
		aggregition.subAggregation(AggregationBuilders.min("incall.min").field("incall")) ;
		
		aggregition.subAggregation(AggregationBuilders.avg("afterprocesstime.avg").field("afterprocesstime")) ;
		
		aggregition.subAggregation(AggregationBuilders.max("afterprocesstime.max").field("afterprocesstime")) ;
		
		aggregition.subAggregation(AggregationBuilders.min("afterprocesstime.min").field("afterprocesstime")) ;
		
		searchBuilder.addAggregation(aggregition);
		
		
		SearchResponse response = searchBuilder.setQuery(query).execute().actionGet();
		List<String> users = new ArrayList<String>() , organs = new ArrayList<String>() , taskList = new ArrayList<String>(),batchList = new ArrayList<String>(),activityList = new ArrayList<String>();
		
		if(response.getAggregations().get(aggField) instanceof Terms){
			Terms agg = response.getAggregations().get(aggField) ;
			if(agg!=null){
				if(loadRef == true) {
					if(aggField.equals(UKDataContext.UKEFU_SYSTEM_DIS_AGENT)) {
						users.add(agg.getName()) ;
					}
					if(aggField.equals(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN)) {
						organs.add(agg.getName()) ;
					}
					if(aggField.equals("taskid")) {
						taskList.add(agg.getName()) ;
					}
					if(aggField.equals("batid")) {
						batchList.add(agg.getName()) ;
					}
					if(aggField.equals("actid")) {
						activityList.add(agg.getName()) ;
					}
				}
				if(agg.getBuckets()!=null && agg.getBuckets().size()>0){
					for (Terms.Bucket entry : agg.getBuckets()) {
						UKDataBean dataBean = new UKDataBean();
						dataBean.getValues().put("id", entry.getKeyAsString()) ;
						dataBean.getValues().put(aggField, entry.getKeyAsString()) ;
						dataBean.setId(agg.getName());
						dataBean.setType(aggField);
						dataBean.getValues().put("total", entry.getDocCount()) ;
						
						for (Aggregation temp : entry.getAggregations()) {
							if(temp instanceof StringTerms) {
								StringTerms agg2  = (StringTerms) temp ;
								for (Terms.Bucket entry2 : agg2.getBuckets()) {
									dataBean.getValues().put(temp.getName()+"."+entry2.getKeyAsString(), entry2.getDocCount()) ;
								}
							}else if(temp instanceof InternalRange) {
								InternalRange<?, ?> range = (InternalRange<?, ?>) temp ;
								if(range.getBuckets().size() > 0) {
									InternalRange.Bucket data = (InternalRange.Bucket) range.getBuckets().get(0)  ;
									if(data!=null) {
										dataBean.getValues().put(range.getName(), data.getDocCount()) ;
									}
								}
							}else if(temp instanceof InternalAvg) {
								InternalAvg avg  = (InternalAvg) temp ;
								if(avg!=null) {
									dataBean.getValues().put(avg.getName(), avg.getValue()) ;
								}
							}else if(temp instanceof InternalMax) {
								InternalMax max  = (InternalMax) temp ;
								if(max!=null) {
									dataBean.getValues().put(max.getName(), max.getValue()) ;
								}
							}else if(temp instanceof InternalMin) {
								InternalMin min  = (InternalMin) temp ;
								if(min!=null) {
									dataBean.getValues().put(min.getName(), min.getValue()) ;
								}
							}else if(temp instanceof InternalMissing) {
								InternalMissing missing  = (InternalMissing) temp ;
								if(missing!=null) {
									dataBean.getValues().put(missing.getName(), missing.getDocCount()) ;
								}
							}
						}
						dataBeanList.add(dataBean) ;
					}
				}
			}
		}else if(response.getAggregations().get(aggField) instanceof InternalDateHistogram){
//			InternalDateHistogram agg = response.getAggregations().get(aggField) ;
//			long total = response.getHits().getTotalHits() ;
		}
		
		if(loadRef) {
			if(users.size() > 0) {
				List<User> userList = userRes.findAll(users) ;
				for(UKDataBean dataBean : dataBeanList) {
					String userid = (String)dataBean.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) ;
					if(!StringUtils.isBlank(userid)) {
						for(User user : userList) {
							if(user.getId().equals(userid)) {
								dataBean.setUser(user);
								break ;
							}
						}
					}
				}
			}
			if(organs.size() > 0) {
				List<Organ> organList = organRes.findAll(organs) ;
				for(UKDataBean dataBean : dataBeanList) {
					String organid = (String)dataBean.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) ;
					if(!StringUtils.isBlank(organid)) {
						for(Organ organ : organList) {
							if(organ.getId().equals(organid)) {
								dataBean.setOrgan(organ);
								break ;
							}
						}
					}
				}
			}
			if(taskList.size() > 0) {
				List<CallOutTask> callOutTaskList = taskRes.findAll(taskList) ;
				for(UKDataBean dataBean : dataBeanList) {
					String taskid = (String)dataBean.getValues().get("taskid") ;
					if(!StringUtils.isBlank(taskid)) {
						for(CallOutTask task : callOutTaskList) {
							if(task.getId().equals(taskid)) {
								dataBean.setTask(task);
								break ;
							}
						}
					}
				}
			}
		}
		return new PageImpl<UKDataBean>(dataBeanList,page , (int)response.getHits().getTotalHits());
	}
	
	
	
	/**
	 * 
	 * @param dataBean
	 */
	public UKDataBean processDate(UKDataBean dataBean) {
		return dataBean;
	}
	/**
	 * 知识库 - 首页数据聚合
	 */
	public PageImpl<EkmDataBean> findAllPageAggResultEkm(QueryBuilder query,Pageable page , boolean loadRef , String types) {
		List<EkmDataBean> dataBeanList = new ArrayList<EkmDataBean>() ;
		SearchRequestBuilder searchBuilder = UKDataContext.getTemplet().getClient().prepareSearch(UKDataContext.CALLOUT_INDEX);
		if(!StringUtils.isBlank(types)) {
			searchBuilder.setTypes(types) ;
		}
		
		searchBuilder.setFrom(0).setSize(0);
		
		searchBuilder.addAggregation(AggregationBuilders.terms("organ").field("organ")).addAggregation(AggregationBuilders.terms("creater").field("creater")).addAggregation(AggregationBuilders.terms("dimenid").field("dimenid"));
		
		
		SearchResponse response = searchBuilder.setQuery(query).execute().actionGet();
		List<String> users = new ArrayList<String>() 
				,	organs = new ArrayList<String>()
		,	dimenids = new ArrayList<String>();
		for(Object value : response.getAggregations()){
			
			if(value instanceof Terms){
				Terms agg = (Terms)value;
				if(agg!=null){
					
					if(agg.getBuckets()!=null && agg.getBuckets().size()>0){
						for (Terms.Bucket entry : agg.getBuckets()) {
							EkmDataBean dataBean = new EkmDataBean();
							dataBean.setId(entry.getKeyAsString());
							dataBean.setDocs((int)entry.getDocCount());
							dataBean.setType(agg.getName());
							if(loadRef == true) {
								if("creater".equals(agg.getName())) {//根据知识作者ID
									users.add( entry.getKeyAsString()) ;
								}
								if("organ".equals(agg.getName())) {//根据知识所属的部门ID
									organs.add( entry.getKeyAsString()) ;
								}
								if("dimenid".equals(agg.getName())) {//根据知识所属的维度ID
									dimenids.add( entry.getKeyAsString()) ;
								}
							}
							dataBeanList.add(dataBean) ;
						}
					}
				}
			}
		}
		
		if(loadRef) {
			if(users.size() > 0) {
				List<User> userList = userRes.findAll(users) ;
				for(EkmDataBean dataBean : dataBeanList) {
					String userid = dataBean.getId() ;
					if(!StringUtils.isBlank(userid)) {
						for(User user : userList) {
							if(user.getId().equals(userid)) {
								dataBean.setUser(user);
								break ;
							}
						}
					}
				}
			}
			if(organs.size() > 0) {
				List<Organ> organList = organRes.findAll(organs) ;
				for(EkmDataBean dataBean : dataBeanList) {
					String organid = dataBean.getId() ;
					if(!StringUtils.isBlank(organid)) {
						for(Organ organ : organList) {
							if(organ.getId().equals(organid)) {
								dataBean.setOrgan(organ);
								break ;
							}
						}
					}
				}
			}
			if(dimenids.size() > 0) {
				List<EkmDimension> dimenidsList = ekmDimensionRes.findAll(dimenids) ;
				for(EkmDataBean dataBean : dataBeanList) {
					String dimenid = dataBean.getId() ;
					if(!StringUtils.isBlank(dimenid)) {
						for(EkmDimension dimension : dimenidsList) {
							if(dimension.getId().equals(dimenid)) {
								dataBean.setEkmdimension(dimension);
								break ;
							}
						}
					}
				}
			}
			
		}
		return new PageImpl<EkmDataBean>(dataBeanList,page , (int)response.getHits().getTotalHits());
	}
	
	public PageImpl<UKDataBean> findAllPageAggResultBatchData(QueryBuilder query,Pageable page , boolean loadRef , String types,String aggField) {
		List<UKDataBean> dataBeanList = new ArrayList<UKDataBean>() ;
		SearchRequestBuilder searchBuilder = UKDataContext.getTemplet().getClient().prepareSearch(UKDataContext.CALLOUT_INDEX);
		if(!StringUtils.isBlank(types)) {
			searchBuilder.setTypes(types) ;
		}
		
		searchBuilder.setFrom(0).setSize(0);
		AggregationBuilder<?> aggregition = AggregationBuilders.terms(aggField).field(aggField) ;
		aggregition.subAggregation(AggregationBuilders.terms("status").field("status")) ;
		aggregition.subAggregation(AggregationBuilders.terms("validresult").field("validresult")) ;
		searchBuilder.addAggregation(aggregition) ;
		SearchResponse response = searchBuilder.setQuery(query).execute().actionGet();
		List<String> jobids = new ArrayList<String>() ;
		
		if(response.getAggregations().get(aggField) instanceof Terms){
			Terms agg = response.getAggregations().get(aggField) ;
			if(agg!=null){
				if(loadRef == true) {
					if(aggField.equals("batid")) {
						jobids.add(agg.getName()) ;
					}
				}
				if(agg.getBuckets()!=null && agg.getBuckets().size()>0){
					for (Terms.Bucket entry : agg.getBuckets()) {
						UKDataBean dataBean = new UKDataBean();
						dataBean.getValues().put("id", entry.getKeyAsString()) ;
						dataBean.getValues().put(aggField, entry.getKeyAsString()) ;
						dataBean.setId(agg.getName());
						dataBean.setType(aggField);
						dataBean.getValues().put("total", entry.getDocCount()) ;
						
						for (Aggregation temp : entry.getAggregations()) {
							if(temp instanceof StringTerms) {
								StringTerms agg2  = (StringTerms) temp ;
								for (Terms.Bucket entry2 : agg2.getBuckets()) {
									dataBean.getValues().put(temp.getName()+"."+entry2.getKeyAsString(), entry2.getDocCount()) ;
								}
							}else if(temp instanceof InternalRange) {
								InternalRange<?, ?> range = (InternalRange<?, ?>) temp ;
								if(range.getBuckets().size() > 0) {
									InternalRange.Bucket data = (InternalRange.Bucket) range.getBuckets().get(0)  ;
									if(data!=null) {
										dataBean.getValues().put(range.getName(), data.getDocCount()) ;
									}
								}
							}
						}
						dataBeanList.add(dataBean) ;
					}
				}
			}
		}
		
		if(loadRef) {
			if(jobids.size() > 0) {
				List<JobDetail> jobidsList = jobDetailRes.findAll(jobids) ;
				for(UKDataBean dataBean : dataBeanList) {
					String jobid = dataBean.getId() ;
					if(!StringUtils.isBlank(jobid)) {
						for(JobDetail job : jobidsList) {
							if(job.getId().equals(jobid)) {
								dataBean.setBatch(job);
								break ;
							}
						}
					}
				}
			}
			
		}
		return new PageImpl<UKDataBean>(dataBeanList,page , (int)response.getHits().getTotalHits());
	}
}
