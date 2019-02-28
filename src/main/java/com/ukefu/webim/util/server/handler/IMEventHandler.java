package com.ukefu.webim.util.server.handler;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.impl.AgentUserService;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.util.MessageUtils;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.util.server.message.AgentStatusMessage;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.util.server.message.NewRequestMessage;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.AgentUser;
import com.ukefu.webim.web.model.Contacts;
import com.ukefu.webim.web.model.CousultInvite;
import com.ukefu.webim.web.model.IMClient;
import com.ukefu.webim.web.model.MessageOutContent;

public class IMEventHandler     
{  
	protected SocketIOServer server;
	
    @Autowired  
    public IMEventHandler(SocketIOServer server)   
    {  
        this.server = server ;
    }  
    
    @OnConnect  
    public void onConnect(SocketIOClient client) 
    {  
    }  
      
    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息  
    @OnDisconnect  
    public void onDisconnect(SocketIOClient client)  
    {  
    	IMClient im = client.get("im") ;
		if(im!=null && im.getUser()!=null){
			try {
				/**
				 * 用户主动断开服务
				 */
				ServiceQuene.serviceFinish((AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(im.getUser(), UKDataContext.SYSTEM_ORGI), im.getOrgi() , UKDataContext.EndByType.USER.toString()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			NettyClients.getInstance().removeIMEventClient(im.getUser() ,UKTools.getContextID(client.getSessionId().toString()));
		}
    }  
      
    //消息接收入口，用于接受网站资源用户传入的 个人信息
    @OnEvent(value = "new")  
    public void onEvent(SocketIOClient client, AckRequest request, IMClient im, Contacts contacts)   
    {
    	try {
    		client.set("im", im);
			if(!StringUtils.isBlank(im.getUser())){
				/**
				 * 用户进入到对话连接 ， 排队用户请求 , 如果返回失败，表示当前坐席全忙，用户进入排队状态，当前提示信息 显示 当前排队的队列位置，不可进行对话，用户发送的消息作为留言处理
				 */
				InetSocketAddress address = (InetSocketAddress) client.getRemoteAddress()  ;
				String ip = UKTools.getIpAddr(client.getHandshakeData().getHttpHeaders(), address.getHostString()) ;
				NewRequestMessage newRequestMessage = OnlineUserUtils.newRequestMessage(im.getUser(), im.getOrgi(), im.getSession(), im.getAppid(), ip , im.getOsname() , im.getBrowser() , UKDataContext.ChannelTypeEnum.WEBIM.toString() , im.getSkill(), im.getAgent(), im.getNickname(), im.getTitle() , im.getUrl(), im.getTraceid() , UKDataContext.ChatInitiatorType.USER.toString()) ;
//				/**
//				 * 加入到 缓存列表
//				 */
				NettyClients.getInstance().putIMEventClient(im.getUser(), client);
//				
				if(newRequestMessage!=null && !StringUtils.isBlank(newRequestMessage.getMessage())){
					MessageOutContent outMessage = new MessageOutContent() ;
			    	outMessage.setMessage(newRequestMessage.getMessage());
			    	outMessage.setMessageType(UKDataContext.MessageTypeEnum.MESSAGE.toString());
			    	outMessage.setCalltype(UKDataContext.CallTypeEnum.IN.toString());
			    	outMessage.setNickName(newRequestMessage.getUsername());
					outMessage.setCreatetime(UKTools.dateFormate.format(new Date()));
					outMessage.setAgentserviceid(newRequestMessage.getAgentserviceid());
					
					outMessage.setNoagent(newRequestMessage.isNoagent());
					
					client.sendEvent(UKDataContext.MessageTypeEnum.STATUS.toString(), outMessage);
				}
			}else{//非法链接
				client.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
			client.disconnect();
		}
    	if(contacts!=null) {
			AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(im.getUser(), im.getOrgi()) ;
			AgentUserService service = UKDataContext.getContext().getBean(
					AgentUserService.class);
			if(agentUser == null){
				agentUser = service.findByUseridAndOrgi(im.getUser() , im.getOrgi());
			}
			if(agentUser!=null){
				agentUser.setName(contacts.getName());
				CousultInvite invite = OnlineUserUtils.cousult(agentUser.getAppid(), im.getOrgi(), UKDataContext.getContext().getBean(ConsultInviteRepository.class)) ;
				if(invite != null && invite.isShowcontacts() && !StringUtils.isBlank(contacts.getName())) {
					agentUser.setUsername(contacts.getName());
				}
				agentUser.setPhone(contacts.getPhone());
				agentUser.setEmail(contacts.getEmail());
				agentUser.setResion(contacts.getMemo());
				service.save(agentUser);
				CacheHelper.getAgentUserCacheBean().put(agentUser.getUserid(), agentUser , UKDataContext.SYSTEM_ORGI) ;
			}
				
			AgentServiceRepository agentServiceRes = UKDataContext.getContext().getBean(AgentServiceRepository.class) ;
			List<AgentService> agentServiceList = agentServiceRes.findByUseridAndOrgi(im.getUser(), im.getOrgi()) ;
			if(agentServiceList.size() > 0){
				AgentService agentService = agentServiceList.get(0) ;
				agentService.setName(contacts.getName());
				agentService.setPhone(contacts.getName());
				agentService.setEmail(contacts.getName());
				agentService.setRegion(contacts.getMemo());
				agentServiceRes.save(agentService) ;
			}
    	}
    }  
    
  //消息接收入口，坐席状态更新  
    @OnEvent(value = "agentstatus")  
    public void onEvent(SocketIOClient client, AckRequest request, AgentStatusMessage data)   
    {
    	System.out.println(data.getMessage());
    } 
    
    //消息接收入口，收发消息，用户向坐席发送消息和 坐席向用户发送消息  
    @OnEvent(value = "message")  
    public void onEvent(SocketIOClient client, AckRequest request, ChatMessage data) throws UnsupportedEncodingException   
    {
    	if(data.getType() == null){
    		data.setType("message");
    	}
    	/**
    	 * 以下代码主要用于检查 访客端的字数限制
    	 */
    	CousultInvite invite = OnlineUserUtils.cousult(data.getAppid(),data.getOrgi(), UKDataContext.getContext().getBean(ConsultInviteRepository.class));
    	if(invite!=null && invite.getMaxwordsnum() > 0) {
	    	if(!StringUtils.isBlank(data.getMessage()) && data.getMessage().length() > invite.getMaxwordsnum()){
	    		data.setMessage(data.getMessage().substring(0 , invite.getMaxwordsnum()));
	    	}
    	}else if(!StringUtils.isBlank(data.getMessage()) && data.getMessage().length() > 300){
    		data.setMessage(data.getMessage().substring(0 , 300));
    	}
    	/**
		 * 处理表情
		 */
    	data.setMessage(UKTools.processEmoti(data.getMessage()));
		
    	MessageUtils.createMessage(data , UKDataContext.MediaTypeEnum.TEXT.toString(), data.getUserid());
    } 
}  