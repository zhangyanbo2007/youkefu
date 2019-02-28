package com.ukefu.webim.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.AgentUserTaskRepository;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.web.model.AgentUser;
import com.ukefu.webim.web.model.AgentUserTask;
import com.ukefu.webim.web.model.AiUser;
import com.ukefu.webim.web.model.CousultInvite;
import com.ukefu.webim.web.model.MessageOutContent;

public class MessageUtils {
	/**
	 * 
	 * @param image
	 * @param userid
	 */
	public static ChatMessage uploadImage(String image ,String attachid, int size , String name  , String userid){
		return createMessage(image , size , name , UKDataContext.MediaTypeEnum.IMAGE.toString(), userid , attachid);
	}
	public static ChatMessage uploadImage(String image  , String attachid, int size , String name , String channel , String userid , String username , String appid , String orgi){
		return createMessage(image , size , name , channel , UKDataContext.MediaTypeEnum.IMAGE.toString(), userid , username, appid , orgi , attachid);
	}
	
	/**
	 * 
	 * @param image
	 * @param userid
	 */
	public static ChatMessage uploadFile(String url , int size , String name , String userid , String attachid){
		return createMessage(url , size , name , UKDataContext.MediaTypeEnum.FILE.toString(), userid , attachid);
	}
	public static ChatMessage uploadFile(String url , int size , String name, String channel , String userid , String username , String appid , String orgi , String attachid){
		return createMessage(url , size , name , channel , UKDataContext.MediaTypeEnum.FILE.toString(), userid , username, appid , orgi , attachid);
	}
	
	/**
	 * 
	 * @param image
	 * @param userid
	 */
	public static ChatMessage uploadVoice(String url , int size , String name , String userid , String attachid , int duration){
		return createMessage(url , size , name , UKDataContext.MediaTypeEnum.VOICE.toString(), userid , attachid , duration);
	}
	public static ChatMessage uploadVoice(String url , int size , String name, String channel , String userid , String username , String appid , String orgi , String attachid, int duration){
		return createMessage(url , size , name , channel , UKDataContext.MediaTypeEnum.VOICE.toString(), userid , username, appid , orgi , attachid , duration);
	}
	public static ChatMessage createMessage(String message , int length , String name , String msgtype , String userid , String attachid){
		return createMessage(message, length, name, msgtype, userid, attachid, 0) ;
	}
	
	public static ChatMessage createMessage(String message , int length , String name , String msgtype , String userid , String attachid , int duration){
		AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(userid, UKDataContext.SYSTEM_ORGI);
		ChatMessage data = new ChatMessage() ;
		data.setFilesize(length);
		data.setFilename(name);
		data.setAttachmentid(attachid);
		
		data.setMessage(message);
		
		data.setMsgtype(msgtype);
		data.setDuration(duration);
		data.setType(UKDataContext.MessageTypeEnum.MESSAGE.toString());
		
		if(agentUser != null){
			data.setUserid(agentUser.getUserid());
			data.setUsername(agentUser.getUsername());
			data.setTouser(agentUser.getAgentno());
			data.setAppid(agentUser.getAppid());
			data.setOrgi(agentUser.getOrgi());
			createMessage(data, msgtype, userid);
		}else {
			AiUser aiUser = (AiUser) CacheHelper.getOnlineUserCacheBean().getCacheObject(userid,UKDataContext.SYSTEM_ORGI) ;
			data.setUserid(userid);
			if(aiUser!=null) {
				data.setAppid(aiUser.getAppid());
				data.setAiid(aiUser.getAiid());
				data.setUsername(aiUser.getUsername());
				data.setOrgi(aiUser.getOrgi());
				MessageOutContent outMessage = createAiMessage(data , data.getAppid() , aiUser.getChannel() , UKDataContext.CallTypeEnum.IN.toString() , UKDataContext.AiItemType.USERINPUT.toString() , msgtype, data.getUserid());
				sendMessage(data , outMessage, msgtype);
				UKTools.ai(data);
			}
		}
		return data ;
	}
	/**
	 * 发送消息
	 * @param data
	 * @param msgtype
	 */
	private static void sendMessage(ChatMessage data ,MessageOutContent outMessage , String msgtype) {
		if(!StringUtils.isBlank(data.getUserid()) && UKDataContext.MessageTypeEnum.MESSAGE.toString().equals(data.getType())){
    		NettyClients.getInstance().sendIMEventMessage(data.getUserid(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), outMessage);
    	}
	}
	
	public static ChatMessage createMessage(String message , int length , String name ,String channel ,String msgtype , String userid , String username , String appid , String orgi , String attachid ){
		return createMessage(message, length, name, channel, msgtype, userid, username, appid, orgi, attachid, 0) ;
	}
	
