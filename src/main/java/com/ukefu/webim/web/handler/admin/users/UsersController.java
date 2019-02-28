package com.ukefu.webim.web.handler.admin.users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.service.repository.UserRoleRepository;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AgentStatus;
import com.ukefu.webim.web.model.User;
import com.ukefu.webim.web.model.UserRole;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UsersController extends Handler{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRes;

    @RequestMapping("/index")
    @Menu(type = "admin" , subtype = "user")
    public ModelAndView index(ModelMap map , HttpServletRequest request) throws FileNotFoundException, IOException {
    	map.addAttribute("userList", userRepository.findByDatastatusAndOrgiAndOrgid(false, super.getOrgiByTenantshare(request),super.getOrgid(request), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.ASC, "createtime")));
    	return request(super.createAdminTempletResponse("/admin/user/index"));
    }
    
    @RequestMapping("/add")
    @Menu(type = "admin" , subtype = "user")
    public ModelAndView add(ModelMap map , HttpServletRequest request) {
        return request(super.createRequestPageTempletResponse("/admin/user/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "admin" , subtype = "user")
    public ModelAndView save(HttpServletRequest request ,@Valid User user) {
    	String msg = "" ;
    	msg = validUser(user);
    	if(!StringUtils.isBlank(msg)){
    		return request(super.createRequestPageTempletResponse("redirect:/admin/user/index.html?msg="+msg));
    	}else{
    		if(request.getParameter("admin")!=null){
    			user.setUsertype("0");
    		}else{
    			user.setUsertype(null);
    		}
    		if(!StringUtils.isBlank(user.getPassword())){
    			user.setPassword(UKTools.md5(user.getPassword()));
    		}
    		
    		user.setOrgi(super.getOrgiByTenantshare(request));
    		if(!StringUtils.isBlank(super.getUser(request).getOrgid())) {
    			user.setOrgid(super.getUser(request).getOrgid());
    		}else {
    			user.setOrgid(UKDataContext.SYSTEM_ORGI);
    		}
    		userRepository.save(user) ;
    		OnlineUserUtils.clean(super.getOrgi(request));
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/user/index.html?msg="+msg));
    }
    
    private String validUser(User user) {
    	String msg = "";
    	User tempUser = userRepository.findByUsernameAndDatastatus(user.getUsername(),false) ;
    	if(tempUser!=null) {
    		msg = "username_exist";
    		return msg;
    	}
    	tempUser = userRepository.findByEmailAndDatastatus(user.getEmail(),false) ;
    	if(tempUser!=null) {
    		msg = "email_exist";
    		return msg;
    	}
    	tempUser = userRepository.findByMobileAndDatastatus(user.getMobile(),false) ;
    	if(tempUser!=null) {
    		msg = "mobile_exist";
    		return msg;
    	}
    	return msg;
    }
    
    @RequestMapping("/edit")
    @Menu(type = "admin" , subtype = "user")
    public ModelAndView edit(ModelMap map ,HttpServletRequest request , @Valid String id) {
    	ModelAndView view = request(super.createRequestPageTempletResponse("/admin/user/edit")) ;
    	view.addObject("userData", userRepository.findByIdAndOrgi(id, super.getOrgiByTenantshare(request))) ;
        return view;
    }
    
    @RequestMapping("/update")
    @Menu(type = "admin" , subtype = "user" , admin = true)
    public ModelAndView update(HttpServletRequest request ,@Valid User user) {
    	User tempUser = userRepository.findById(user.getId()) ;
    	if(tempUser != null){
    		String msg = validUserUpdate(user,tempUser);
    		if(!StringUtils.isBlank(msg)){
    			return request(super.createRequestPageTempletResponse("redirect:/admin/user/index.html?msg="+msg)); 
    		}
    		tempUser.setUname(user.getUname());
    		tempUser.setUsername(user.getUsername());
    		tempUser.setEmail(user.getEmail());
    		tempUser.setMobile(user.getMobile());
    		//切换成非坐席 判断是否坐席 以及 是否有对话
    		if(!user.isAgent()) {
    			AgentStatus agentStatus = (AgentStatus)CacheHelper.getAgentStatusCacheBean().getCacheObject(user.getId(), super.getOrgi(request));
    	    	if(!(agentStatus==null && ServiceQuene.getAgentUsers(user.getId(), super.getOrgi(request))==0)) {
    	    		return request(super.createRequestPageTempletResponse("redirect:/admin/user/index.html?msg=t1")); 
    	    	}
    		}
    		tempUser.setAgent(user.isAgent());
    		
    		tempUser.setOrgi(super.getOrgiByTenantshare(request));
    		
    		if(!StringUtils.isBlank(super.getUser(request).getOrgid())) {
    			tempUser.setOrgid(super.getUser(request).getOrgid());
    		}else {
    			tempUser.setOrgid(UKDataContext.SYSTEM_ORGI);
    		}
    		
    		tempUser.setCallcenter(user.isCallcenter());
    		if(!StringUtils.isBlank(user.getPassword())){
    			tempUser.setPassword(UKTools.md5(user.getPassword()));
    		}
    		if(!tempUser.isSuperuser()) {
	    		if(request.getParameter("admin")!=null){
	    			tempUser.setUsertype("0");
	    		}else{
	    			tempUser.setUsertype(null);
	    		}
    		}
    		if(tempUser.getCreatetime() == null){
    			tempUser.setCreatetime(new Date());
    		}
    		tempUser.setUpdatetime(new Date());
    		userRepository.save(tempUser) ;
    		OnlineUserUtils.clean(super.getOrgi(request));
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/user/index.html"));
    }
    
    private String validUserUpdate(User user,User oldUser) {
    	String msg = "";
    	User tempUser = userRepository.findByUsernameAndDatastatus(user.getUsername(),false) ;
    	if(tempUser!=null&&!user.getUsername().equals(oldUser.getUsername())) {
    		msg = "username_exist";
    		return msg;
    	}
    	tempUser = userRepository.findByEmailAndDatastatus(user.getEmail(),false) ;
    	if(tempUser!=null&&!user.getEmail().equals(oldUser.getEmail())) {
    		msg = "email_exist";
    		return msg;
    	}
    	tempUser = userRepository.findByMobileAndDatastatus(user.getMobile(),false) ;
    	if(tempUser!=null&&!user.getMobile().equals(oldUser.getMobile())) {
    		msg = "mobile_exist";
    		return msg;
    	}
    	return msg;
    }
    
    @RequestMapping("/delete")
    @Menu(type = "admin" , subtype = "user")
    public ModelAndView delete(HttpServletRequest request ,@Valid User user) {
    	String msg = "admin_user_delete" ;
    	if(user!=null){
	    	List<UserRole> userRole = userRoleRes.findByOrgiAndUser(super.getOrgiByTenantshare(request), user) ;
	    	userRoleRes.delete(userRole);	//删除用户的时候，同时删除用户对应的
	    	user = userRepository.findById(user.getId()) ;
	    	user.setDatastatus(true);
	    	userRepository.save(user) ;
	    	OnlineUserUtils.clean(super.getOrgi(request));
    	}else{
    		msg = "admin_user_not_exist" ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/user/index.html?msg="+msg));
    }
    
}