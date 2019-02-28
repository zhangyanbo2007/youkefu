package com.ukefu.webim.web.handler.apps.service;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.es.ContactsRepository;
import com.ukefu.webim.service.impl.AgentUserService;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.service.repository.AgentUserContactsRepository;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.service.repository.OnlineUserHisRepository;
import com.ukefu.webim.service.repository.OnlineUserRepository;
import com.ukefu.webim.service.repository.ServiceSummaryRepository;
import com.ukefu.webim.service.repository.SessionTypeRepository;
import com.ukefu.webim.service.repository.SysDicRepository;
import com.ukefu.webim.service.repository.TagRelationRepository;
import com.ukefu.webim.service.repository.TagRepository;
import com.ukefu.webim.service.repository.UserEventRepository;
import com.ukefu.webim.service.repository.WeiXinUserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.AgentServiceSummary;
import com.ukefu.webim.web.model.AgentUser;
import com.ukefu.webim.web.model.AgentUserContacts;
import com.ukefu.webim.web.model.OnlineUser;
import com.ukefu.webim.web.model.SessionType;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.WeiXinUser;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Controller
@RequestMapping("/service")
public class OnlineUserController extends Handler{
	@Autowired
	private AgentServiceRepository agentServiceRes ;
	
	@Autowired
	private AgentUserService agentUserRes ;
	
	@Autowired
	private OnlineUserRepository onlineUserRes; 
	
	@Autowired
	private UserEventRepository userEventRes; 
	
	@Autowired
	private ServiceSummaryRepository serviceSummaryRes; 
	
	
	@Autowired
	private OnlineUserHisRepository onlineUserHisRes;
	
	@Autowired
	private WeiXinUserRepository weiXinUserRes;
	
	@Autowired
	private TagRepository tagRes ;
	
	@Autowired
	private TagRelationRepository tagRelationRes ;
	
	@Autowired
	private ChatMessageRepository chatMessageRepository ;
	
	@Autowired
	private ContactsRepository contactsRes ;
	
	@Autowired
	private AgentUserContactsRepository agentUserContactsRes ;
	
	@Autowired
	private SessionTypeRepository sessionTypeRes ;
	
	@Autowired
	private SysDicRepository sysDicRes ;
	
