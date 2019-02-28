package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.CallOutNames;

public class ForecastCallOutFilterPredicate implements Predicate<String, CallOutNames> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	private String ownerqueue = null;
	/**
	 * 
	 */
	public ForecastCallOutFilterPredicate(String orgi){
		this.orgi = orgi ;
	}
	public ForecastCallOutFilterPredicate(String orgi,String ownerqueue){
		this.orgi = orgi ;
		this.ownerqueue = ownerqueue;
	}
	public boolean apply(Map.Entry<String, CallOutNames> mapEntry) {
		boolean flag =  mapEntry.getValue()!=null && !StringUtils.isBlank(orgi) && orgi.equals(mapEntry.getValue().getOrgi()) && UKDataContext.CallOutType.FORECAST.toString().equals(mapEntry.getValue().getCalltype());
		if(StringUtils.isNotBlank(ownerqueue)) {
			return flag && ownerqueue.equals(mapEntry.getValue().getOwnerforecast());
		}
		return flag;
	}
}