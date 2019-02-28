package com.ukefu.util.extra;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QualityExchangeInterface {
	
	public Serializable getDataByIdAndOrgi(String id, String orgi) ;
	
	public Page<?> getPageDataByOrgi(Object query , Pageable page) ;
	
	public List<?> getListDataByOrgi(Object query) ;
	
	public void process(Object data) ;
	
	public void processList(Object data) ;
}
