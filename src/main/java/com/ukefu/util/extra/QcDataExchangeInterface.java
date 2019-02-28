package com.ukefu.util.extra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface QcDataExchangeInterface<T> extends DataExchangeInterface{
	public Page<T> load(Object query , PageRequest pageRequest); 
	public T get(String id, String orgi);
}
