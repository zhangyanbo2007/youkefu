package com.ukefu.util.task;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.PageImpl;

import com.google.common.collect.ArrayListMultimap;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.TabelSql;
import com.ukefu.util.UKTools;
import com.ukefu.util.es.SearchTools;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.metadata.DatabaseMetaDataHandler;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.ReporterRepository;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.TableProperties;
import com.ukefu.webim.web.model.UKeFuDic;

public class DatabaseImportProecess extends DataProcess{
	private AtomicInteger pages = new AtomicInteger() , errors = new AtomicInteger(); 
	private static final int PER_PAGE_SIZE = 5000 ;
	
	public DatabaseImportProecess(DSDataEvent event){
		super(event);
	}
	
	@Override
	public void process() {
		Connection connection = null ;
		Statement statement = null ;
		ResultSet result = null ;
		int resultNum = 0 ;
		try {
			if(event!=null && event.getDSData()!=null && event.getDSData().getDatabase()!=null) {
				Class.forName(event.getDSData().getDatabase().getDriverclazz()) ;
				connection = DriverManager.getConnection(event.getDSData().getDatabase().getDatabaseurl() , event.getDSData().getDatabase().getAccount() , !StringUtils.isBlank(event.getDSData().getDatabase().getPassword()) ? UKTools.decryption(event.getDSData().getDatabase().getPassword()):"") ;
				do {
					resultNum = 0 ;
					if(event.getDSData().getJobDetail()!=null && !StringUtils.isBlank(event.getDSData().getJobDetail().getSource())) {
			            statement = connection.createStatement() ;
			            TabelSql tableSQL = DatabaseMetaDataHandler.getSQL(event.getDSData().getDatabase(), event.getDSData().getJobDetail().getSource() , pages.intValue() , PER_PAGE_SIZE) ;
			            result = statement.executeQuery(tableSQL.getSql()) ;
			            resultNum = processDb(event , result);
			            if(result!=null) {
			            	result.close();
			            }
			            if(statement!=null) {
			            	statement.close();
			            }
					}
				}while(resultNum == PER_PAGE_SIZE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(result!=null) {
					result.close();
				}
				if(statement!=null) {
					statement.close(); 
				}
				if(connection!=null){
					connection.close();
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			/**
			 * 更新数据
			 */
			UKDataContext.getContext().getBean(ReporterRepository.class).save(event.getDSData().getReport()) ;
			if(event.getDSData().getClazz() == null && !StringUtils.isBlank(event.getBatid())) {
				JobDetailRepository batchRes = UKDataContext.getContext().getBean(JobDetailRepository.class) ;
				JobDetail batch = this.event.getDSData().getJobDetail();
				if(batch == null) {
					batch = batchRes.findByIdAndOrgi(event.getBatid(), event.getOrgi()) ;
				}
				if(batch!=null) {
					BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
					queryBuilder.must(termQuery("orgi",batch.getOrgi()));
					queryBuilder.must(termQuery("batid",batch.getId()));
					PageImpl<UKDataBean> aggregationBatchData = SearchTools.aggregationBatchData(queryBuilder, true, 0, 1, "batid");
					if(aggregationBatchData != null && aggregationBatchData.getContent().size() > 0) {
						UKDataBean uKDataBean = aggregationBatchData.getContent().get(0);
						//总数
						if(uKDataBean.getValues().get("total") != null) {
							batch.setNamenum(Integer.parseInt(uKDataBean.getValues().get("total").toString()));
						}else {
							batch.setNamenum(0);
						}
						//未分配
						if(uKDataBean.getValues().get("status.not") != null){
							batch.setNotassigned(Integer.parseInt(uKDataBean.getValues().get("status.not").toString()));
							//已分配
							batch.setAssigned(batch.getNamenum() - Integer.parseInt(uKDataBean.getValues().get("status.not").toString()));
						}else {
							batch.setNotassigned(0);
							//已分配
							batch.setAssigned(batch.getNamenum());
						}
						//有效
						if(uKDataBean.getValues().get("validresult.valid") != null) {
							batch.setValidnum(Integer.parseInt(uKDataBean.getValues().get("validresult.valid").toString()));
						}else {
							batch.setValidnum(0);
						}
						//无效
						if(uKDataBean.getValues().get("validresult.invalid") != null) {
							batch.setInvalidnum(Integer.parseInt(uKDataBean.getValues().get("validresult.invalid").toString()));
						}else {
							batch.setInvalidnum(0);
						}
					}
					batchRes.save(batch) ;
				}
			}
		}
	}
	
	private int processDb(final DSDataEvent event , ResultSet result) throws SQLException, ParseException{
		long start = System.currentTimeMillis() ;
		int i = 0 ;
		/**
		 * 需要检查Mapping 是否存在
		 */
		if(event!=null && event.getDSData()!=null && event.getDSData().getDatabase()!=null) {
			ResultSetMetaData meta = result.getMetaData() ;
			while(result.next()){
				i++ ;
				event.getDSData().getJobDetail().getReport().getAtompages().incrementAndGet() ;
				Map<Object, Object> values = new HashMap<Object , Object>() ;
				ArrayListMultimap<String, Object> multiValues = ArrayListMultimap.create();
				boolean skipDataVal = false; //跳过数据校验
				StringBuffer pkStr = new StringBuffer() , allStr = new StringBuffer();
				for(int col=1 ; col <= meta.getColumnCount() ; col++){
					String value = getValue(result.getObject(col)) ;
					TableProperties tableProperties = getTableProperties(event, meta.getColumnName(col));
					if(tableProperties!=null && value!=null){
						String valuestr = getValue(value) ;
						if(!StringUtils.isBlank(valuestr)) {
							if(tableProperties.isModits()){
								if(!StringUtils.isBlank(valuestr)) {
									multiValues.put(tableProperties.getFieldname(), valuestr) ;
								}
							}else{
								if(tableProperties.isSeldata()){
									SysDic sysDic = UKeFuDic.getInstance().getDicItem(valuestr) ;
									if(sysDic!=null){
										values.put(tableProperties.getFieldname(), sysDic.getName()) ;
									}else{
										List<SysDic> dicItemList = UKeFuDic.getInstance().getSysDic(tableProperties.getSeldatacode());
										if(dicItemList!=null && dicItemList.size() > 0) {
											for(SysDic dicItem : dicItemList) {
												if(dicItem.getName().equals(valuestr)) {
													values.put(tableProperties.getFieldname(), dicItem.isDiscode()?dicItem.getCode():dicItem.getId()) ; break ;
												}
											}
										}
									}
								}else{
									values.put(tableProperties.getFieldname(), valuestr) ;
								}
								if(tableProperties.isPk() && !tableProperties.getFieldname().equalsIgnoreCase("id")){
									pkStr.append(valuestr) ;
								}
							}
							allStr.append(valuestr) ;
						}
						event.getDSData().getReport().setBytes(event.getDSData().getReport().getBytes() + valuestr.length());
						event.getDSData().getReport().getAtompages().incrementAndGet() ;
					}
				}
				values.put("orgi", event.getOrgi()) ;
				if(values.get("id") == null){
					if(pkStr.length() > 0) {
						values.put("id", UKTools.md5(pkStr.append(event.getDSData().getTask().getTablename()).toString())) ;
					}else {
						values.put("id", UKTools.md5(allStr.append(event.getDSData().getTask().getTablename()).toString())) ;
					}
				}
				if(event.getValues()!=null && event.getValues().size() > 0){
					values.putAll(event.getValues());
				}
				values.putAll(multiValues.asMap());
				String validFaildMessage = null ;
				for(TableProperties tp : event.getDSData().getTask().getTableproperty()){
					if(!StringUtils.isBlank(tp.getDefaultvaluetitle())) {
						String valuestr = (String) values.get(tp.getFieldname()) ;
						if(tp.getDefaultvaluetitle().indexOf("required") >= 0 && StringUtils.isBlank(valuestr)) {
							skipDataVal = true ; validFaildMessage = "required" ;break ;
						}else if(valuestr!=null && (tp.getDefaultvaluetitle().indexOf("numstr") >= 0 && !valuestr.matches("[\\d]{1,}"))) {
							skipDataVal = true ; validFaildMessage = "numstr" ;break ;
						}else if(valuestr!=null && (tp.getDefaultvaluetitle().indexOf("datenum") >= 0 || tp.getDefaultvaluetitle().indexOf("datetime") >= 0 )) {
							if(!valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2,2}") && !valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2} [\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}")) {
								skipDataVal = true ; validFaildMessage = "datenum" ; break ;
							}else {
								if(valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-{1,1}")) {
									if("date".equals(tp.getDefaultfieldvalue())) {
										values.put(tp.getFieldname(),UKTools.simpleDateFormat.parse(valuestr));
									}else {
										values.put(tp.getFieldname(),UKTools.simpleDateFormat.format(UKTools.simpleDateFormat.parse(valuestr)));
									}
								}else if(valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2,2} [\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}")) {
									if("date".equals(tp.getDefaultfieldvalue())) {
										values.put(tp.getFieldname(),UKTools.dateFormate.parse(valuestr));
									}else {
										values.put(tp.getFieldname(),UKTools.simpleDateFormat.format(UKTools.dateFormate.parse(valuestr)));
									}

								}
							}
						}
					}
				}

				if(!values.containsKey("orgi")) {
					skipDataVal = true ;
				}
				event.getDSData().getReport().setTotal(pages.intValue());
				values.put("creater", event.getValues().get("creater")) ;
				{
					/**
					 * 导入的数据，只写入ES
					 */
					if(skipDataVal == true) {	//跳过
						values.put("status", "invalid") ;
						values.put("validresult", "invalid") ;
						values.put("validmessage", validFaildMessage!=null ? validFaildMessage : "") ;
					}else {
						values.put("validresult", "valid") ;
					}
					values.put("status", UKDataContext.NamesDisStatusType.NOT.toString()) ;
					values.put("batid", event.getBatid()) ;

					values.put("createtime", System.currentTimeMillis()) ;
					values.put("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString()) ;
					values.put("execid", event.getDSData().getReport().getId()) ;

					if(i%500 == 0) {
						UKDataContext.getContext().getBean(ReporterRepository.class).save(event.getDSData().getReport()) ;
						CacheHelper.getJobCacheBean().put(event.getDSData().getJobDetail().getId(), event.getDSData().getJobDetail() , event.getOrgi()) ;
					}

					if(values.get("cusid")==null) {
						/**
						 * 
						 */
						values.put("cusid", values.get("id"))  ;
					}
					pages.incrementAndGet() ;
					event.getDSData().getProcess().process(values);

					/**
					 * 访客信息表
					 */
				}
				if(skipDataVal == true) {	//跳过
					errors.incrementAndGet();
					continue ;
				}
			}
		}

		event.setTimes(System.currentTimeMillis() - start);
		event.getDSData().getReport().setEndtime(new Date());
		event.getDSData().getReport().setAmount(String.valueOf((float)event.getTimes()/1000f));
		event.getDSData().getReport().setStatus(UKDataContext.TaskStatusType.END.getType());
		event.getDSData().getReport().setTotal(pages.intValue());
		event.getDSData().getReport().setPages(pages.intValue());
		event.getDSData().getReport().setErrors(errors.intValue());

		event.getDSData().getProcess().end();
		return i;
	}
	
	private TableProperties getTableProperties(DSDataEvent event , String title){
		TableProperties tableProperties = null ; 
		for(TableProperties tp : event.getDSData().getTask().getTableproperty()){
			if(tp.getName().equals(title) || tp.getFieldname().equals(title) || tp.getFieldname().equals("f"+UKTools.genIDByKey(event.getTablename()+title+"String"))){
				tableProperties = tp ; break ;
			}
		}
		return tableProperties;
	}
	
	private String getValue(Object data){
		String value = null ;
		if(data!=null){
			value = data.toString() ;
		}
        return value;
	}
}
