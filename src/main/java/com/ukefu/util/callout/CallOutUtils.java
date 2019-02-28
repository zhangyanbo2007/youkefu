package com.ukefu.util.callout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.client.NettyClients;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.util.task.DSDataEvent;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.CallOutConfigRepository;
import com.ukefu.webim.service.repository.CallOutNamesRepository;
import com.ukefu.webim.service.repository.CallOutTaskRepository;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.web.model.CallOutConfig;
import com.ukefu.webim.web.model.CallOutNames;
import com.ukefu.webim.web.model.CallOutTask;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.TableProperties;

public class CallOutUtils {
	/**
	 * AI配置
	 * @param orgi
	 * @return
	 */
	public static CallOutConfig initCallOutConfig(String dataid,String orgi){
		CallOutConfig callOutConfig = (CallOutConfig) CacheHelper.getSystemCacheBean().getCacheObject(UKDataContext.SYSTEM_CACHE_CALLOUT_CONFIG+"_"+dataid, orgi);
		if(UKDataContext.getContext() != null && callOutConfig == null){
			CallOutConfigRepository callOutConfigRepository = UKDataContext.getContext().getBean(CallOutConfigRepository.class) ;
			List<CallOutConfig> callOutConfigList = callOutConfigRepository.findByDataidAndOrgi(dataid,orgi) ;
			if(callOutConfigList.size() == 0){
				callOutConfig = new CallOutConfig() ;
			}else{
				callOutConfig = callOutConfigList.get(0) ;
				CacheHelper.getSystemCacheBean().put(UKDataContext.SYSTEM_CACHE_CALLOUT_CONFIG+"_"+callOutConfig.getDataid(),callOutConfig, orgi) ;
			}
		}
		return callOutConfig ;
	}
	
	/**
	 * AI配置
	 * @param orgi
	 * @return
	 */
	public static CallOutConfig initCallOutConfig(String orgi){
		CallOutConfig callOutConfig = (CallOutConfig) CacheHelper.getSystemCacheBean().getCacheObject(UKDataContext.SYSTEM_CACHE_CALLOUT_CONFIG+"_"+orgi, orgi);
		if(UKDataContext.getContext() != null && callOutConfig == null){
			CallOutConfigRepository callOutConfigRepository = UKDataContext.getContext().getBean(CallOutConfigRepository.class) ;
			List<CallOutConfig> callOutConfigList = callOutConfigRepository.findByOrgi(orgi) ;
			if(callOutConfigList.size() == 0){
				callOutConfig = new CallOutConfig() ;
			}else{
				callOutConfig = callOutConfigList.get(0) ;
				CacheHelper.getSystemCacheBean().put(UKDataContext.SYSTEM_CACHE_CALLOUT_CONFIG+"_"+orgi,callOutConfig, orgi) ;
			}
		}
		return callOutConfig ;
	}
	
