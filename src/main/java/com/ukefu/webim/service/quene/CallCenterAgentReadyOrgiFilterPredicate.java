package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.util.freeswitch.model.CallCenterAgent;

public class CallCenterAgentReadyOrgiFilterPredicate implements Predicate<String, CallCenterAgent> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	/**
	 * 
	 */
	public CallCenterAgentReadyOrgiFilterPredicate(String orgi){
		this.orgi = orgi ;
	}
	public boolean apply(Map.Entry<String, CallCenterAgent> mapEntry) {
		return mapEntry.getValue()!=null && !StringUtils.isBlank(orgi) && orgi.equals(mapEntry.getValue().getOrgi()) && mapEntry.getValue().isReady();
	}
}