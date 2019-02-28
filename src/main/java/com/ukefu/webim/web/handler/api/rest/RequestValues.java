package com.ukefu.webim.web.handler.api.rest;

import java.io.Serializable;

public class RequestValues<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QueryParams query ;
	private T data ;
	public QueryParams getQuery() {
		return query;
	}
	public void setQuery(QueryParams query) {
		this.query = query;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
