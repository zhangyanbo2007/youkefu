package com.ukefu.util.client;

import java.util.List;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.rpc.RPCTools;


public class NettyClients {
	
	private static NettyClients clients = new NettyClients();
	
	private NettyIMClient imClients = new NettyIMClient();
	private NettyAgentClient agentClients = new NettyAgentClient();
	private NettyIMClient entIMClients = new NettyIMClient();
	private NettyCallCenterClient callCenterClients = new NettyCallCenterClient();
	
	public int size(){
		return imClients.size();
	}
	
	public static NettyClients getInstance(){
		return clients ;
	}
	
	/**
	 * 放入开始
	 * @param id
	 * @param userClient
	 */
	public void putIMEventClient(String id , SocketIOClient userClient){
		imClients.putClient(id, userClient);
	}
	public void putAgentEventClient(String id , SocketIOClient agentClient){
		agentClients.putClient(id, agentClient);
	}
	public void putEntIMEventClient(String id , SocketIOClient userClient){
		entIMClients.putClient(id, userClient);
	}
	public void putCallCenterEventClient(String id , SocketIOClient userClient){
		callCenterClients.putClient(id, userClient);
	}
	/**
	 * 放入结束
	 * @return
	 */
	
	
	public NettyCallCenterClient getCallCenterClients(){
		return this.callCenterClients ;
	}
	
	public NettyIMClient getIMClients(){
		return this.imClients ;
	}
	
	public NettyIMClient getEntIMClients(){
		return this.entIMClients;
	}
	public NettyAgentClient getAgentClients(){
		return this.agentClients;
	}
	

	public void setImClients(NettyIMClient imClients) {
		this.imClients = imClients;
	}
	
	public void closeIMEventClient(String id , String sessionid, String orgi){
		List<SocketIOClient> userClients = imClients.getClients(id) ;
		for(SocketIOClient userClient : userClients){
			if(UKTools.getContextID(userClient.getSessionId().toString()).equals(sessionid)){
				userClient.disconnect();
			}
		}
	}
	
	public void removeAgentEventClient(String id , String sessionid){
		agentClients.removeClient(id, sessionid);
	}
	public void removeEntIMEventClient(String id , String sessionid){
		entIMClients.removeClient(id, sessionid);
	}
	public void removeIMEventClient(String id , String sessionid){
		imClients.removeClient(id, sessionid);
	}
	public void removeCallCenterClient(String id , String sessionid){
		callCenterClients.removeClient(id, sessionid);
	}
	/**
	 * HA支持
	 * @param id
	 * @param event
	 * @param data
	 */
	public void sendIMEventMessage(String id , String event , Object data){
		if(UKTools.getSystemConfig()!=null && UKTools.getSystemConfig().isEnabledis()){
			RPCTools.sendIMEventMessage(id, event, data);
		}else {
			List<SocketIOClient> userClients = imClients.getClients(id) ;
			if(userClients.size() > 0) {
				for(SocketIOClient userClient : userClients){
					userClient.sendEvent(event, data);
				}
			}
		}
	}
	
	public void setAgentClients(NettyAgentClient agentClients) {
		this.agentClients = agentClients;
	}
	
	/**
	 * HA支持
	 * @param id
	 * @param event
	 * @param data
	 */
	public void sendAgentEventMessage(String id , String event , Object data){
		if(UKTools.getSystemConfig()!=null && UKTools.getSystemConfig().isEnabledis()){
			RPCTools.sendAgentEventMessage(id, event, data);
		}else {
			List<SocketIOClient> agents = agentClients.getClients(id) ;
			if(agents.size() > 0) {
				for(SocketIOClient agentClient : agents){
					agentClient.sendEvent(event, data);
				}
			}
		}
	}
	
	public void setEntImClients(NettyIMClient entIMClients) {
		this.entIMClients = entIMClients;
	}
	
	/**
	 * HA支持
	 * @param id
	 * @param event
	 * @param data
	 */
	public void sendEntIMEventMessage(String id , String event , Object data){
		if(UKTools.getSystemConfig()!=null && UKTools.getSystemConfig().isEnabledis()) {
			RPCTools.sendEntIMEventMessage(id, event, data);
		}else{
			List<SocketIOClient> entims = entIMClients.getClients(id) ;
			if(entims.size() > 0) {
				for(SocketIOClient userClient : entims){
					userClient.sendEvent(event, data);
				}
			}
		}
	}
	
	public int getEntIMClientsNum(String user){
		return entIMClients.getClients(user)!=null ? entIMClients.getClients(user).size() : 0;
	}
	/**
	 * HA支持
	 * @param id
	 * @param event
	 * @param data
	 */
	public void sendCallCenterMessage(String id , String event , Object data){
		if(UKTools.getSystemConfig()!=null && UKTools.getSystemConfig().isEnabledis()) {
			RPCTools.sendCallCenterMessage(id, event, data);
		}else {
			List<SocketIOClient> ccClients = callCenterClients.getClients(id) ;
			if(ccClients.size() > 0) {
				for(SocketIOClient ccClient : ccClients){
					ccClient.sendEvent(event, data);
				}
			}
		}
	}
	
	/**
	 * HA支持
	 * @param id
	 * @param event
	 * @param data
	 */
	public void published(String name , String event , Object data){
		if(UKTools.getSystemConfig()!=null && UKTools.getSystemConfig().isEnabledis()) {
			RPCTools.published(name, event, data);
		}else {
			UKDataContext.getContext().getBean(name , SocketIONamespace.class) .getBroadcastOperations().sendEvent(event, data);
		}
	}
	
	
}