	@RequestMapping("/online/index")
    @Menu(type = "service" , subtype = "online" , admin= true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , final String userid , String agentservice , @Valid String channel) {
		if(!StringUtils.isBlank(userid)){
			map.put("inviteResult", UKTools.getWebIMInviteResult(onlineUserRes.findByOrgiAndUserid(super.getOrgi(request), userid))) ;
			map.put("tagRelationList", tagRelationRes.findByUserid(userid)) ;
			map.put("onlineUserHistList", onlineUserHisRes.findByUseridAndOrgi(userid, super.getOrgi(request))) ;
			map.put("agentServicesAvg", onlineUserRes.countByUserForAvagTime(super.getOrgi(request), UKDataContext.AgentUserStatusEnum.END.toString(),userid)) ;
			
			//List<AgentService> agentServiceList = agentServiceRes.findByUseridAndOrgi(userid, super.getOrgi(request)) ; 
			final String orgi = super.getOrgi(request);
			Page<AgentService> agentServiceList= agentServiceRes.findAll(new Specification<AgentService>(){
				@Override
				public Predicate toPredicate(Root<AgentService> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();  
					list.add(cb.equal(root.get("orgi").as(String.class), orgi));
					list.add(cb.equal(root.get("userid").as(String.class),userid));
					
					Predicate[] p = new Predicate[list.size()];  
					return cb.and(list.toArray(p));   
				}}, new PageRequest(0, 10000, Sort.Direction.DESC, new String[] { "servicetime" }));
			
			map.put("agentServiceList", agentServiceList) ;
			if(agentServiceList.getContent().size()>0){
				map.put("serviceCount", Integer
						.valueOf(this.agentServiceRes
								.countByUseridAndOrgiAndStatus(userid, super.getOrgi(request),
										UKDataContext.AgentUserStatusEnum.END.toString())));
				
				AgentService agentService = agentServiceList.getContent().get(0) ;
				if(!StringUtils.isBlank(agentservice)){
					for(AgentService as : agentServiceList.getContent()){
						if(as.getId().equals(agentservice)){
							agentService = as ; break ;
						}
					}
				}
				
				if(agentService!=null){
					Page<AgentServiceSummary> summaryList = this.serviceSummaryRes.findByOrgiAndUserid(super.getOrgi(request), userid , new PageRequest(0, super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" }));
					map.addAttribute("summaryList", summaryList) ;
					map.addAttribute("tagsSummary", tagRes.findByOrgiAndTagtype(super.getOrgi(request) , UKDataContext.ModelType.SUMMARY.toString())) ;
				}
				
				List<AgentUserContacts> agentUserContactsList = agentUserContactsRes.findByUseridAndOrgi(userid, super.getOrgi(request)) ;
				if(agentUserContactsList.size() > 0){
					AgentUserContacts agentUserContacts = agentUserContactsList.get(0) ;
					map.put("contacts", contactsRes.findOne(agentUserContacts.getContactsid())) ;
				}
				
				map.put("tags", tagRes.findByOrgiAndTagtype(super.getOrgi(request) , UKDataContext.ModelType.USER.toString())) ;
				map.put("curAgentService", agentService) ;
				
				
				map.put("agentUserMessageList", chatMessageRepository.findByAgentserviceidAndOrgi(agentService.getId() , super.getOrgi(request), new PageRequest(0, 50, Direction.DESC , "updatetime")));
			}
			
			if(UKDataContext.ChannelTypeEnum.WEIXIN.toString().equals(channel)){
				List<WeiXinUser> weiXinUserList = weiXinUserRes.findByOpenidAndOrgi(userid, super.getOrgi(request)) ;
				if(weiXinUserList.size() > 0){
					WeiXinUser weiXinUser = weiXinUserList.get(0) ;
					map.put("weiXinUser",weiXinUser);
				}
			}else if(UKDataContext.ChannelTypeEnum.WEBIM.toString().equals(channel)){
				List<OnlineUser> onlineUserList = onlineUserRes.findByUseridAndOrgi(userid, super.getOrgi(request)) ;
				if(onlineUserList.size() >0){
					map.put("onlineUser", onlineUserList.get(0)) ;
				}
			}
			map.put("agentUser", agentUserRes.findByUseridAndOrgi(userid, super.getOrgi(request))) ;
			map.put("curagentuser", agentUserRes.findByUseridAndOrgi(userid, super.getOrgi(request))) ;
			
			//文字客服
			AgentService agentService = agentServiceRes.findByIdAndOrgi(agentservice, super.getOrgi(request));
			SysDic sysDic = sysDicRes.findByCode("sessionWords");
			if(agentService != null &&sysDic != null){
				List<SessionType> sessionTypeList = sessionTypeRes.findByOrgiAndCtype(super.getOrgi(request), sysDic.getId());
				for(SessionType  ses : sessionTypeList){
					if(!StringUtils.isBlank(agentService.getSessiontype()) && ses.getId().equals(agentService.getSessiontype())){
						map.addAttribute("agentSessionType", ses.getName());
					}
				}
			}
			map.addAttribute("agentService", agentService);
			map.addAttribute("userid", userid);
			map.addAttribute("agentservice", agentservice);
			map.addAttribute("channel", channel);
		}
        return request(super.createAppsTempletResponse("/apps/service/online/index"));
    }
	
	@RequestMapping("/online/chatmsg")
    @Menu(type = "service" , subtype = "chatmsg" , admin= true)
    public ModelAndView onlinechat(ModelMap map , HttpServletRequest request , String id , String title) {
		AgentService agentService = agentServiceRes.getOne(id) ; 
		AgentUser curragentuser = agentUserRes.findByUseridAndOrgi(agentService.getUserid(), super.getOrgi(request)) ;
		
		map.put("curAgentService", agentService) ;
		map.put("curagentuser", curragentuser) ;
		if(!StringUtils.isBlank(title)){
			map.put("title", title) ;
		}
		
		map.addAttribute("tagsSummary", tagRes.findByOrgiAndTagtype(super.getOrgi(request) , UKDataContext.ModelType.SUMMARY.toString())) ;
		if(agentService!=null){
			Page<AgentServiceSummary> summaryList = this.serviceSummaryRes.findByOrgiAndUserid(super.getOrgi(request), agentService.getUserid() , new PageRequest(0, super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" }));
			map.addAttribute("summaryList", summaryList) ;
		}
		
		map.put("agentUserMessageList", chatMessageRepository.findByAgentserviceidAndOrgi(agentService.getId() , super.getOrgi(request), new PageRequest(0, 50, Direction.DESC , "updatetime")));
		
        return request(super.createRequestPageTempletResponse("/apps/service/online/chatmsg"));
    }
	
	@RequestMapping("/trace")
    @Menu(type = "service" , subtype = "trace" , admin= false)
    public ModelAndView trace(ModelMap map , HttpServletRequest request , @Valid String sessionid) {
		if(!StringUtils.isBlank(sessionid)){
			map.addAttribute("traceHisList", userEventRes.findBySessionidAndOrgi(sessionid, super.getOrgi(request), new PageRequest(0, 100))) ;
		}
        return request(super.createRequestPageTempletResponse("/apps/service/online/trace"));
    }
	@RequestMapping("/sessiontype")
	@Menu(type = "service" , subtype = "online" , admin= false)
	public ModelAndView getSessionType(ModelMap map , HttpServletRequest request , @Valid String sesid, String userid , String agentservice , @Valid String channel) {
		//文字客服
		SysDic sysDic = sysDicRes.findByCode("sessionWords");
		if(sysDic != null){
			map.addAttribute("sesTypeList", sessionTypeRes.findByOrgiAndCtype(super.getOrgi(request), sysDic.getId()));
		}
		
		AgentService agentSer = agentServiceRes.findByIdAndOrgi(agentservice, super.getOrgi(request));
		if(agentSer != null) {
			map.addAttribute("sesid", agentSer.getSessiontype());
			map.addAttribute("sesTemp", sessionTypeRes.findById(agentSer.getSessiontype()));
		}
		map.addAttribute("userid", userid);
		map.addAttribute("agentservice", agentservice);
		map.addAttribute("channel", channel);
		return request(super.createRequestPageTempletResponse("/apps/service/online/sessiontype"));
	}
	@RequestMapping("/sessiontype/save")
	@Menu(type = "service" , subtype = "online" , admin= false)
	public ModelAndView saveSessionType(ModelMap map , HttpServletRequest request ,@Valid String sesid, String userid , String agentservice , @Valid String channel) {
		
		AgentService agentService = agentServiceRes.findByIdAndOrgi(agentservice, super.getOrgi(request));
		if(agentService != null){
			agentService.setSessiontype(sesid);
			agentServiceRes.save(agentService);
			SysDic sysDic = sysDicRes.findByCode("sessionWords");
			if(sysDic != null){
				List<SessionType> sessionTypeList = sessionTypeRes.findByOrgiAndCtype(super.getOrgi(request), sysDic.getId());
				for(SessionType  ses : sessionTypeList){
					if(!StringUtils.isBlank(agentService.getSessiontype()) && ses.getId().equals(agentService.getSessiontype())){
						map.addAttribute("agentSessionType", ses.getName());
					}
				}
			}
		}
		map.addAttribute("agentService", agentService);
		map.addAttribute("sesid", sesid);
		return request(super.createRequestPageTempletResponse("/apps/service/online/agenttype"));
	}
	@RequestMapping("/sessionmemo")
	@Menu(type = "service" , subtype = "online" , admin= false)
	public ModelAndView getSessionMemo(ModelMap map , HttpServletRequest request , @Valid String sesid, String userid , String agentservice , @Valid String channel) {
		AgentService agentService = agentServiceRes.findByIdAndOrgi(agentservice, super.getOrgi(request));
		
		map.addAttribute("agentService", agentService);
		map.addAttribute("sesid", sesid);
		map.addAttribute("userid", userid);
		map.addAttribute("agentservice", agentservice);
		map.addAttribute("channel", channel);
		return request(super.createRequestPageTempletResponse("/apps/service/online/sessionmemo"));
	}
	@RequestMapping("/sessionmemo/save")
	@Menu(type = "service" , subtype = "online" , admin= false)
	public ModelAndView saveSessionMemo(ModelMap map , HttpServletRequest request ,@Valid String sesid, String userid , String agentservice , @Valid String channel, @Valid String memo) {
		
		AgentService agentService = agentServiceRes.findByIdAndOrgi(agentservice, super.getOrgi(request));
		if(agentService != null){
			agentService.setMemo(memo);
			agentServiceRes.save(agentService);
		}
		map.addAttribute("agentService", agentService);
		return request(super.createRequestPageTempletResponse("/apps/service/online/agentmemo"));
	}
}
