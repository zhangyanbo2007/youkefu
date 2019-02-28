package com.ukefu.util.event;

import com.ukefu.webim.service.hibernate.BaseService;

public class MultiUpdateEvent<S> implements UserEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6657324711766725192L;
	private S data ;
	private BaseService<?> crudRes ;
	private String eventype ;
	
	public MultiUpdateEvent(S data , BaseService<?> crudRes , String eventype){
		this.data = data ;
		this.crudRes = crudRes ;
		this.eventype= eventype ;
	}
	
	public S getData() {
		return data;
	}
	public void setData(S data) {
		this.data = data;
	}
	public BaseService<?> getCrudRes() {
		return crudRes;
	}

	public void setCrudRes(BaseService<?> crudRes) {
		this.crudRes = crudRes;
	}

	public String getEventype() {
		return eventype;
	}
	public void setEventype(String eventype) {
		this.eventype = eventype;
	}
}
