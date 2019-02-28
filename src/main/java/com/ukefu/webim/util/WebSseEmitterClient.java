package com.ukefu.webim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;
import com.ukefu.util.webim.WebIMClient;

public class WebSseEmitterClient{
	
	private ConcurrentMap<String, WebIMClient> imClientsMap = new MapMaker().weakValues().makeMap();
	
	public List<WebIMClient> getClients(String userid){
		Collection<WebIMClient> values = imClientsMap.values() ;
		List<WebIMClient> clents = new ArrayList<WebIMClient>();
		for(WebIMClient client : values){
			if(client.getUserid().equals(userid)){
				clents.add(client) ;
			}
		}
		return clents;
	}
	
	public int size(){
		return imClientsMap.size() ;
	}
	
	public void putClient(String userid , WebIMClient client){
		imClientsMap.put(client.getClient(), client) ;
	}
	
	public void removeClient(String sessionid,String userid , String client , boolean timeout) throws Exception{
		List<WebIMClient> keyClients = this.getClients(userid) ;
		for(int i=0 ; i<keyClients.size() ; i++){
			WebIMClient webIMClient = keyClients.get(i) ;
			if(webIMClient.getClient()!=null && webIMClient.getClient().equals(client)){
				imClientsMap.remove(client) ;
				keyClients.remove(i) ;
				break ;
			}
		}
		if(keyClients.size() == 0 && timeout == true){
			OnlineUserUtils.offline(sessionid , userid, userid);
		}
	}
}
