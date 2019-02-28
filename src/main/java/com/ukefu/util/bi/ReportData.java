package com.ukefu.util.bi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queryparser.flexible.core.nodes.PathQueryNode.QueryText;

import com.ukefu.util.bi.model.Level;
import com.ukefu.util.bi.model.RequestData;
import com.ukefu.util.bi.model.ValueData;


public interface ReportData extends Serializable{
	public Level getRow() ;
	public Level getCol() ;
	public void setRow(Level level) ;
	public List<List<ValueData>> getData() ;
	public String getViewData() ;
	public void setPageSize(int pageSize);
	public int getPageSize() ;
	public int getPage();
	public void setPage(int page) ;
	public void setViewData(String viewData) ;
	public void exchangeColRow() ;	//行列转换
	public void merge(ReportData data) ;
	public Date getDate() ;
	public void setDate(Date createtime) ;
	public void setException(Exception ex) ;
	public Exception getException ();
	public RequestData getRequestData();
	public void setRequestData(RequestData data);
	
	public QueryText getQueryText() ;
	public void setQueryText(QueryText queryText) ;
	
	public void setTotal(long total) ;
	public long getTotal() ;
	
	public Map<String , Object> getOptions() ;
	
	public void setOptions(Map<String , Object> options);
	
	public void setQueryTime(long queryTime) ;
	
	public long getQueryTime() ;
}
