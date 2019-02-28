package com.ukefu.webim.service.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.impl.CallOutQuene;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.service.repository.AgentUserTaskRepository;
import com.ukefu.webim.service.repository.BlackListRepository;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.OnlineUserRepository;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.util.router.OutMessageRouter;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.AgentStatus;
import com.ukefu.webim.web.model.AgentUser;
import com.ukefu.webim.web.model.AgentUserTask;
import com.ukefu.webim.web.model.AiConfig;
import com.ukefu.webim.web.model.AiUser;
import com.ukefu.webim.web.model.BlackEntity;
import com.ukefu.webim.web.model.CousultInvite;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MessageOutContent;
import com.ukefu.webim.web.model.OnlineUser;
import com.ukefu.webim.web.model.SessionConfig;

@Configuration
@EnableScheduling
public class WebIMTask {
	
	@Autowired
	private AgentUserTaskRepository agentUserTaskRes ;
	
	@Autowired
	private AgentServiceRepository agentServiceRes ;
	
	@Autowired
	private OnlineUserRepository onlineUserRes ;
	
	@Autowired
	private AgentServiceRepository agentServiceRepository;
	
	@Autowired
	private JobDetailRepository jobDetailRes ;
	
	@Autowired
	private BlackListRepository blackListRes ;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Scheduled(fixedDelay= 5000) // 每5秒执行一次
    public void task() {
		List<SessionConfig> sessionConfigList = ServiceQuene.initSessionConfigList() ;
		if(sessionConfigList!=null && sessionConfigList.size() > 0 && UKDataContext.getContext() != null && UKDataContext.needRunTask()){
			for(SessionConfig sessionConfig : sessionConfigList) {
				if(sessionConfig.isSessiontimeout()){		//设置了启用 超时提醒
					List<AgentUserTask> agentUserTask = agentUserTaskRes.findByLastmessageLessThanAndStatusAndOrgi(UKTools.getLastTime(sessionConfig.getTimeout()) , UKDataContext.AgentUserStatusEnum.INSERVICE.toString() , sessionConfig.getOrgi()) ;
					for(AgentUserTask task : agentUserTask){		// 超时未回复
						AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(task.getUserid(), UKDataContext.SYSTEM_ORGI);
						if(agentUser!=null && agentUser.getAgentno()!=null){
							AgentStatus agentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(agentUser.getAgentno(), task.getOrgi()) ;
							task.setAgenttimeouttimes(task.getAgenttimeouttimes()+1);
							if(agentStatus!=null && (task.getWarnings()==null || task.getWarnings().equals("0"))){
								task.setWarnings("1");
								task.setWarningtime(new Date());
								
								//发送提示消息
								processMessage(sessionConfig, sessionConfig.getTimeoutmsg() , agentStatus.getUsername(),agentUser , agentStatus , task);
								agentUserTaskRes.save(task) ;
							}else if(sessionConfig.isResessiontimeout() && agentStatus!=null && task.getWarningtime()!=null && UKTools.getLastTime(sessionConfig.getRetimeout()).after(task.getWarningtime())){	//再次超时未回复
								/**
								 * 设置了再次超时 断开
								 */
								processMessage(sessionConfig, sessionConfig.getRetimeoutmsg() , sessionConfig.getServicename() , agentUser , agentStatus , task);
								try {
									ServiceQuene.serviceFinish(agentUser, task.getOrgi() , UKDataContext.EndByType.TIMEOUT.toString());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}else if(sessionConfig.isResessiontimeout()){	//未启用超时提醒，只设置了超时断开
					List<AgentUserTask> agentUserTask = agentUserTaskRes.findByLastmessageLessThanAndStatusAndOrgi(UKTools.getLastTime(sessionConfig.getRetimeout()) , UKDataContext.AgentUserStatusEnum.INSERVICE.toString() , sessionConfig.getOrgi()) ;
					for(AgentUserTask task : agentUserTask){		// 超时未回复
						AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(task.getUserid(), UKDataContext.SYSTEM_ORGI);
						if(agentUser!=null){
							AgentStatus agentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(agentUser.getAgentno(), task.getOrgi()) ;
							if(agentStatus!=null && task.getWarningtime()!=null && UKTools.getLastTime(sessionConfig.getRetimeout()).after(task.getWarningtime())){	//再次超时未回复
								/**
								 * 设置了再次超时 断开
								 */
								processMessage(sessionConfig, sessionConfig.getRetimeoutmsg() , agentStatus.getUsername(), agentUser , agentStatus , task);
								try {
									ServiceQuene.serviceFinish(agentUser, task.getOrgi() , UKDataContext.EndByType.TIMEOUT.toString());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				if(sessionConfig.isQuene()){	//启用排队超时功能，超时断开
					List<AgentUserTask> agentUserTask = agentUserTaskRes.findByLogindateLessThanAndStatusAndOrgi(UKTools.getLastTime(sessionConfig.getQuenetimeout()) , UKDataContext.AgentUserStatusEnum.INQUENE.toString() , sessionConfig.getOrgi()) ;
					for(AgentUserTask task : agentUserTask){		// 超时未回复
						AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(task.getUserid(), UKDataContext.SYSTEM_ORGI);
						if(agentUser!=null){
							/**
							 * 设置了超时 断开
							 */
							processMessage(sessionConfig, sessionConfig.getQuenetimeoutmsg() , sessionConfig.getServicename(), agentUser , null , task);
							try {
								ServiceQuene.serviceFinish(agentUser, task.getOrgi() , UKDataContext.EndByType.QUEUE.toString());
								CacheHelper.getAgentUserCacheBean().delete(agentUser.getUserid(), UKDataContext.SYSTEM_ORGI);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	@Scheduled(fixedDelay= 5000) // 每5秒执行一次
    public void agent() {
		List<SessionConfig> sessionConfigList = ServiceQuene.initSessionConfigList() ;
		if(sessionConfigList!=null && sessionConfigList.size() > 0 && UKDataContext.getContext() != null && UKDataContext.needRunTask()){
			for(SessionConfig sessionConfig : sessionConfigList) {
				sessionConfig = ServiceQuene.initSessionConfig(sessionConfig.getOrgi()) ;
				if(sessionConfig!=null && UKDataContext.getContext() != null && sessionConfig.isAgentreplaytimeout()){
					List<AgentUserTask> agentUserTask = agentUserTaskRes.findByLastgetmessageLessThanAndStatusAndOrgi(UKTools.getLastTime(sessionConfig.getAgenttimeout()) , UKDataContext.AgentUserStatusEnum.INSERVICE.toString() , sessionConfig.getOrgi()) ;
					for(AgentUserTask task : agentUserTask){		// 超时未回复
						AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(task.getUserid(), UKDataContext.SYSTEM_ORGI);
						if(agentUser!=null){
							AgentStatus agentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(agentUser.getAgentno(), task.getOrgi()) ;
							if(agentStatus!=null && ( (task.getReptimes()!=null && task.getReptimes().equals("0")) || task.getReptimes() == null  )){
								task.setReptimes("1");
								task.setReptime(new Date());
								
								//发送提示消息
								processMessage(sessionConfig, sessionConfig.getAgenttimeoutmsg() , sessionConfig.getServicename(),agentUser , agentStatus , task);
								agentUserTaskRes.save(task) ;
							}
						}
					}
				}
			}
		}
	}
	
	@Scheduled(fixedDelay= 600000) // 每分钟执行一次
    public void onlineuser() {
		if(UKDataContext.needRunTask()) {
			Page<OnlineUser> pages = onlineUserRes.findByStatusAndCreatetimeLessThan(UKDataContext.OnlineUserOperatorStatus.ONLINE.toString(), UKTools.getLastTime(60), new PageRequest(0,  100)) ;
			if(pages.getContent().size()>0){
				for(OnlineUser onlineUser : pages.getContent()){
					try {
						OnlineUserUtils.offline(onlineUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Scheduled(fixedDelay= 10000) // 每10秒执行一次 ， 负责将当期在线的访客序列化到 数据库
    public void traceOnlineUser() {
		if(UKDataContext.getContext()!=null && UKDataContext.needRunTask()){	//判断系统是否启动完成，避免 未初始化完成即开始执行 任务
			long onlineusers = CacheHelper.getOnlineUserCacheBean().getSize() ;
			if(onlineusers > 0){
				Collection<?> datas = CacheHelper.getOnlineUserCacheBean().getAllCacheObject(UKDataContext.SYSTEM_ORGI) ;
				ConsultInviteRepository consultInviteRes = UKDataContext.getContext().getBean(ConsultInviteRepository.class) ;
				for(Object key : datas){
					Object data = CacheHelper.getOnlineUserCacheBean().getCacheObject(key.toString(), UKDataContext.SYSTEM_ORGI) ;
					if(data instanceof OnlineUser){
						OnlineUser onlineUser = (OnlineUser)data ;
						if(onlineUser.getUpdatetime()!=null && (System.currentTimeMillis() - onlineUser.getUpdatetime().getTime()) < 15000){
							CousultInvite invite = OnlineUserUtils.cousult(onlineUser.getAppid(), onlineUser.getOrgi(), consultInviteRes) ;
							if(invite.isTraceuser()){
								OnlineUserRepository service = (OnlineUserRepository) UKDataContext.getContext().getBean(OnlineUserRepository.class);
								List<OnlineUser> onlineUserList = service.findByUseridAndOrgi(onlineUser.getUserid() , onlineUser.getOrgi());
								if(onlineUserList .size() > 1){
									service.delete(onlineUserList);
								}else if(onlineUserList .size() == 1){
									OnlineUser tempOnlineUser = onlineUserList.get(0) ;
									onlineUser.setId(tempOnlineUser.getId());
								}
								service.save(onlineUser) ;
							}
						}
					}else if(data instanceof AiUser){
						AiUser aiUser = (AiUser)data ;
						if(UKDataContext.model.get("xiaoe")!=null){
							DataExchangeInterface dataInterface = (DataExchangeInterface) UKDataContext.getContext().getBean("aiconfig") ;
							AiConfig aiConfig = (AiConfig) dataInterface.getDataByIdAndOrgi(aiUser.getAiid(), aiUser.getOrgi()) ;
							if(aiConfig!=null){
								long leavetime = (System.currentTimeMillis() - aiUser.getTime())/1000 ;
								if(aiConfig.getAsktimes()>0 && leavetime > aiConfig.getAsktimes()){//最大空闲时间不能超过540秒 
									NettyClients.getInstance().closeIMEventClient(aiUser.getUserid(), aiUser.getId(), UKDataContext.SYSTEM_ORGI) ;
								}
							}
						}else{
							NettyClients.getInstance().closeIMEventClient(aiUser.getUserid(), aiUser.getId(), UKDataContext.SYSTEM_ORGI) ;
						}
					}
				}
			}
		}
	}
	/**
	 * 		appid : appid ,
			userid:userid,
			sign:session,
			touser:touser,
			session: session ,
			orgi:orgi,
			username:agentstatus,
			nickname:agentstatus,
			message : message
	 * @param sessionConfig
	 * @param agentUser
	 * @param task
	 */
	
	private void processMessage(SessionConfig sessionConfig , String message, String servicename, AgentUser agentUser , AgentStatus agentStatus , AgentUserTask task){

		MessageOutContent outMessage = new MessageOutContent() ;
		if(!StringUtils.isBlank(message)){
	    	outMessage.setMessage(message);
	    	outMessage.setMessageType(UKDataContext.MediaTypeEnum.TEXT.toString());
	    	outMessage.setCalltype(UKDataContext.CallTypeEnum.OUT.toString());
	    	outMessage.setAgentUser(agentUser);
	    	outMessage.setSnsAccount(null);
	    	
	    	ChatMessage data = new ChatMessage();
	    	if(agentUser!=null){
	    		data.setAppid(agentUser.getAppid());
	    		
	    		data.setUserid(agentUser.getUserid());
	    		data.setUsession(agentUser.getUserid());
	    		data.setTouser(agentUser.getUserid());
	    		data.setOrgi(agentUser.getOrgi());
	    		data.setUsername(agentUser.getUsername());
	    		data.setMessage(message);
	    		
	    		data.setId(UKTools.getUUID());
	    		data.setContextid(agentUser.getContextid());
	    		
	    		data.setAgentserviceid(agentUser.getAgentserviceid());
	    		
	    		data.setCalltype(UKDataContext.CallTypeEnum.OUT.toString());
	    		if(!StringUtils.isBlank(agentUser.getAgentno())){
	    			data.setTouser(agentUser.getUserid());
	    		}
	    		data.setChannel(agentUser.getChannel());
	    		
	    		data.setUsession(agentUser.getUserid());
	    		
	    		outMessage.setContextid(agentUser.getContextid());
	    		outMessage.setFromUser(data.getUserid());
	    		outMessage.setToUser(data.getTouser());
	    		outMessage.setChannelMessage(data);
	    		if(agentStatus!=null){
	        		data.setUsername(agentStatus.getUsername());
	        		outMessage.setNickName(agentStatus.getUsername());
	        	}else {
	        		data.setUsername(servicename);
	        		outMessage.setNickName(servicename);
	        	}
	    		outMessage.setCreatetime(data.getCreatetime());
	    		
	    		/**
	    		 * 保存消息
	    		 */
	    		UKDataContext.getContext().getBean(ChatMessageRepository.class).save(data) ;
	
	    		if(agentUser!=null && !StringUtils.isBlank(agentUser.getAgentno())){	//同时发送消息给双方
	        		NettyClients.getInstance().sendAgentEventMessage(agentUser.getAgentno(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), data);
	        	}

                if(!StringUtils.isBlank(data.getTouser())){
                    OutMessageRouter router = null ;
                    router  = (OutMessageRouter) UKDataContext.getContext().getBean(agentUser.getChannel()) ;
                    if(router!=null){
                        router.handler(data.getTouser(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), agentUser.getAppid(), outMessage);
                    }
                }

	    	}
		}
	}
	
	
	@Scheduled(fixedDelay= 3000 , initialDelay = 20000) // 每三秒 , 加载 标记为执行中的任务何 即将执行的 计划任务
    public void jobDetail() {
		if(UKDataContext.needRunTask()) {
			List<JobDetail> allJob = new ArrayList<JobDetail>();
			Page<JobDetail> readyTaskList = jobDetailRes.findByTaskstatus(UKDataContext.TaskStatusType.READ.getType() , new PageRequest(0,  100)) ;
			allJob.addAll(readyTaskList.getContent()) ;
			Page<JobDetail> planTaskList = jobDetailRes.findByPlantaskAndTaskstatusAndNextfiretimeLessThan(true , UKDataContext.TaskStatusType.NORMAL.getType() ,new Date(), new PageRequest(0,  100)) ;
			allJob.addAll(planTaskList.getContent()) ;
			if(allJob.size()>0){
				for(JobDetail jobDetail : allJob){
					if(CacheHelper.getJobCacheBean().getCacheObject(jobDetail.getId(), jobDetail.getOrgi()) == null) {
						jobDetail.setTaskstatus(UKDataContext.TaskStatusType.QUEUE.getType());
						jobDetailRes.save(jobDetail) ;
						CacheHelper.getJobCacheBean().put(jobDetail.getId(), jobDetail, jobDetail.getOrgi());
						/**
						 * 加入到作业执行引擎
						 */
						taskExecutor.execute(new Task(jobDetail, jobDetailRes));
					}
				}
			}
		}
	}
	
	@Scheduled(fixedDelay= 3000 , initialDelay = 20000) // 每三秒 , 加载 标记为执行中的任务何 即将执行的 计划任务
    public void callOut() {
		if(UKDataContext.model.get("sales")!=null && UKDataContext.needRunTask()) {
			/**
			 * 遍历 队列， 然后推送 名单
			 */
			List<CallCenterAgent> agents = CallOutQuene.service() ;
			for(CallCenterAgent agent : agents) {
				taskExecutor.execute(new NamesTask(agent));
			}
		}
	}
	
	
	@Scheduled(fixedDelay= 5000 , initialDelay = 20000) // 每三秒 , 加载 标记为执行中的任务何 即将执行的 计划任务
    public void blackListClean() {
		if(UKDataContext.needRunTask()) {
			Page<BlackEntity> blackList = blackListRes.findByEndtimeLessThan(new Date(), new PageRequest(0, 20)) ;
			for(BlackEntity blackEntity : blackList) {
				blackListRes.delete(blackEntity);
				CacheHelper.getSystemCacheBean().getCacheObject(blackEntity.getUserid(),  blackEntity.getOrgi())  ;
			}
		}
	}
	
	@Scheduled(fixedDelay= 3000 , initialDelay = 20000) 
    public void checkInService() throws Exception {
		if(UKDataContext.needRunTask()) {
			Page<AgentService> agentServiceList = agentServiceRes.findAll(new Specification<AgentService>(){
				@Override
				public Predicate toPredicate(Root<AgentService> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();  
					list.add(cb.lessThan(root.get("createtime").as(Date.class), new Date(System.currentTimeMillis() - 1000 * 60 * 10))) ;
					list.add(cb.equal(root.get("status").as(String.class),UKDataContext.AgentUserStatusEnum.INSERVICE.toString())) ;
					Predicate[] p = new Predicate[list.size()];  

					return cb.and(list.toArray(p));
				}}, new PageRequest(0, 100, Sort.Direction.ASC, "createtime")) ;
			if(agentServiceList.getContent().size()>0) {
				List<AgentService> serviceList = new ArrayList<AgentService>();
				for(AgentService agentService : agentServiceList.getContent()) {
					if(CacheHelper.getAgentUserCacheBean().getCacheObject(agentService.getUserid(), UKDataContext.SYSTEM_ORGI) == null) {
						if(agentService.getCreatetime() == null || (System.currentTimeMillis() - agentService.getCreatetime().getTime()) > 1000 * 60 * 10) {
							agentService.setStatus(UKDataContext.AgentUserStatusEnum.END.toString());
							serviceList.add(agentService) ;
						}
					}
				}
				if(serviceList.size() > 0) {
					agentServiceRepository.save(agentServiceList) ;
				}
			}
		}
	}
}
