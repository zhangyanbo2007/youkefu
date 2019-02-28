package com.ukefu.util.es;

import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.service.es.EkmKnowledgeMasterRepository;
import com.ukefu.webim.service.es.EkmKnowledgeTimesRepository;
import com.ukefu.webim.service.es.WorkOrdersRepository;
import com.ukefu.webim.service.impl.ESDataExchangeImpl;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.service.repository.StatusEventRepository;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.EkmDimension;
import com.ukefu.webim.web.model.EkmKnowledgeMaster;
import com.ukefu.webim.web.model.EkmKnowledgeTimes;
import com.ukefu.webim.web.model.FormFilter;
import com.ukefu.webim.web.model.FormFilterItem;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.QualityFormFilterItem;
import com.ukefu.webim.web.model.StatusEvent;
import com.ukefu.webim.web.model.User;
import com.ukefu.webim.web.model.WorkOrders;

public class SearchTools {
	
	public static PageImpl<UKDataBean> search(String orgi , FormFilter formFilter , List<FormFilterItem> itemList , MetadataTable metadataTable , boolean loadRef , int p, int ps){
		return search(query(orgi, formFilter, itemList, metadataTable, loadRef, p, ps), metadataTable, loadRef, p, ps);
	}
	
	public static PageImpl<UKDataBean> dissearch(String orgi , FormFilter formFilter , List<FormFilterItem> itemList , MetadataTable metadataTable , int p, int ps){
		BoolQueryBuilder queryBuilder = query(orgi, formFilter, itemList, metadataTable, false, p, ps) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.NOT.toString())) ;
		queryBuilder.must(termQuery("validresult", "valid")) ;
		return search(queryBuilder, metadataTable, false, p, ps);
	}
	
	public static PageImpl<UKDataBean> recoversearch(String orgi , String cmd ,String id, MetadataTable metadataTable , int p, int ps){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.mustNot(termQuery("status", UKDataContext.NamesDisStatusType.NOT.toString())) ;
		queryBuilder.must(termQuery("validresult", "valid")) ;
		
		switch(cmd) {
			//case "actid" : queryBuilder.must(termQuery("actid", id)) ; break ;
			case "batid" : queryBuilder.must(termQuery("batid", id)) ; 
					BoolQueryBuilder queryBuilder1 = new BoolQueryBuilder();
					queryBuilder1.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString()));
					queryBuilder1.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.WASTE.toString()));
					queryBuilder.must(queryBuilder1) ;break ;
			case "batsuccess" :queryBuilder.must(termQuery("batid", id)) ; 
								queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.SUCCESS.toString()));break ;
			case "batfailed" : queryBuilder.must(termQuery("batid", id)) ; 
								queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.FAILD.toString()));break ;
			case "baterror" : queryBuilder.must(termQuery("batid", id)) ; 
								queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.ERROR.toString()));break ;
			//case "taskid" : queryBuilder.must(termQuery("taskid", id)) ; break ;
			//case "filterid" : queryBuilder.must(termQuery("filterid", id)) ; break ;
			case "agent" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, id)) ;
					BoolQueryBuilder queryBuilder3 = new BoolQueryBuilder();
					queryBuilder3.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString()));
					queryBuilder3.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.WASTE.toString()));
					queryBuilder.must(queryBuilder3) ;break ;
			case "agentsuccess" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, id)) ; 
								queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.SUCCESS.toString()));break ;
			case "agentfailed" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, id)) ; 
								queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.FAILD.toString()));break ;
			case "agenterror" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, id)) ; 
								queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.ERROR.toString()));break ;
			case "forecast" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, id)) ;
					BoolQueryBuilder queryBuilder2 = new BoolQueryBuilder();
					queryBuilder2.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString()));
					queryBuilder2.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.WASTE.toString()));
					queryBuilder.must(queryBuilder2) ;break ;
			case "forecastsuccess" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, id)) ; 
							queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.SUCCESS.toString()));break ;
			case "forecastfailed" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, id)) ; 
							queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.FAILD.toString()));break ;
			case "forecasterror" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, id)) ; 
							queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.ERROR.toString()));break ;
			case "ai" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AI, id)) ;
					BoolQueryBuilder queryBuilder4 = new BoolQueryBuilder();
					queryBuilder4.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString()));
					queryBuilder4.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.WASTE.toString()));
					queryBuilder.must(queryBuilder4) ;break ;
			case "aisuccess" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AI, id)) ; 
								queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.SUCCESS.toString()));break ;
			case "aifailed" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AI, id)) ;
							queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.FAILD.toString()));break ;
			case "aierror" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AI, id)) ;
						queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.ERROR.toString()));break ;
			case "skill" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, id)) ; 
					BoolQueryBuilder queryBuilder5 = new BoolQueryBuilder();
					queryBuilder5.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString()));
					queryBuilder5.should(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.WASTE.toString()));
					queryBuilder.must(queryBuilder5) ;break ;
			case "skillnot" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, id)) ; //未分配
					queryBuilder.should(termQuery("status", UKDataContext.NamesDisStatusType.NOT.toString()));break ;
			case "skillsuccess" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, id)) ; 
						queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.SUCCESS.toString()));break ;
			case "skillfailed" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, id)) ;
						queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.FAILD.toString()));break ;
			case "skillerror" : queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, id)) ;
						queryBuilder.must(termQuery("callstatus", UKDataContext.NamesCalledEnum.ERROR.toString()));break ;
			//case "taskskill" : queryBuilder.must(termQuery("taskid", id)).must(termQuery("status", UKDataContext.NamesDisStatusType.DISAGENT.toString())) ; break ;
			//case "filterskill" : queryBuilder.must(termQuery("filterid", id)).must(termQuery("status", UKDataContext.NamesDisStatusType.DISAGENT.toString())) ; break ;
			default : queryBuilder.must(termQuery("actid", "NOT_EXIST_KEY")) ;  //必须传入一个进来;
		}
		
		return search(queryBuilder, metadataTable, false, p, ps);
	}
	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> agentsearch(String orgi ,boolean excludeCalled ,  String agent , int p, int ps){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		if(excludeCalled){
			queryBuilder.must(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString())) ;
		}
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, agent)) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.DISAGENT.toString())) ;
		
		return search(queryBuilder, p, ps);
	}
	
	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> agentapsearch(String orgi , String agent , int p, int ps){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, agent)) ;
		queryBuilder.must(termQuery("apstatus", true)) ;		//预约状态
		
		queryBuilder.must(rangeQuery("aptime").to(System.currentTimeMillis())) ;		//预约状态
		
		return search(queryBuilder, p, ps);
	}
	
	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> aisearch(String orgi , int p, int ps){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.must(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString())) ;
		
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.DISAI.toString())) ;
		
		return search(queryBuilder, p, ps);
	}
	/**
	 * 通过ai id 查询
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> aisearch(String orgi ,String aiid, int p, int ps){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.must(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString())) ;
		
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.DISAI.toString())) ;
		queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_AI,aiid));
		return search(queryBuilder, p, ps);
	}
	
	/**
	 * 通过ai id 查询
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> forecastsearch(String orgi ,String queueid, int p, int ps){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.must(termQuery("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString())) ;
		
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.DISFORECAST.toString())) ;
		queryBuilder.must(termQuery(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST,queueid));
		return search(queryBuilder, p, ps);
	}
	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> ainamesearch(String orgi , String phonenum){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.DISAI.toString())) ;
		StringBuffer strb = new StringBuffer();
		if(!StringUtils.isBlank(phonenum)) {
			if(phonenum.startsWith("0")) {
				strb.append(phonenum.substring(1)) ;
			}else {
				strb.append(phonenum) ;
			}
		}else {
			strb.append(UKDataContext.UKEFU_SYSTEM_NO_DAT) ;
		}
		queryBuilder.must(new QueryStringQueryBuilder(strb.toString()).defaultOperator(Operator.OR) );
		return search(queryBuilder,0, 1);
	}

	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> aiidsearch(String orgi , String id){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.DISAI.toString())) ;
		StringBuffer strb = new StringBuffer();
		if(!StringUtils.isBlank(id)) {
			strb.append(id) ;
		}else {
			strb.append(UKDataContext.UKEFU_SYSTEM_NO_DAT) ;
		}
		queryBuilder.must(termQuery("id",strb.toString())) ;
		return search(queryBuilder,0, 1);
	}
	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> namesearch(String orgi , String phonenum){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		queryBuilder.must(termQuery("validresult", "valid")) ;
		queryBuilder.must(termQuery("status", UKDataContext.NamesDisStatusType.DISAGENT.toString())) ;
		StringBuffer strb = new StringBuffer();
		if(!StringUtils.isBlank(phonenum)) {
			strb.append(phonenum) ;
			if(phonenum.startsWith("0")) {
				strb.append(" ").append(phonenum.substring(1)) ;
			}
		}else {
			strb.append(UKDataContext.UKEFU_SYSTEM_NO_DAT) ;
		}
		queryBuilder.must(new QueryStringQueryBuilder(strb.toString()).defaultOperator(Operator.OR) );
		return search(queryBuilder,0, 1);
	}
	
	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> search(BoolQueryBuilder queryBuilder, int p, int ps){
		return search(queryBuilder, null, true, p, ps);
	}
	/**
	 * 
	 * @param orgi
	 * @param agent
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> search(BoolQueryBuilder queryBuilder, int p, int ps , String sort){
		return search(queryBuilder, null, true, p, ps , sort);
	}
	/**
	 * 
	 * @param queryBuilder
	 * @param metadataTable
	 * @param loadRef
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> search(BoolQueryBuilder queryBuilder , MetadataTable metadataTable , boolean loadRef , int p, int ps){
		return search(queryBuilder, metadataTable, loadRef, p, ps, "createtime");
	}
	
	/**
	 * 
	 * @param queryBuilder
	 * @param metadataTable
	 * @param loadRef
	 * @param p
	 * @param ps
	 * @return
	 */
	private static PageImpl<UKDataBean> search(BoolQueryBuilder queryBuilder , MetadataTable metadataTable , boolean loadRef , int p, int ps , String sort){
		ESDataExchangeImpl esDataExchange = UKDataContext.getContext().getBean(ESDataExchangeImpl.class);
		return esDataExchange.findPageResult(queryBuilder, UKDataContext.CALLOUT_INDEX, metadataTable, new PageRequest(p, ps , Sort.Direction.ASC, sort) , loadRef) ;
	}
	
	/**
	 * 
	 * @param queryBuilder
	 * @param metadataTable
	 * @param loadRef
	 * @param p
	 * @param ps
	 * @return
	 */
	public static PageImpl<UKDataBean> aggregation(BoolQueryBuilder queryBuilder , String aggField, boolean loadRef , int p, int ps){
		ESDataExchangeImpl esDataExchange = UKDataContext.getContext().getBean(ESDataExchangeImpl.class);
		return esDataExchange.findAllPageAggResult(queryBuilder , aggField ,  new PageRequest(p, ps , Sort.Direction.ASC, "createtime") , loadRef , null) ;
	}
	
	/**
	 * 
	 * @param queryBuilder
	 * @param metadataTable
	 * @param loadRef
	 * @param p
	 * @param ps
	 * @return
	 */
	public static UKDataBean get(UKDataBean dataBean){
		ESDataExchangeImpl esDataExchange = UKDataContext.getContext().getBean(ESDataExchangeImpl.class);
		return esDataExchange.getIObjectByPK(dataBean, dataBean.getId());
	}
	
	/**
	 * 
	 * @param queryBuilder
	 * @param metadataTable
	 * @param loadRef
	 * @param p
	 * @param ps
	 * @return
	 */
	public static UKDataBean get(String type, String id){
		ESDataExchangeImpl esDataExchange = UKDataContext.getContext().getBean(ESDataExchangeImpl.class);
		return esDataExchange.getIObjectByPK(type, id);
	}
	
	/**
	 * 
	 * @param queryBuilder
	 * @param metadataTable
	 * @param loadRef
	 * @param p
	 * @param ps
	 * @return
	 */
	public static void save(UKDataBean dataBean){
		ESDataExchangeImpl esDataExchange = UKDataContext.getContext().getBean(ESDataExchangeImpl.class);
		try {
			esDataExchange.saveIObject(dataBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * EKM 知识库首页 - 数据聚合
	 */
	public static PageImpl<EkmDataBean> aggregationEkm(BoolQueryBuilder queryBuilder , boolean loadRef , int p, int ps){
		ESDataExchangeImpl esDataExchange = UKDataContext.getContext().getBean(ESDataExchangeImpl.class);
		return esDataExchange.findAllPageAggResultEkm(queryBuilder ,   new PageRequest(p, ps , Sort.Direction.ASC, "createtime") , loadRef , "uk_ekm_kb_master") ;
	}
	
	/**
	 * EKM 知识库维度
	 */
	public static List<EkmDataBean> sumEkmDimension(BoolQueryBuilder queryBuilder ,String orgi,User user, List<EkmDimension> ekmDimensionList){
		
		EkmKnowledgeMasterRepository ekmKnowledgeEsRes = UKDataContext.getContext().getBean(EkmKnowledgeMasterRepository.class) ;
		EkmKnowledgeTimesRepository ekmKnowledgeTimesRes = UKDataContext.getContext().getBean(EkmKnowledgeTimesRepository.class) ;
		List<EkmDataBean> dataBeanList = new ArrayList<EkmDataBean>() ;
		if (ekmDimensionList!=null && ekmDimensionList.size()>0) {
			for(EkmDimension dimension : ekmDimensionList){
				int collectResult = 0 ;
				int viewnumResult = 0 ;
				BoolQueryBuilder boolQueryBuild = QueryBuilders.boolQuery();
				boolQueryBuild.must(QueryBuilders.wildcardQuery("dimenid", "*"+dimension.getId()+"*"));
				List<EkmKnowledgeMaster> ekmKnowledgeList = ekmKnowledgeEsRes.findByDatastatusAndOrgi(boolQueryBuild,false,orgi) ;
				if (ekmKnowledgeList!=null && ekmKnowledgeList.size()>0) {
					for(EkmKnowledgeMaster knowledgeMaster : ekmKnowledgeList){
						List<EkmKnowledgeTimes> ekmKnowledgeTimesList = ekmKnowledgeTimesRes.findByKbidAndOrgi(knowledgeMaster.getId(), orgi) ;
						if (ekmKnowledgeTimesList!=null && ekmKnowledgeTimesList.size()>0) {
							collectResult = ekmKnowledgeTimesList.get(0)!=null?collectResult+ekmKnowledgeTimesList.get(0).getCollectimes():0 ;
							viewnumResult = ekmKnowledgeTimesList.get(0)!=null?viewnumResult+ekmKnowledgeTimesList.get(0).getViewtimes():0;
						}
					}
				}
				dimension.setCollectnum(collectResult);
				dimension.setViewnum(viewnumResult);
				EkmDataBean ekmDataBean = new EkmDataBean() ;
				ekmDataBean.setEkmdimension(dimension);
				ekmDataBean.setType("dimenid");
				dataBeanList.add(ekmDataBean) ;
			}
		}
		return dataBeanList;
	}
	/**
	 * 	查询参与质检的通话记录
	 * @param orgi
	 * @param qcFormFilterItemList
	 * @return
	 */
	public static List<StatusEvent> searchQualityStatusEvent(final String orgi, final List<QualityFormFilterItem> qcFormFilterItemList, User user){
		StatusEventRepository statusEventRes = UKDataContext.getContext().getBean(StatusEventRepository.class);
		Page<StatusEvent> page = statusEventRes.findAll(new Specification<StatusEvent>(){
			@Override
			public Predicate toPredicate(Root<StatusEvent> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  //and关系
				List<Predicate> inlist = new ArrayList<Predicate>();  //or关系
				
				list.add(cb.equal(root.get("orgi").as(String.class), orgi)) ;
				list.add(cb.equal(root.get("record").as(boolean.class), true)) ;
				list.add(cb.and(cb.or(cb.and(root.get("qualitydistype").as(String.class).isNull()),cb.equal(root.get("qualitydistype").as(String.class), UKDataContext.QualityDisStatusType.NOT.toString())))) ;
				
				if(qcFormFilterItemList.size() > 0 && qcFormFilterItemList.get(0).getField() != null) {
					try {
						for(QualityFormFilterItem formFilterItem : qcFormFilterItemList) {
							if(formFilterItem.getField().equals("q")) {
								list.add(cb.like(root.get("discaller").as(String.class), "%" + formFilterItem.getValue() + "%")) ;
							}else {
								Number number = null ;
								if(!StringUtils.isBlank(formFilterItem.getValue()) && formFilterItem.getValue().matches("[+-.]{0,1}[\\d.]{1,}")) {
									number = NumberFormat.getInstance().parse(formFilterItem.getValue()) ;
								}
								if(number == null){
									number = 0;
								}
								switch(formFilterItem.getCond()) {
									case "01" : 
										if("AND".equals(formFilterItem.getComp())) {
											list.add(cb.gt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}else if("OR".equals(formFilterItem.getComp())) {
											inlist.add(cb.gt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}
										break ;
									case "02" : 
										if("AND".equals(formFilterItem.getComp())) {
											list.add(cb.ge(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}else if("OR".equals(formFilterItem.getComp())) {
											inlist.add(cb.ge(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}
										break ;
									case "03" : 
										if("AND".equals(formFilterItem.getComp())) {
											list.add(cb.lt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}else if("OR".equals(formFilterItem.getComp())) {
											inlist.add(cb.lt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}
										break ;
									case "04" : 
										if("AND".equals(formFilterItem.getComp())) {
											list.add(cb.le(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}else if("OR".equals(formFilterItem.getComp())) {
											inlist.add(cb.le(root.get(formFilterItem.getField()).as(Number.class), number)) ;
										}
										break ;
									case "05" : 
										if("AND".equals(formFilterItem.getComp())) {
											list.add(cb.equal(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
										}else if("OR".equals(formFilterItem.getComp())) {
											inlist.add(cb.equal(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
										}
										break ;
									case "06" : 
										if("AND".equals(formFilterItem.getComp())) {
											list.add(cb.notEqual(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
										}else if("OR".equals(formFilterItem.getComp())) {
											inlist.add(cb.notEqual(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
										}
										break ;
									case "07" : 
										if("AND".equals(formFilterItem.getComp())) {
											list.add(cb.like(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
										}else if("OR".equals(formFilterItem.getComp())) {
											inlist.add(cb.like(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
										}
										break ;
									default :
										break ;
								}
							}
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Predicate[] p = new Predicate[list.size()];  
				Predicate[] inp = new Predicate[inlist.size()]; 
				
				Predicate pb = null;
				if(inp != null && inp.length > 0) {
					pb = cb.and(list.toArray(p)).in(inlist.toArray(inp));
				}else {
					pb = cb.and(list.toArray(p));
				}
				
			    return pb; 
			}}, new PageRequest(0, 10000 , Sort.Direction.DESC, "starttime")) ;
		return page.getContent();
	}
	/**
	 * 查询参与质检的工单
	 * @param orgi
	 * @param qcFormFilterItemList
	 * @return
	 */
	public static List<WorkOrders> searchQualityWorkOrders(String orgi, List<QualityFormFilterItem> qcFormFilterItemList, User user){
		
		WorkOrdersRepository workOrdersRes = UKDataContext.getContext().getBean(WorkOrdersRepository.class);
		//工单质检
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		//筛选未分配质检的工单
		BoolQueryBuilder bool = new BoolQueryBuilder();
		bool.should(termQuery("qualitydistype",UKDataContext.QualityDisStatusType.DISAGENT.toString()));
		bool.should(termQuery("qualitydistype",UKDataContext.QualityDisStatusType.DISORGAN.toString()));
		boolQueryBuilder.mustNot(bool);
		
		//权限控制
		if(qcFormFilterItemList.size() > 0 && qcFormFilterItemList.get(0).getField() != null) {
			BoolQueryBuilder orBuilder = new BoolQueryBuilder();
			int orNums = 0 ;
			for(QualityFormFilterItem formFilterItem : qcFormFilterItemList) {
				QueryBuilder tempQueryBuilder = null ;
				if(formFilterItem.getField().equals("q")) {
					tempQueryBuilder = new QueryStringQueryBuilder(formFilterItem.getValue()).defaultOperator(Operator.AND) ;
				}else {
					switch(formFilterItem.getCond()) {
					case "01" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).from(formFilterItem.getValue()).includeLower(false) ;
						break ;
					case "02" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).from(formFilterItem.getValue()).includeLower(true) ;
						break ;
					case "03" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).to(formFilterItem.getValue()).includeUpper(false) ;
						break ;
					case "04" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).to(formFilterItem.getValue()).includeUpper(true) ;
						break ;
					case "05" : 
						tempQueryBuilder = termQuery(formFilterItem.getField() , formFilterItem.getValue()) ;
						break ;
					case "06" : 
						tempQueryBuilder = termQuery(formFilterItem.getField() , formFilterItem.getValue()) ;
						break ;
					case "07" : 
						tempQueryBuilder = new QueryStringQueryBuilder(formFilterItem.getValue()).field(formFilterItem.getField()).defaultOperator(Operator.AND) ;
						break ;
					default :
						break ;
					}
				}
				if("AND".equalsIgnoreCase(formFilterItem.getComp())) {
					if("06".equals(formFilterItem.getCond())) {
						boolQueryBuilder.mustNot(tempQueryBuilder) ;
					}else {
						boolQueryBuilder.must(tempQueryBuilder) ;
					}
				}else {
					orNums ++ ;
					if("06".equals(formFilterItem.getCond())) {
						orBuilder.mustNot(tempQueryBuilder) ;
					}else {
						orBuilder.should(tempQueryBuilder) ;
					}
				}
			}
			if(orNums > 0) {
				boolQueryBuilder.must(orBuilder) ;
			}
		}
		Page<WorkOrders> page = workOrdersRes.findById(boolQueryBuilder, false, orgi,new PageRequest(0, 10000));
		return page.getContent();
	}
	/**
	 * 	查询参与质检的文字客服会话记录
	 * @param orgi
	 * @param qcFormFilterItemList
	 * @return
	 */
	public static List<AgentService> searchQualityAgentService(final String orgi, final List<QualityFormFilterItem> qcFormFilterItemList, User user){
		AgentServiceRepository agentServiceRes = UKDataContext.getContext().getBean(AgentServiceRepository.class);
		Page<AgentService> page = agentServiceRes.findAll(new Specification<AgentService>(){
			@Override
			public Predicate toPredicate(Root<AgentService> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  //and关系
				List<Predicate> inlist = new ArrayList<Predicate>();  //or关系
				
				list.add(cb.equal(root.get("orgi").as(String.class), orgi)) ;
				list.add(cb.and(cb.or(cb.and(root.get("qualitydistype").as(String.class).isNull()),cb.equal(root.get("qualitydistype").as(String.class), UKDataContext.QualityDisStatusType.NOT.toString())))) ;
				
				if(qcFormFilterItemList.size() > 0 && qcFormFilterItemList.get(0).getField() != null) {
					try {
						for(QualityFormFilterItem formFilterItem : qcFormFilterItemList) {
							if(formFilterItem.getField().equals("q")) {
								list.add(cb.like(root.get("agentusername").as(String.class), "%" + formFilterItem.getValue() + "%")) ;
							}else {
								Number number = null ;
								if(!StringUtils.isBlank(formFilterItem.getValue()) && formFilterItem.getValue().matches("[+-.]{0,1}[\\d.]{1,}")) {
									number = NumberFormat.getInstance().parse(formFilterItem.getValue()) ;
								}
								if(number == null){
									number = 0;
								}
								switch(formFilterItem.getCond()) {
								case "01" : 
									if("AND".equals(formFilterItem.getComp())) {
										list.add(cb.gt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}else if("OR".equals(formFilterItem.getComp())) {
										inlist.add(cb.gt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}
									break ;
								case "02" : 
									if("AND".equals(formFilterItem.getComp())) {
										list.add(cb.ge(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}else if("OR".equals(formFilterItem.getComp())) {
										inlist.add(cb.ge(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}
									break ;
								case "03" : 
									if("AND".equals(formFilterItem.getComp())) {
										list.add(cb.lt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}else if("OR".equals(formFilterItem.getComp())) {
										inlist.add(cb.lt(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}
									break ;
								case "04" : 
									if("AND".equals(formFilterItem.getComp())) {
										list.add(cb.le(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}else if("OR".equals(formFilterItem.getComp())) {
										inlist.add(cb.le(root.get(formFilterItem.getField()).as(Number.class), number)) ;
									}
									break ;
								case "05" : 
									if("AND".equals(formFilterItem.getComp())) {
										list.add(cb.equal(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
									}else if("OR".equals(formFilterItem.getComp())) {
										inlist.add(cb.equal(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
									}
									break ;
								case "06" : 
									if("AND".equals(formFilterItem.getComp())) {
										list.add(cb.notEqual(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
									}else if("OR".equals(formFilterItem.getComp())) {
										inlist.add(cb.notEqual(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
									}
									break ;
								case "07" : 
									if("AND".equals(formFilterItem.getComp())) {
										list.add(cb.like(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
									}else if("OR".equals(formFilterItem.getComp())) {
										inlist.add(cb.like(root.get(formFilterItem.getField()).as(String.class), formFilterItem.getValue())) ;
									}
									break ;
								default :
									break ;
							}
							}
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				Predicate[] p = new Predicate[list.size()];  
				Predicate[] inp = new Predicate[inlist.size()];  
				
				Predicate pb = null;
				if(inp != null && inp.length > 0) {
					pb = cb.and(list.toArray(p)).in(inlist.toArray(inp));
				}else {
					pb = cb.and(list.toArray(p));
				}
				
			    return pb;  
			}}, new PageRequest(0, 10000 , Sort.Direction.DESC, "createtime")) ;
		return page.getContent();
	}
	public static BoolQueryBuilder query(String orgi , FormFilter formFilter , List<FormFilterItem> itemList , MetadataTable metadataTable , boolean loadRef , int p, int ps){
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		queryBuilder.must(termQuery("orgi", orgi)) ;
		
		BoolQueryBuilder orBuilder = new BoolQueryBuilder();
		int orNums = 0 ;
		for(FormFilterItem formFilterItem : itemList) {
			QueryBuilder tempQueryBuilder = null ;
			if(formFilterItem.getField().equals("q")) {
				tempQueryBuilder = new QueryStringQueryBuilder(formFilterItem.getValue()).defaultOperator(Operator.AND) ;
			}else {
				switch(formFilterItem.getCond()) {
					case "01" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).from(formFilterItem.getValue()).includeLower(false) ;
						break ;
					case "02" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).from(formFilterItem.getValue()).includeLower(true) ;
						break ;
					case "03" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).to(formFilterItem.getValue()).includeUpper(false) ;
						break ;
					case "04" : 
						tempQueryBuilder = rangeQuery(formFilterItem.getField()).to(formFilterItem.getValue()).includeUpper(true) ;
						break ;
					case "05" : 
						tempQueryBuilder = termQuery(formFilterItem.getField() , formFilterItem.getValue()) ;
						break ;
					case "06" : 
						tempQueryBuilder = termQuery(formFilterItem.getField() , formFilterItem.getValue()) ;
						break ;
					case "07" : 
						tempQueryBuilder = new QueryStringQueryBuilder(formFilterItem.getValue()).field(formFilterItem.getField()).defaultOperator(Operator.AND) ;
						break ;
					default :
						break ;
				}
			}
			if("AND".equalsIgnoreCase(formFilterItem.getComp())) {
				if("06".equals(formFilterItem.getCond())) {
					queryBuilder.mustNot(tempQueryBuilder) ;
				}else {
					queryBuilder.must(tempQueryBuilder) ;
				}
			}else {
				orNums ++ ;
				if("06".equals(formFilterItem.getCond())) {
					orBuilder.mustNot(tempQueryBuilder) ;
				}else {
					orBuilder.should(tempQueryBuilder) ;
				}
			}
		}
		if(orNums > 0) {
			queryBuilder.must(orBuilder) ;
		}
		
		return queryBuilder;
	}
	
	public static PageImpl<UKDataBean> aggregationBatchData(BoolQueryBuilder queryBuilder , boolean loadRef , int p, int ps,String aggField){
		ESDataExchangeImpl esDataExchange = UKDataContext.getContext().getBean(ESDataExchangeImpl.class);
		return esDataExchange.findAllPageAggResultBatchData(queryBuilder ,   new PageRequest(p, ps , Sort.Direction.ASC, "createtime") , loadRef , null ,aggField) ;
	}
}
