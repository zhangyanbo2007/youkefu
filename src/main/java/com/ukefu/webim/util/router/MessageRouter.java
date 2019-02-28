package com.ukefu.webim.util.router;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.MessageDataBean;
import com.ukefu.webim.web.model.MessageOutContent;

public class MessageRouter extends Router{

	@Override
	public MessageDataBean handler(MessageDataBean inMessage) {
		MessageOutContent outMessage = new MessageOutContent() ;
		try {
			outMessage.setOrgi(inMessage.getOrgi());
			outMessage.setFromUser(inMessage.getToUser());
			outMessage.setToUser(inMessage.getFromUser());
			outMessage.setId(UKTools.genID());
			outMessage.setMessageType(inMessage.getMessageType());
			outMessage.setUser(inMessage.getUser());
			outMessage.setAgentUser(inMessage.getAgentUser());
			/**
			 * 首先交由 IMR处理 MESSAGE指令 ， 如果当前用户是在 坐席对话列表中， 则直接推送给坐席，如果不在，则执行 IMR
			 */
			if(outMessage.getAgentUser()!=null && outMessage.getAgentUser().getStatus()!=null){
				if(outMessage.getAgentUser().getStatus().equals(UKDataContext.AgentUserStatusEnum.INQUENE.toString())){
					int queneIndex = ServiceQuene.getQueneIndex(inMessage.getAgentUser().getAgent() , inMessage.getOrgi(), inMessage.getAgentUser().getSkill()) ;
					if(UKDataContext.AgentUserStatusEnum.INQUENE.toString().equals(outMessage.getAgentUser().getStatus())){
						outMessage.setMessage(ServiceQuene.getQueneMessage(queneIndex , outMessage.getAgentUser().getChannel(),inMessage.getOrgi()));
					}
				}else if(outMessage.getAgentUser().getStatus().equals(UKDataContext.AgentUserStatusEnum.INSERVICE.toString())){
					
				}
			}else if(UKDataContext.MessageTypeEnum.NEW.toString().equals(inMessage.getMessageType())){
				/**
				 * 找到空闲坐席，如果未找到坐席， 则将该用户放入到 排队队列 
				 * 
				 */
				AgentService agentService = ServiceQuene.allotAgent(inMessage.getAgentUser(), inMessage.getOrgi()) ;
				if(agentService!=null && UKDataContext.AgentUserStatusEnum.INSERVICE.toString().equals(agentService.getStatus())){
					outMessage.setMessage(ServiceQuene.getSuccessMessage(agentService , inMessage.getAgentUser().getChannel(),inMessage.getOrgi()));
					NettyClients.getInstance().sendAgentEventMessage(agentService.getAgentno(), UKDataContext.MessageTypeEnum.NEW.toString(), inMessage.getAgentUser());
				}else{
					if(agentService!=null && agentService.getQueneindex() > 0){	//当前有坐席
						outMessage.setMessage(ServiceQuene.getQueneMessage(agentService.getQueneindex(), inMessage.getAgentUser().getChannel(), inMessage.getOrgi()));
					}else{
						outMessage.setMessage(ServiceQuene.getNoAgentMessage(agentService.getQueneindex(), inMessage.getAgentUser().getChannel(), inMessage.getOrgi()));
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return outMessage ;
	}

}