	/**
	 * AI配置
	 * @param orgi
	 * @return
	 */
	public static List<CallOutConfig> initCallOutConfig(){
		List<CallOutConfig> configList = new ArrayList<CallOutConfig>() ;
		if(UKDataContext.getContext()!=null) {
			CallOutConfigRepository callOutConfigRepository = UKDataContext.getContext().getBean(CallOutConfigRepository.class) ;
			configList = callOutConfigRepository.findAll() ;
		}
		return configList;
	}
	
	
	public static CallOutNames processNames(UKDataBean name, CallCenterAgent agent , String orgi , int leavenames) {
		CallOutNames callOutName = null ;
		if(name.getValues() != null && name.getValues().get("batid") != null && name.getValues().get("taskid") != null) {
			String batid = (String) name.getValues().get("batid") ;
			String taskid = (String) name.getValues().get("taskid") ;
			JobDetail batch = UKDataContext.getContext().getBean(JobDetailRepository.class).findByIdAndOrgi(batid, orgi) ;
			CallOutTask task = UKDataContext.getContext().getBean(CallOutTaskRepository.class).findByIdAndOrgi(taskid, orgi) ;
			CallOutNamesRepository callOutNamesRes = UKDataContext.getContext().getBean(CallOutNamesRepository.class) ;
			List<CallOutNames> callNamesList = callOutNamesRes.findByDataidAndCreaterAndOrgi((String)name.getValues().get("id"), (String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT), orgi) ;
			if(callNamesList.size() > 0) {
				callOutName = callNamesList.get(0) ;
			}
			if(callOutName == null) {
				callOutName = new CallOutNames() ; 
			}
			if(callOutName!=null){
				callOutName.setOrgi(orgi);
				if(task!=null) {
					callOutName.setName(task.getName());	//任务名称
					callOutName.setActid(task.getActid());
				}
				if(batch!=null) {
					callOutName.setBatname(batch.getName());
					callOutName.setMetaname(batch.getActid());
				}
				callOutName.setBatid(batid);
				
				callOutName.setTaskid(taskid);
				
				callOutName.setFilterid((String) name.getValues().get("filterid"));
				callOutName.setDataid((String)name.getValues().get("id"));
				
				callOutName.setStatus((String)name.getValues().get("status"));
				
				callOutName.setCreater((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
				callOutName.setOrgan((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
				callOutName.setCreatetime(new Date());
				callOutName.setUpdatetime(new Date());
				Object apstatus = name.getValues().get("apstatus") ;
				if(apstatus!=null && apstatus.toString().equals("true")) {
					callOutName.setReservation(true);
				}else {
					callOutName.setReservation(false);
				}
				callOutName.setMemo((String) name.getValues().get("apmemo"));
				callOutName.setWorkstatus((String) name.getValues().get("workstatus"));
				
				callOutName.setOwneruser((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
				callOutName.setOwnerdept((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
				callOutName.setOwnerai((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
				callOutName.setOwnerforecast((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
				callOutName.setLeavenum(leavenames);
			}
			
			
			String dial_number = null ;
			boolean disphonenum = false ;
			String distype = null;
			
			if(batch!=null && !StringUtils.isBlank(batch.getActid())) {
				MetadataTable table = UKDataContext.getContext().getBean(MetadataRepository.class).findByTablename(batch.getActid()) ;
				if(table!=null) {
					for(TableProperties tp : table.getTableproperty()) {
						if(tp.isPhonenumber()) {
							if(tp.isPrivatefield()) {
								if(!tp.isSystemfield()) {
									dial_number = (String) name.getValues().get("pri_"+tp.getFieldname()) ;
								}
							}else {
								dial_number = (String) name.getValues().get(tp.getFieldname()) ;
							}
							disphonenum = tp.isSecfield() ;
							distype = tp.getSecdistype() ;
							break ;
						}
					}
				}
			}
			
			if(!StringUtils.isBlank(dial_number)) {
				callOutName.setPhonenumber(dial_number);
				if(disphonenum) {
					callOutName.setDistype(distype);
				}
				if(agent!=null) {
					NettyClients.getInstance().sendCallCenterMessage(agent.getExtno(), "preview", callOutName);
				}
			}else if(agent!=null){
				agent.setWorkstatus(UKDataContext.WorkStatusEnum.IDLE.toString());
				NettyClients.getInstance().sendCallCenterMessage(agent.getExtno(), "nophonenumber", callOutName.getName());
				//NettyClients.getInstance().sendCallCenterMessage(agent.getExtno(), "error", "nophonenumber");
				NettyClients.getInstance().sendCallCenterMessage(agent.getExtno(), "docallout", agent);
			}
			callOutNamesRes.save(callOutName) ;
			if(agent!=null) {
				agent.setNameid(callOutName.getId());
			}
		}
		return callOutName ;
	}
	
	public static CallOutNames processNames(UKDataBean name, String orgi , int leavenames , CallOutNamesRepository callOutNamesRes) {
		String batid = (String) name.getValues().get("batid") ;
		String taskid = (String) name.getValues().get("taskid") ;
		JobDetail batch = UKDataContext.getContext().getBean(JobDetailRepository.class).findByIdAndOrgi(batid, orgi) ;
		CallOutTask task = UKDataContext.getContext().getBean(CallOutTaskRepository.class).findByIdAndOrgi(taskid, orgi) ;
		CallOutNames callOutName = null; 
		
		List<CallOutNames> callNamesList = callOutNamesRes.findByDataidAndCreaterAndOrgi((String)name.getValues().get("id"), (String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT), orgi) ;
		if(callNamesList.size() > 0) {
			callOutName = callNamesList.get(0) ;
		}
		if(callOutName == null) {
			callOutName = new CallOutNames() ; 
		}
		if(callOutName!=null){
			callOutName.setOrgi(orgi);
			if(task!=null) {
				callOutName.setName(task.getName());	//任务名称
				callOutName.setActid(task.getActid());
			}
			if(batch!=null) {
				callOutName.setBatname(batch.getName());
			}
			
			
			callOutName.setBatid(batid);
			
			callOutName.setTaskid(taskid);
			
			callOutName.setMetaname(batch.getActid());
			
			callOutName.setFilterid((String) name.getValues().get("filterid"));
			callOutName.setDataid((String)name.getValues().get("id"));
			
			callOutName.setStatus(UKDataContext.NamesProcessStatus.DIS.toString());
			
			callOutName.setCreater((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
			callOutName.setOrgan((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
			callOutName.setCreatetime(new Date());
			callOutName.setUpdatetime(new Date());
			Object apstatus = name.getValues().get("apstatus") ;
			if(apstatus!=null && !StringUtils.isBlank(apstatus.toString()) && apstatus.toString().toLowerCase().equals("true")) {
				callOutName.setReservation(true);
			}else {
				callOutName.setReservation(false);
			}
			callOutName.setMemo((String) name.getValues().get("apmemo"));
			callOutName.setWorkstatus((String) name.getValues().get("workstatus"));
			callOutName.setOwneruser((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
			callOutName.setOwnerdept((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
			callOutName.setOwnerai((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
			callOutName.setOwnerforecast((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
			callOutName.setLeavenum(leavenames);
		}
		
		
		String dial_number = null ;
		boolean disphonenum = false ;
		String distype = null;
		
		if(batch!=null && !StringUtils.isBlank(batch.getActid())) {
			MetadataTable table = UKDataContext.getContext().getBean(MetadataRepository.class).findByTablename(batch.getActid()) ;
			for(TableProperties tp : table.getTableproperty()) {
				if(tp.isPhonenumber()) {
					dial_number = (String) name.getValues().get(tp.getFieldname()) ; 
					disphonenum = tp.isSecfield() ;
					distype = tp.getSecdistype() ;
					break ;
				}
			}
		}
		
		if(!StringUtils.isBlank(dial_number)) {
			callOutName.setPhonenumber(dial_number);
			if(disphonenum) {
				callOutName.setDistype(distype);
			}
			
		}
		callOutNamesRes.save(callOutName) ;
		return callOutName ;
	}
	
	public static void processMetadataTable(boolean findId , MetadataTable metaDataTable , DSDataEvent event) {
		metaDataTable.getTableproperty().add(initProperties("id", "主键", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("orgi", "租户ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("creater", "创建人", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("createtime", "创建时间", "Datetime", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("validresult", "数据状态", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("validmessage", "数据状态", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("assuser", "分配执行人", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		
		metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_AI, "分配AI", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, "分配用户", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, "分配部门", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_TIME, "分配时间", "Datetime", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, "分配队列", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("status", "状态", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		/**
		 * 机器人/人工
		 */
		metaDataTable.getTableproperty().add(initProperties("process", "处理状态", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("processtime", "处理时间", "Datetime", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("processmemo", "处理备注", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("metaid", "元数据", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("actid", "活动ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("batid", "批次ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("taskid", "任务ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("filterid", "任务ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("cusid", "客户ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("calloutfilid", "筛选记录ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("execid", "导入记录ID", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("callstatus", "拨打状态", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("workstatus", "业务状态", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("apstatus", "是否预约", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("aptime", "预约时间", "Date", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("apmemo", "预约备注", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("callresult", "拨打结果信息", "String", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("calltime", "拨打时间", "Date", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("firstcalltimes", "首次拨打时间", "Date", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("firstcallstatus", "首次拨打结果", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("calltimes", "拨打次数", "Long", event.getOrgi() , event.getTablename() , true)) ;
		
		
		
		metaDataTable.getTableproperty().add(initProperties("succcall", "拨打成功次数", "Long", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("faildcall", "拨打失败次数", "Long", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("ringtime", "振铃时长", "Long", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("incall", "通话时长", "Long", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("level", "评级", "String", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("levelscore", "评分", "Long", event.getOrgi() , event.getTablename() , true)) ;
		
		metaDataTable.getTableproperty().add(initProperties("focustimes", "关注点次数", "Long", event.getOrgi() , event.getTablename() , true)) ;
		metaDataTable.getTableproperty().add(initProperties("afterprocesstime", "后处理时长", "Long", event.getOrgi() , event.getTablename() , true)) ;
	}
	
	public static TableProperties initProperties(String name ,String title, String type ,String orgi ,String tableName , boolean sysfield) {
		TableProperties tablePorperties = new TableProperties(name, type, 255 , tableName) ;
		tablePorperties.setOrgi(orgi) ;
		
		tablePorperties.setDatatypecode(0);
		tablePorperties.setLength(255);
		tablePorperties.setDatatypename(type);
		tablePorperties.setName(title);
		
		tablePorperties.setSysfield(sysfield);
		return tablePorperties;
	}
}
