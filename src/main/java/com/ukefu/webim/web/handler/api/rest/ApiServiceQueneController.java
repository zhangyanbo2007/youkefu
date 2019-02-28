package com.ukefu.webim.web.handler.api.rest;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.AgentStatusRepository;
import com.ukefu.webim.service.repository.AgentUserRepository;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AgentStatus;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.SessionConfig;
import com.ukefu.webim.web.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/servicequene")
@Api(value = "ACD服务", description = "获取队列统计信息")
public class ApiServiceQueneController extends Handler{

	@Autowired
	private AgentStatusRepository agentStatusRepository ;
	

	@Autowired
	private AgentUserRepository agentUserRepository ;

	@Autowired
	private OrganRepository organRes ;
	/**
	 * 返回当前队列信息
	 * @param request
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "user" , access = true)
	@ApiOperation("获取队列统计信息，包含当前队列服务中的访客数，排队人数，坐席数")
    public ResponseEntity<RestResult> list(HttpServletRequest request) {
        return new ResponseEntity<>(new RestResult(RestResultType.OK , ServiceQuene.getAgentReport(super.getOrgi(request))), HttpStatus.OK);
    }
	
	/**
	 * 返回当前队列信息
	 * @param request
	 * @return
	 */
	@RequestMapping( method = RequestMethod.PUT)
	@Menu(type = "apps" , subtype = "user" , access = true)
	@ApiOperation("坐席状态操作，就绪、未就绪、忙")
    public ResponseEntity<RestResult> agentStatus(HttpServletRequest request , @Valid String status) {
		User user = super.getUser(request) ;
		AgentStatus agentStatus = null ;
		if(!StringUtils.isBlank(status) && status.equals(UKDataContext.AgentStatusEnum.READY.toString())) {
			List<AgentStatus> agentStatusList = agentStatusRepository.findByAgentnoAndOrgi(user.getId() , super.getOrgi(request));
	    	if(agentStatusList.size() > 0){
	    		agentStatus = agentStatusList.get(0) ;
	    	}else{
	    		agentStatus = new AgentStatus() ;
		    	agentStatus.setUserid(user.getId());
		    	agentStatus.setUsername(user.getUname());
		    	agentStatus.setAgentno(user.getId());
		    	agentStatus.setLogindate(new Date());
		    	
		    	if(!StringUtils.isBlank(user.getOrgan())){
		    		Organ organ = organRes.findByIdAndOrgi(user.getOrgan(), super.getOrgiByTenantshare(request)) ;
		    		if(organ!=null && organ.isSkill()){
		    			agentStatus.setSkill(organ.getId());
		    			agentStatus.setSkillname(organ.getName());
		    		}
		    	}
		    	
		    	SessionConfig sessionConfig = ServiceQuene.initSessionConfig(super.getOrgi(request)) ;
		    	
		    	agentStatus.setUsers(agentUserRepository.countByAgentnoAndStatusAndOrgi(user.getId(), UKDataContext.AgentUserStatusEnum.INSERVICE.toString(), super.getOrgi(request)));
		    	
		    	agentStatus.setUpdatetime(new Date());
		    	
		    	agentStatus.setOrgi(super.getOrgi(request));
		    	agentStatus.setMaxusers(sessionConfig.getMaxuser());
		    	agentStatusRepository.save(agentStatus) ;
		    	
	    	}
	    	if(agentStatus!=null){
		    	/**
		    	 * 更新当前用户状态
		    	 */
		    	agentStatus.setUsers(ServiceQuene.getAgentUsers(agentStatus.getAgentno(), super.getOrgi(request)));
		    	agentStatus.setStatus(UKDataContext.AgentStatusEnum.READY.toString());
		    	CacheHelper.getAgentStatusCacheBean().put(agentStatus.getAgentno(), agentStatus, super.getOrgi(request));
		    	ServiceQuene.recordAgentStatus(agentStatus.getAgentno(),agentStatus.getUsername(),agentStatus.getAgentno(), agentStatus.getSkill(),"0".equals(super.getUser(request).getUsertype()), agentStatus.getAgentno(), UKDataContext.AgentStatusEnum.OFFLINE.toString(),UKDataContext.AgentStatusEnum.READY.toString(), UKDataContext.AgentWorkType.MEIDIACHAT.toString() , agentStatus.getOrgi() , null);
		    	
		    	ServiceQuene.allotAgent(agentStatus.getAgentno(), super.getOrgi(request));
	    	}
		}else if(!StringUtils.isBlank(status)) {
			if(status.equals(UKDataContext.AgentStatusEnum.NOTREADY.toString())) {
				List<AgentStatus> agentStatusList = agentStatusRepository.findByAgentnoAndOrgi(user.getId() , super.getOrgi(request));
				for(AgentStatus temp : agentStatusList){
					ServiceQuene.recordAgentStatus(temp.getAgentno(),temp.getUsername(),temp.getAgentno(), temp.getSkill(),"0".equals(super.getUser(request).getUsertype()), temp.getAgentno(), temp.isBusy() ? UKDataContext.AgentStatusEnum.BUSY.toString():UKDataContext.AgentStatusEnum.READY.toString(),UKDataContext.AgentStatusEnum.NOTREADY.toString(), UKDataContext.AgentWorkType.MEIDIACHAT.toString() , temp.getOrgi() , temp.getUpdatetime());
					agentStatusRepository.delete(temp);
				}
		    	CacheHelper.getAgentStatusCacheBean().delete(super.getUser(request).getId(),super.getOrgi(request));
			}else if(!StringUtils.isBlank(status) && status.equals(UKDataContext.AgentStatusEnum.BUSY.toString())) {
				List<AgentStatus> agentStatusList = agentStatusRepository.findByAgentnoAndOrgi(user.getId() , super.getOrgi(request));
		    	if(agentStatusList.size() > 0){
		    		agentStatus = agentStatusList.get(0) ;
					agentStatus.setBusy(true);
					ServiceQuene.recordAgentStatus(agentStatus.getAgentno(),agentStatus.getUsername(), agentStatus.getAgentno(),agentStatus.getSkill(), "0".equals(super.getUser(request).getUsertype()),agentStatus.getAgentno(), UKDataContext.AgentStatusEnum.READY.toString(),UKDataContext.AgentStatusEnum.BUSY.toString(), UKDataContext.AgentWorkType.MEIDIACHAT.toString() , agentStatus.getOrgi() , agentStatus.getUpdatetime());
					agentStatus.setUpdatetime(new Date());
					
					agentStatusRepository.save(agentStatus);
					CacheHelper.getAgentStatusCacheBean().put(agentStatus.getAgentno(), agentStatus, super.getOrgi(request));
				}
			}else if(!StringUtils.isBlank(status) && status.equals(UKDataContext.AgentStatusEnum.NOTBUSY.toString())) {
				List<AgentStatus> agentStatusList = agentStatusRepository.findByAgentnoAndOrgi(user.getId() , super.getOrgi(request));
		    	if(agentStatusList.size() > 0){
		    		agentStatus = agentStatusList.get(0) ;
					agentStatus.setBusy(false);
					ServiceQuene.recordAgentStatus(agentStatus.getAgentno(),agentStatus.getUsername(), agentStatus.getAgentno(),agentStatus.getSkill(), "0".equals(super.getUser(request).getUsertype()),agentStatus.getAgentno(), UKDataContext.AgentStatusEnum.BUSY.toString(),UKDataContext.AgentStatusEnum.READY.toString(), UKDataContext.AgentWorkType.MEIDIACHAT.toString() , agentStatus.getOrgi() , agentStatus.getUpdatetime());
					
					agentStatus.setUpdatetime(new Date());
					agentStatusRepository.save(agentStatus);
					CacheHelper.getAgentStatusCacheBean().put(agentStatus.getAgentno(), agentStatus,super.getOrgi(request));
				}
				ServiceQuene.allotAgent(agentStatus.getAgentno(), super.getOrgi(request));
			}
			ServiceQuene.publishMessage(super.getOrgi(request) , "agent" , "api" , super.getUser(request).getId());
		}
        return new ResponseEntity<>(new RestResult(RestResultType.OK , agentStatus), HttpStatus.OK);
    }
}