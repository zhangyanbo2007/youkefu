package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.webim.web.model.StatusEvent;

public class CallAgentFilterPredicate implements Predicate<String, StatusEvent> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String agent ;
	/**
	 * 
	 */
	public CallAgentFilterPredicate(String agent){
		this.agent = agent ;
	}
	public boolean apply(Map.Entry<String, StatusEvent> mapEntry) {
		return agent!=null && !StringUtils.isBlank(agent) && agent.equals(mapEntry.getValue().getAgent());
	}
}