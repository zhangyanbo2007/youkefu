package com.ukefu.util.crm;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.task.DSDataEvent;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.TableProperties;

public class CrmUtils {
	
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
