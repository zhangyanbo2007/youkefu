package com.ukefu.webim.util.server.handler;

import java.net.InetSocketAddress;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.IPTools;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.util.MessageUtils;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.util.router.OutMessageRouter;
import com.ukefu.webim.util.server.message.AgentStatusMessage;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.util.server.message.NewRequestMessage;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.AiConfig;
import com.ukefu.webim.web.model.AiUser;
import com.ukefu.webim.web.model.CousultInvite;
import com.ukefu.webim.web.model.IMClient;
import com.ukefu.webim.web.model.MessageOutContent;

public class AiIMEventHandler     
{  
	protected SocketIOServer server;
	
    @Autowired  
    public AiIMEventHandler(SocketIOServer server)   
    {  
        this.server = server ;
    }  
    
    @OnConnect  
    public void onConnect(SocketIOClient client)  
    {  
    }  
      
    
    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息  
    @OnDisconnect  
    public void onDisconnect(SocketIOClient client) throws Exception  
    {  
    	IMClient im = client.get("im") ;
    	if(im!=null && !StringUtils.isBlank(im.getUser())){
	    	NettyClients.getInstance().removeIMEventClient(im.getUser() , UKTools.getContextID(client.getSessionId().toString()));
	    	Object object = CacheHelper.getOnlineUserCacheBean().getCacheObject(im.getUser(), im.getOrgi()) ;
	    	if(object!=null && object instanceof AiUser) {
	    		AiUser aiUser = (AiUser)object ;
		    	ServiceQuene.processAiService(aiUser, im.getOrgi() , UKDataContext.EndByType.USER.toString()) ;
	    	}
	    	CacheHelper.getOnlineUserCacheBean().delete(im.getUser(),UKDataContext.SYSTEM_ORGI) ;
    	}
    	client.disconnect();
    }  
      
    //消息接收入口，网站有新用户接入对话  
    @OnEvent(value = "new")  
    public void onEvent(SocketIOClient client, AckRequest request ,IMClient im, NewRequestMessage data)   
    {
    	client.set("im", im);
    	
    	try {
			if(im!=null && !StringUtils.isBlank(im.getUser())){
//				/**
//				 * 加入到 缓存列表
//				 */
				NettyClients.getInstance().putIMEventClient(im.getUser(), client);
				MessageOutContent outMessage = new MessageOutContent() ;
				CousultInvite invite = OnlineUserUtils.cousult(im.getAppid(), im.getOrgi(), UKDataContext.getContext().getBean(ConsultInviteRepository.class));
		    	if(invite!=null && !StringUtils.isBlank(invite.getAisuccesstip())) {
		    		outMessage.setMessage(invite.getAisuccesstip());
		    	}else{
		    		outMessage.setMessage("欢迎使用优客服小E，我来帮您解答问题");
		    	}
		    	
		    	outMessage.setMessageType(UKDataContext.MessageTypeEnum.MESSAGE.toString());
		    	outMessage.setCalltype(UKDataContext.CallTypeEnum.IN.toString());
		    	outMessage.setNickName("AI");
				outMessage.setCreatetime(UKTools.dateFormate.format(new Date()));
				
				client.sendEvent(UKDataContext.MessageTypeEnum.STATUS.toString(), outMessage);
				
				if(!StringUtils.isBlank(im.getAiid())) {
					AiConfig aiConfig = UKTools.initAiConfig(im.getAiid(), im.getOrgi()) ;
					if(aiConfig!=null && !StringUtils.isBlank(aiConfig.getHotmsg()) && invite != null) {
						MessageOutContent hotMessage = new MessageOutContent() ;
						hotMessage.setMessage(invite.getAimsg());
						
						hotMessage.setMessageType(UKDataContext.MessageTypeEnum.MESSAGE.toString());
						hotMessage.setCalltype(UKDataContext.CallTypeEnum.OUT.toString());
						hotMessage.setNickName(invite.getAiname());
						hotMessage.setCreatetime(UKTools.dateFormate.format(new Date()));
						hotMessage.setSuggest(aiConfig.getHot());
						client.sendEvent(UKDataContext.MessageTypeEnum.MESSAGE.toString(), hotMessage);
					}
				}
				
				InetSocketAddress address = (InetSocketAddress) client.getRemoteAddress()  ;
				String ip = UKTools.getIpAddr(client.getHandshakeData().getHttpHeaders(), address.getHostString()) ;
				AiUser aiUser = new AiUser(im.getUser(), im.getUser(), System.currentTimeMillis() , im.getOrgi(),IPTools.getInstance().findGeography(ip)) ;
				aiUser.setSessionid(UKTools.getContextID(client.getSessionId().toString()));
				aiUser.setAppid(im.getAppid());
				aiUser.setAiid(im.getAiid());
				aiUser.setUsername(UKDataContext.GUEST_USER+"_"+UKTools.genIDByKey(aiUser.getId()));
				aiUser.setChannel(UKDataContext.ChannelTypeEnum.WEBIM.toString());
				
				aiUser.setSkill(im.getSkill());
				
				AgentService agentService = ServiceQuene.processAiService(aiUser, im.getOrgi() , null) ;
				aiUser.setAgentserviceid(agentService.getId());
				
				CacheHelper.getOnlineUserCacheBean().put(im.getUser(), aiUser, UKDataContext.SYSTEM_ORGI);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
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
    public void onEvent(SocketIOClient client, AckRequest request, ChatMessage data)   
    {
    	IMClient im = client.get("im") ;
    	if(im!=null) {
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
	    	data.setSessionid(UKTools.getContextID(client.getSessionId().toString()));
	    	/**
			 * 处理表情
			 */
	    	data.setMessage(UKTools.processEmoti(data.getMessage()));
	    	data.setTousername(UKDataContext.ChannelTypeEnum.AI.toString());
	    	
	    	data.setAiid(im.getAiid());
	    	
	    	Object cacheData = CacheHelper.getOnlineUserCacheBean().getCacheObject(im.getUser(),im.getOrgi()) ;
	    	if(cacheData!=null && cacheData instanceof AiUser){
				AiUser aiUser = (AiUser)cacheData ;
				data.setAgentserviceid(aiUser.getAgentserviceid());
				data.setChannel(aiUser.getChannel());
				/**
				 * 一定要设置 ContextID
				 */
				data.setContextid(aiUser.getAgentserviceid());
			}
	    	MessageOutContent outMessage = MessageUtils.createAiMessage(data , data.getAppid() , data.getChannel() , UKDataContext.CallTypeEnum.IN.toString() , UKDataContext.AiItemType.USERINPUT.toString() , UKDataContext.MediaTypeEnum.TEXT.toString(), data.getUserid()) ;
	    	if(!StringUtils.isBlank(data.getUserid()) && UKDataContext.MessageTypeEnum.MESSAGE.toString().equals(data.getType())){
	    		if(!StringUtils.isBlank(data.getTouser())){
		    		OutMessageRouter router = null ; 
		    		router  = (OutMessageRouter) UKDataContext.getContext().getBean(data.getChannel()) ;
		    		if(router!=null){
		    			router.handler(data.getTouser(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), data.getAppid(), outMessage);
		    		}
		    	}
	    		if(cacheData!=null && cacheData instanceof AiUser){
	    			AiUser aiUser = (AiUser)cacheData ;
	    			aiUser.setTime(System.currentTimeMillis());
	    			CacheHelper.getOnlineUserCacheBean().put(im.getUser(), aiUser, UKDataContext.SYSTEM_ORGI);
	    		}
	    	}
	    	UKTools.ai(data);
    	}
    } 
}  