package com.ukefu.webim.web.handler.apps.organization;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.OrganizationRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Organization;
import com.ukefu.webim.web.model.SystemConfig;
import com.ukefu.webim.web.model.User;

@Controller
@RequestMapping("/apps/organization")
public class OrganizationController extends Handler{

	@Autowired
	private OrganizationRepository organizationRes;
	

	@Autowired
	private UserRepository userRes;
	
	
    @RequestMapping("/add")
    @Menu(type = "apps" , subtype = "organization")
    public ModelAndView add(ModelMap map , HttpServletRequest request) {
        return request(super.createRequestPageTempletResponse("/apps/organization/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "apps" , subtype = "organization")
    public ModelAndView save(HttpServletRequest request ,@Valid Organization organization) throws NoSuchAlgorithmException, IOException {
    	if(StringUtils.isBlank(organization.getName())|| organization.getName().length()>100) {
    		return request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index?msg=max_illegal"));	
    	}
    	organizationRes.save(organization) ;
    	User user = super.getUser(request);
    	if(user!=null) {
    		User userTemp = userRes.getOne(user.getId());
    		if(userTemp!=null&&StringUtils.isBlank(user.getOrgid())) {
    			userTemp.setOrgid(organization.getId());
    			userTemp.setOrgi(organization.getId());
    			userRes.save(userTemp);
    			super.setUser(request, userTemp);
    		}
    	}
    	ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/"));
    	//登录成功 判断是否进入多租户页面
    	SystemConfig systemConfig = UKTools.getSystemConfig();
    	if(systemConfig!=null&&systemConfig.isEnabletneant()&&systemConfig.isTenantconsole()) {
    		view = request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index"));
    	}
    	return view;
    }
    
    @RequestMapping("/edit")
    @Menu(type = "apps" , subtype = "organization")
    public ModelAndView edit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("organization", organizationRes.findById(id)) ;
        return request(super.createRequestPageTempletResponse("/apps/organization/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "apps" , subtype = "organizationRes" , admin = true)
    public ModelAndView update(HttpServletRequest request ,@Valid Organization organization) throws NoSuchAlgorithmException, IOException {
    	if(StringUtils.isBlank(organization.getName())|| organization.getName().length()>100) {
    		return request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index?msg=max_illegal"));	
    	}
    	Organization temp = organizationRes.findById(organization.getId()) ;
    	if(organization!=null) {
    		organization.setCreatetime(temp.getCreatetime());
    		organizationRes.save(organization) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index"));
    }
    
}