	private static ChatMessage createMessage(String message , int length , String name ,String channel ,String msgtype , String userid , String username , String appid , String orgi , String attachid , int duration){
		ChatMessage data = new ChatMessage() ;
		if(!StringUtils.isBlank(userid)){
			data.setUserid(userid);
			data.setUsername(username);
			data.setTouser(userid);
			data.setAppid(appid);
			data.setOrgi(orgi);
			data.setChannel(channel);
			data.setMessage(message);
			
			data.setFilesize(length);
			data.setFilename(name);
			data.setAttachmentid(attachid);
			
			data.setMsgtype(msgtype);
			
			data.setDuration(duration);
			
			data.setType(UKDataContext.MessageTypeEnum.MESSAGE.toString());
			createAiMessage(data , appid , channel, UKDataContext.CallTypeEnum.IN.toString() , UKDataContext.AiItemType.USERINPUT.toString() , msgtype, data.getUserid());
		}
		return data ;
	}
	
	public static void createMessage(ChatMessage data , String msgtype , String userid){
		AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(userid, UKDataContext.SYSTEM_ORGI);
    	MessageOutContent outMessage = new MessageOutContent() ;
    	
    	outMessage.setMessage(data.getMessage());
    	outMessage.setFilename(data.getFilename());
    	outMessage.setFilesize(data.getFilesize());
    	
    	
    	outMessage.setMessageType(msgtype);
    	outMessage.setCalltype(UKDataContext.CallTypeEnum.IN.toString());
    	outMessage.setAgentUser(agentUser);
    	outMessage.setSnsAccount(null);
    	outMessage.setDuration(data.getDuration());
    	
    	outMessage.setId(data.getId());
    	
    	MessageOutContent statusMessage = null ;
    	if(agentUser==null){
    		statusMessage = new MessageOutContent() ;
    		statusMessage.setMessage(data.getMessage());
    		statusMessage.setMessageType(UKDataContext.MessageTypeEnum.STATUS.toString());
    		statusMessage.setCalltype(UKDataContext.CallTypeEnum.OUT.toString());
    		statusMessage.setMessage("当前坐席全忙，请稍候");
    	}else{
    		data.setUserid(agentUser.getUserid());
    		data.setTouser(agentUser.getAgentno());
    		data.setUsername(agentUser.getUsername());
    		data.setAgentuser(agentUser.getId());
    		
    		data.setAgentserviceid(agentUser.getAgentserviceid());
    		
    		data.setAppid(agentUser.getAppid());
    		data.setOrgi(agentUser.getOrgi());
    		
    		data.setMsgtype(msgtype);
    		
    		
    		data.setUsession(agentUser.getUserid());				//agentUser作为 session id
    		data.setContextid(agentUser.getContextid());
    		data.setCalltype(UKDataContext.CallTypeEnum.IN.toString());
    		if(!StringUtils.isBlank(agentUser.getAgentno())){
    			data.setTouser(agentUser.getAgentno());
    		}
    		data.setChannel(agentUser.getChannel());
    		
    		outMessage.setContextid(agentUser.getContextid());
    		outMessage.setFromUser(data.getUserid());
    		outMessage.setToUser(data.getTouser());
    		outMessage.setChannelMessage(data);
    		outMessage.setNickName(agentUser.getUsername());
    		outMessage.setCreatetime(data.getCreatetime());
    		
    		if(data.getType()!=null && data.getType().equals(UKDataContext.MessageTypeEnum.MESSAGE.toString())){
	    		AgentUserTaskRepository agentUserTaskRes = UKDataContext.getContext().getBean(AgentUserTaskRepository.class) ;
	    		AgentUserTask agentUserTask = agentUserTaskRes.getOne(agentUser.getId()) ;
	    		if(agentUserTask!=null){
	    			if(agentUserTask.getLastgetmessage() != null && agentUserTask.getLastmessage()!=null){
		    			data.setLastagentmsgtime(agentUserTask.getLastgetmessage());
		    			data.setLastmsgtime(agentUserTask.getLastmessage());
		    			data.setAgentreplyinterval((int)((System.currentTimeMillis() - agentUserTask.getLastgetmessage().getTime())/1000));	//坐席上次回复消息的间隔
		    			data.setAgentreplytime((int)((System.currentTimeMillis() - agentUserTask.getLastmessage().getTime())/1000));		//坐席回复消息花费时间
	    			}
	    			agentUserTask.setUserasks(agentUserTask.getUserasks()+1);	//总咨询记录数量
	    			agentUserTask.setAgentreplytime(agentUserTask.getAgentreplytime() + data.getAgentreplyinterval());	//总时长
	    			if(agentUserTask.getUserasks()>0){
	    				agentUserTask.setAvgreplytime(agentUserTask.getAgentreplytime() / agentUserTask.getUserasks());
	    			}
	    			
		    		agentUserTask.setLastmessage(new Date());
		    		agentUserTask.setWarnings("0");
		    		agentUserTask.setWarningtime(null);
		    		
		    		/**
		    		 * 去掉坐席超时回复消息提醒
		    		 */
		    		agentUserTask.setReptime(null);
		    		agentUserTask.setReptimes("0");
		    		
		    		agentUserTask.setLastmsg(data.getMessage().length() > 100 ? data.getMessage().substring(0 , 100) : data.getMessage());
		    		agentUserTask.setTokenum(agentUserTask.getTokenum()+1);
		    		data.setTokenum(agentUserTask.getTokenum());
		    		agentUserTaskRes.save(agentUserTask) ;
	    		}
    		}
    		
    		/**
    		 * 保存消息
    		 */
    		if(UKDataContext.MessageTypeEnum.MESSAGE.toString().equals(data.getType())){
    			UKDataContext.getContext().getBean(ChatMessageRepository.class).save(data) ;
    		}
    		CousultInvite invite = OnlineUserUtils.cousult(agentUser.getAppid(), agentUser.getOrgi(), UKDataContext.getContext().getBean(ConsultInviteRepository.class)) ;
    		if(invite != null && invite.isAgentshowcontacts() && !StringUtils.isBlank(agentUser.getName())) {
				data.setUsername(agentUser.getName());
			}else {
				data.setUsername(agentUser.getUsername());
			}
    	}
    	if(!StringUtils.isBlank(data.getUserid()) && UKDataContext.MessageTypeEnum.MESSAGE.toString().equals(data.getType())){
    		NettyClients.getInstance().sendIMEventMessage(data.getUserid(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), outMessage);
    		if(statusMessage!=null){
    			NettyClients.getInstance().sendIMEventMessage(data.getUserid(), UKDataContext.MessageTypeEnum.STATUS.toString(), statusMessage);
    		}
    	}
    	if(agentUser!=null && !StringUtils.isBlank(agentUser.getAgentno())){
    		//将消息发送给 坐席
    		NettyClients.getInstance().sendAgentEventMessage(agentUser.getAgentno(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), data);
    	}
	}
	
