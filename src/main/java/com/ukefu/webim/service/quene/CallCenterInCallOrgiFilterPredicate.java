package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.freeswitch.model.CallCenterAgent;

public class CallCenterInCallOrgiFilterPredicate implements Predicate<String, CallCenterAgent> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	/**
	 * 
	 */
	public CallCenterInCallOrgiFilterPredicate(String orgi){
		this.orgi = orgi ;
	}
	public boolean apply(Map.Entry<String, CallCenterAgent> mapEntry) {
		return mapEntry.getValue()!=null && !StringUtils.isBlank(orgi) && orgi.equals(mapEntry.getValue().getOrgi()) && UKDataContext.AgentStatusEnum.INCALL.toString().equals(mapEntry.getValue().getStatus());
	}
}