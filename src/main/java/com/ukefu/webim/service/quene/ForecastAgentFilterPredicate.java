package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.freeswitch.model.CallCenterAgent;

public class ForecastAgentFilterPredicate implements Predicate<String, CallCenterAgent> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	private String ownerqueue = null;
	/**
	 * 
	 */
	public ForecastAgentFilterPredicate(String orgi){
		this.orgi = orgi ;
	}
	public ForecastAgentFilterPredicate(String orgi,String ownerqueue){
		this.orgi = orgi ;
		this.ownerqueue = ownerqueue;
	}
	public boolean apply(Map.Entry<String, CallCenterAgent> mapEntry) {
		boolean flag =  mapEntry.getValue()!=null && !StringUtils.isBlank(orgi) && UKDataContext.WorkStatusEnum.CALLOUT.toString().equals(mapEntry.getValue().getWorkstatus()) && orgi.equals(mapEntry.getValue().getOrgi()) && !StringUtils.isBlank(mapEntry.getValue().getForecastvalue()) && mapEntry.getValue().getForecastvalue().indexOf(this.ownerqueue) >= 0;
		if(StringUtils.isNotBlank(ownerqueue)) {
			return flag && mapEntry.getValue().isForecast();
		}
		return flag;
	}
}