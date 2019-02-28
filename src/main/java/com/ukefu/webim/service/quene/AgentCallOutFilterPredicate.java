package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.CallOutNames;

public class AgentCallOutFilterPredicate implements Predicate<String, CallOutNames> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	/**
	 * 
	 */
	public AgentCallOutFilterPredicate(String orgi){
		this.orgi = orgi ;
	}
	public boolean apply(Map.Entry<String, CallOutNames> mapEntry) {
		return mapEntry.getValue()!=null && !StringUtils.isBlank(orgi) && orgi.equals(mapEntry.getValue().getOrgi()) && UKDataContext.CallOutType.AGENT.toString().equals(mapEntry.getValue().getCalltype());
	}
}