package com.ukefu.webim.service.resource;

import java.util.HashMap;
import java.util.Map;

import com.ukefu.util.es.UKDataBean;
import com.ukefu.webim.web.model.JobDetail;

public class OutputTextFormat {
	private String id ;
	private String title ;
	private String parent ;
	
	private Map<String , Object> data = new HashMap<String , Object>();
	private JobDetail job ;
	private UKDataBean dataBean ;
	private Object object ;
	
	public OutputTextFormat(JobDetail job){
		this.job = job ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public JobDetail getJob() {
		return job;
	}
	public void setJob(JobDetail job) {
		this.job = job;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public UKDataBean getDataBean() {
		return dataBean;
	}
	public void setDataBean(UKDataBean dataBean) {
		this.dataBean = dataBean;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
