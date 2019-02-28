package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.CallOutNames;

public class AiCallOutFilterPredicate implements Predicate<String, CallOutNames> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	private String ownerai = null;
	/**
	 * 
	 */
	public AiCallOutFilterPredicate(String orgi){
		this.orgi = orgi ;
	}
	public AiCallOutFilterPredicate(String orgi,String ownerai){
		this.orgi = orgi ;
		this.ownerai = ownerai;
	}
	public boolean apply(Map.Entry<String, CallOutNames> mapEntry) {
		boolean flag =  mapEntry.getValue()!=null && !StringUtils.isBlank(orgi) && orgi.equals(mapEntry.getValue().getOrgi()) && UKDataContext.CallOutType.AI.toString().equals(mapEntry.getValue().getCalltype());
		if(StringUtils.isNotBlank(ownerai)) {
			return flag && ownerai.equals(mapEntry.getValue().getOwnerai());
		}
		return flag;
	}
}