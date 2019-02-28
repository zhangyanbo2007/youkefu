package com.ukefu.webim.web.handler.apps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.es.ContactsRepository;
import com.ukefu.webim.service.repository.InviteRecordRepository;
import com.ukefu.webim.service.repository.OnlineUserRepository;
import com.ukefu.webim.service.repository.OrgiSkillRelRepository;
import com.ukefu.webim.service.repository.UserEventRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AgentStatus;
import com.ukefu.webim.web.model.Contacts;
import com.ukefu.webim.web.model.InviteRecord;
import com.ukefu.webim.web.model.OnlineUser;
import com.ukefu.webim.web.model.OrgiSkillRel;
import com.ukefu.webim.web.model.User;

@Controller
public class AppsController extends Handler{
	
	@Autowired
	private UserRepository userRes;
	
	@Autowired
	private OnlineUserRepository onlineUserRes;
	
	@Autowired
	private UserEventRepository userEventRes ;
	
	@Autowired
	private InviteRecordRepository inviteRecordRes ;
	
	@Autowired
	private ContactsRepository contactsRes ;
	
	@Autowired
	private OrgiSkillRelRepository orgiSkillRelService;
	
	@RequestMapping({"/apps/content"})
	@Menu(type="apps", subtype="content")
	public ModelAndView content(ModelMap map , HttpServletRequest request,@Valid String msg){
		
		Page<OnlineUser> onlineUserList = this.onlineUserRes.findByOrgiAndStatus(super.getOrgi(request), UKDataContext.OnlineUserOperatorStatus.ONLINE.toString(), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" })) ;
		List<String> ids = new ArrayList<String>();
		for(OnlineUser onlineUser : onlineUserList.getContent()){
			onlineUser.setBetweentime((int) (System.currentTimeMillis() - onlineUser.getLogintime().getTime()));
			if(!StringUtils.isBlank(onlineUser.getContactsid())){
				ids.add(onlineUser.getContactsid()) ;
			}
		}
		if(ids.size() > 0){
			Iterable<Contacts> contactsList = contactsRes.findAll(ids) ;
			for(OnlineUser onlineUser : onlineUserList.getContent()){
				if(!StringUtils.isBlank(onlineUser.getContactsid())){
					for(Contacts contacts : contactsList){
						if(onlineUser.getContactsid().equals(contacts.getId())){
							onlineUser.setContacts(contacts);
						}
					}
				}
			}
		}
		map.put("onlineUserList", onlineUserList);
		map.put("msg", msg);
		aggValues(map, request);
		
		return request(super.createAppsTempletResponse("/apps/desktop/index"));
	}
	
	private void aggValues(ModelMap map , HttpServletRequest request){
		map.put("agentReport", ServiceQuene.getAgentReport(super.getOrgi(request))) ;
		map.put("webIMReport", UKTools.getWebIMReport(userEventRes.findByOrgiAndCreatetimeRange(super.getOrgi(request), UKTools.getStartTime() , UKTools.getEndTime()))) ;
		
		map.put("agents",getUsers(request).size()) ;

		map.put("webIMInvite", UKTools.getWebIMInviteStatus(onlineUserRes.findByOrgiAndStatus(super.getOrgi(request), UKDataContext.OnlineUserOperatorStatus.ONLINE.toString()))) ;
		
		map.put("inviteResult", UKTools.getWebIMInviteResult(onlineUserRes.findByOrgiAndAgentnoAndCreatetimeRange(super.getOrgi(request), super.getUser(request).getId() , UKTools.getStartTime() , UKTools.getEndTime()))) ;
		
		map.put("agentUserCount", onlineUserRes.countByAgentForAgentUser(super.getOrgi(request), UKDataContext.AgentUserStatusEnum.INSERVICE.toString(),super.getUser(request).getId() , UKTools.getStartTime() , UKTools.getEndTime())) ;
		
		map.put("agentServicesCount", onlineUserRes.countByAgentForAgentUser(super.getOrgi(request), UKDataContext.AgentUserStatusEnum.END.toString(),super.getUser(request).getId() , UKTools.getStartTime() , UKTools.getEndTime())) ;
		
		map.put("agentServicesAvg", onlineUserRes.countByAgentForAvagTime(super.getOrgi(request), UKDataContext.AgentUserStatusEnum.END.toString(),super.getUser(request).getId() , UKTools.getStartTime() , UKTools.getEndTime())) ;
		
	}
	@RequestMapping({"/apps/onlineuser"})
	@Menu(type="apps", subtype="onlineuser")
	public ModelAndView onlineuser(ModelMap map , HttpServletRequest request){
		Page<OnlineUser> onlineUserList = this.onlineUserRes.findByOrgiAndStatus(super.getOrgi(request), UKDataContext.OnlineUserOperatorStatus.ONLINE.toString(), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" })) ;
		List<String> ids = new ArrayList<String>();
		for(OnlineUser onlineUser : onlineUserList.getContent()){
			onlineUser.setBetweentime((int) (System.currentTimeMillis() - onlineUser.getLogintime().getTime()));
			if(!StringUtils.isBlank(onlineUser.getContactsid())){
				ids.add(onlineUser.getContactsid()) ;
			}
		}
		if(ids.size() > 0){
			Iterable<Contacts> contactsList = contactsRes.findAll(ids) ;
			for(OnlineUser onlineUser : onlineUserList.getContent()){
				if(!StringUtils.isBlank(onlineUser.getContactsid())){
					for(Contacts contacts : contactsList){
						if(onlineUser.getContactsid().equals(contacts.getId())){
							onlineUser.setContacts(contacts);
						}
					}
				}
			}
		}
		map.put("onlineUserList", onlineUserList);
		aggValues(map, request);
		
		return request(super.createAppsTempletResponse("/apps/desktop/onlineuser"));
	}
	
	@RequestMapping({"/apps/invite"})
	@Menu(type="apps", subtype="invite")
	public ModelAndView invite(ModelMap map , HttpServletRequest request , @Valid String id) throws Exception{
		OnlineUser onlineUser = onlineUserRes.findOne(id) ;
		if(onlineUser!=null){
			onlineUser.setInvitestatus(UKDataContext.OnlineUserInviteStatus.INVITE.toString());
			onlineUser.setInvitetimes(onlineUser.getInvitetimes()+1);
			onlineUserRes.save(onlineUser) ;
			OnlineUserUtils.sendWebIMClients(onlineUser.getSessionid() , onlineUser.getUserid() , "invite");
			InviteRecord record = new InviteRecord() ;
			record.setAgentno(super.getUser(request).getId());
			record.setUserid(onlineUser.getUserid());
			record.setAppid(onlineUser.getAppid());
			record.setOrgi(super.getOrgi(request));
			inviteRecordRes.save(record) ;
		}
		
		return request(super.createRequestPageTempletResponse("redirect:/apps/content.html"));
	}
	
	@RequestMapping({"/apps/profile"})
	@Menu(type="apps", subtype="content")
	public ModelAndView profile(ModelMap map , HttpServletRequest request,@Valid String index){
		map.addAttribute("userData",super.getUser(request)) ;
		map.addAttribute("index",index) ;
		return request(super.createRequestPageTempletResponse("/apps/desktop/profile"));
	}
	
	@RequestMapping("/apps/desk")
	@Menu(type="apps", subtype="desk" , admin=true)
    public ModelAndView desk(HttpServletRequest request) {
        return request(super.createAppsTempletResponse("/apps/desktop/desk"));
    }
	
	@RequestMapping("/apps/desk/disabledesk")
	@Menu(type="apps", subtype="desk" , admin=true)
    public ModelAndView disabledesk(HttpServletRequest request) {
		User user = super.getUser(request) ;
		if(user!=null) {
			user.setDisabledesk(true);
			super.setUser(request, user);
			userRes.save(user) ;
		}
		return request(super.createRequestPageTempletResponse("/apps/desktop/empty"));
    }
	
	@RequestMapping("/apps/desk/enabledesk")
	@Menu(type="apps", subtype="desk" , admin=true)
    public ModelAndView enabledesk(HttpServletRequest request) {
		User user = super.getUser(request) ;
		if(user!=null) {
			user.setDisabledesk(false);
			super.setUser(request, user);
			userRes.save(user) ;
		}
		return request(super.createRequestPageTempletResponse("/apps/desktop/empty"));
    }
	
	@RequestMapping({"/apps/profile/save"})
	@Menu(type="apps", subtype="content")
	public ModelAndView profile(ModelMap map , HttpServletRequest request , @Valid User user,@Valid String index){
		User tempUser = userRes.getOne(user.getId()) ;
    	if(tempUser != null){
    		String msg = validUserUpdate(user,tempUser);
    		if(!StringUtils.isBlank(msg)){
    			if(StringUtils.isBlank(index)) {
    				return request(super.createRequestPageTempletResponse("redirect:/apps/content.html?msg="+msg));
    			}
    			return request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index.html?msg="+msg)); 
    		}
    		tempUser.setUname(user.getUname());
    		tempUser.setEmail(user.getEmail());
    		tempUser.setMobile(user.getMobile());
    		//切换成非坐席 判断是否坐席 以及 是否有对话
    		if(!user.isAgent()) {
    			AgentStatus agentStatus = (AgentStatus)CacheHelper.getAgentStatusCacheBean().getCacheObject((super.getUser(request)).getId(), super.getOrgi(request));
    	    	if(!(agentStatus==null && ServiceQuene.getAgentUsers(super.getUser(request).getId(), super.getOrgi(request))==0)) {
    	    		if(StringUtils.isBlank(index)) {
        				return request(super.createRequestPageTempletResponse("redirect:/apps/content.html?msg=t1"));
        			}
        			return request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index.html?msg=t1")); 
    	    	}
    		}
    		tempUser.setAgent(user.isAgent());
    		tempUser.setOrgi(super.getOrgiByTenantshare(request));
    		if(!StringUtils.isBlank(user.getPassword())){
    			tempUser.setPassword(UKTools.md5(user.getPassword()));
    		}
    		if(tempUser.getCreatetime() == null){
    			tempUser.setCreatetime(new Date());
    		}
    		tempUser.setUpdatetime(new Date());
    		userRes.save(tempUser) ;
    		User sessionUser = super.getUser(request) ;
    		tempUser.setRoleList(sessionUser.getRoleList()) ;
    		tempUser.setRoleAuthMap(sessionUser.getRoleAuthMap());
    		User u = tempUser;
    		u.setOrgi(super.getOrgi(request));
    		super.setUser(request, u);
    	}
    	if(StringUtils.isBlank(index)) {
			return request(super.createRequestPageTempletResponse("redirect:/apps/content.html"));
		}
		return request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index.html")); 
	}
	
	private String validUserUpdate(User user,User oldUser) {
    	String msg = "";
    	User tempUser = userRes.findByUsernameAndDatastatus(user.getUsername(),false) ;
    	if(tempUser!=null&&!user.getUsername().equals(oldUser.getUsername())) {
    		msg = "username_exist";
    		return msg;
    	}
    	tempUser = userRes.findByEmailAndDatastatus(user.getEmail(),false) ;
    	if(tempUser!=null&&!user.getEmail().equals(oldUser.getEmail())) {
    		msg = "email_exist";
    		return msg;
    	}
    	tempUser = userRes.findByMobileAndDatastatus(user.getMobile(),false) ;
    	if(tempUser!=null&&!user.getMobile().equals(oldUser.getMobile())) {
    		msg = "mobile_exist";
    		return msg;
    	}
    	return msg;
    }
	/**
	 * 获取当前产品下人员信息
	 * @param request
	 * @param q
	 * @return
	 */
	private List<User> getUsers(HttpServletRequest request){
		List<User> userList = null;
		if(super.isTenantshare()) {
			List<String> organIdList = new ArrayList<>();
			List<OrgiSkillRel> orgiSkillRelList = orgiSkillRelService.findByOrgi(super.getOrgi(request)) ;
			if(!orgiSkillRelList.isEmpty()) {
				for(OrgiSkillRel rel:orgiSkillRelList) {
					organIdList.add(rel.getSkillid());
				}
			}
			userList=userRes.findByOrganInAndAgentAndDatastatus(organIdList,true,false);
		}else {
			userList=userRes.findByOrgiAndAgentAndDatastatus(super.getOrgi(request), true,false) ;
		}
		return userList;
    }
	
}