	public static ChatMessage createMessage(String message , int length , String name ,String channel ,String msgtype , String userid , String username , String appid , String orgi , String attachid ,String aiid){
		ChatMessage data = new ChatMessage() ;
		if(!StringUtils.isBlank(userid)){
			data.setUserid(userid);
			data.setUsername(username);
			data.setTouser(userid);
			data.setAppid(appid);
			data.setOrgi(orgi);
			data.setChannel(channel);
			data.setMessage(message);
			
			data.setAiid(aiid);
			
			data.setFilesize(length);
			data.setFilename(name);
			data.setAttachmentid(attachid);
			
			data.setMsgtype(msgtype);
			
			data.setType(UKDataContext.MessageTypeEnum.MESSAGE.toString());
			createAiMessage(data , appid , channel, UKDataContext.CallTypeEnum.IN.toString() , UKDataContext.AiItemType.USERINPUT.toString() , msgtype, data.getUserid());
		}
		return data ;
	}
	
	public static MessageOutContent createAiMessage(ChatMessage data , String appid,String channel , String direction , String chatype, String msgtype , String userid){
    	MessageOutContent outMessage = new MessageOutContent() ;
    	
    	outMessage.setMessage(data.getMessage());
    	outMessage.setMessageType(msgtype);
    	outMessage.setCalltype(direction);
    	outMessage.setAgentUser(null);
    	outMessage.setSnsAccount(null);
    	outMessage.setDuration(data.getDuration());
    	
    	if(!StringUtils.isBlank(userid)){
    		data.setUserid(userid);
    		data.setTouser(userid);
    		
    		data.setAgentuser(userid);
    		
    		data.setChatype(chatype);
    		
    		data.setChannel(channel);
    		
    		data.setAppid(data.getAppid());
    		data.setOrgi(data.getOrgi());
    		
    		data.setMsgtype(msgtype);
    		
    		data.setUsession(data.getUserid());				//agentUser作为 session id
    		data.setCalltype(direction);
    		
    		if(data.isQuickagent()) {
    			outMessage.setQuickagent(true);
    		}
    		outMessage.setContextid(data.getContextid());
    		outMessage.setFromUser(data.getUserid());
    		outMessage.setToUser(data.getTouser());
    		outMessage.setChannelMessage(data);
    		outMessage.setNickName(data.getUsername());
    		outMessage.setCreatetime(data.getCreatetime());
    		outMessage.setTopic(!StringUtils.isBlank(data.getTopicid()));
    		if(!StringUtils.isBlank(data.getSuggestmsg())) {
    			outMessage.setSuggest(data.getSuggest());
    		}
    		
    		data.setUpdatetime(System.currentTimeMillis());
    		
    		
    		/**
    		 * 保存消息
    		 */
    		if(UKDataContext.MessageTypeEnum.MESSAGE.toString().equals(data.getType())){
    			UKDataContext.getContext().getBean(ChatMessageRepository.class).save(data) ;
    		}
    		outMessage.setId(data.getId());
    		AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(userid, UKDataContext.SYSTEM_ORGI);
    		if(agentUser!=null && !StringUtils.isBlank(agentUser.getAgentno())){
        		//将消息发送给 坐席
    			
    			if(UKDataContext.CallTypeEnum.OUT.toString().equals(direction)) {
    				data.setUserid(agentUser.getAgentno());
    			}
        		NettyClients.getInstance().sendAgentEventMessage(agentUser.getAgentno(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), data);
        	}
    	}
    	return outMessage ;
	}
	
}
