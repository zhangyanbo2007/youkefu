package com.ukefu.webim.web.handler.admin.callcenter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.AclRepository;
import com.ukefu.webim.service.repository.PbxHostRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Acl;

@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterAclController extends Handler{
	
	@Autowired
	private PbxHostRepository pbxHostRes ;
	
	@Autowired
	private AclRepository aclRes ;
	
	@RequestMapping(value = "/acl")
    @Menu(type = "callcenter" , subtype = "callcenteracl" , access = false , admin = true)
    public ModelAndView acl(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		if(!StringUtils.isBlank(hostid)){
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
			map.addAttribute("aclList" , aclRes.findByHostidAndOrgi(hostid, super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/acl/index"));
    }
	
	@RequestMapping(value = "/acl/add")
    @Menu(type = "callcenter" , subtype = "acl" , access = false , admin = true)
    public ModelAndView acladd(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/acl/add"));
    }
	
	@RequestMapping(value = "/acl/save")
    @Menu(type = "callcenter" , subtype = "acl" , access = false , admin = true)
    public ModelAndView aclsave(ModelMap map , HttpServletRequest request , @Valid Acl acl) {
		if(!StringUtils.isBlank(acl.getName())){
			int count = aclRes.countByNameAndOrgi(acl.getName(), super.getOrgi(request)) ;
			if(count == 0){
				acl.setOrgi(super.getOrgi(request));
				acl.setCreater(super.getUser(request).getId());
				aclRes.save(acl) ;
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/acl.html?hostid="+acl.getHostid()));
    }
	
	@RequestMapping(value = "/acl/edit")
    @Menu(type = "callcenter" , subtype = "acl" , access = false , admin = true)
    public ModelAndView acledit(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		map.addAttribute("acl" , aclRes.findByIdAndOrgi(id, super.getOrgi(request)));
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/acl/edit"));
    }
	
	@RequestMapping(value = "/acl/update")
    @Menu(type = "callcenter" , subtype = "acl" , access = false , admin = true)
    public ModelAndView pbxhostupdate(ModelMap map , HttpServletRequest request , @Valid Acl acl) {
		if(!StringUtils.isBlank(acl.getId())){
			Acl oldAcl = aclRes.findByIdAndOrgi(acl.getId(), super.getOrgi(request)) ;
			if(oldAcl!=null){
				oldAcl.setName(acl.getName());
				oldAcl.setDefaultvalue(acl.getDefaultvalue());
				oldAcl.setStrategy(acl.getStrategy());
				aclRes.save(oldAcl);
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/acl.html?hostid="+acl.getHostid()));
    }
	
	@RequestMapping(value = "/acl/delete")
    @Menu(type = "callcenter" , subtype = "acl" , access = false , admin = true)
    public ModelAndView acldelete(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		if(!StringUtils.isBlank(id)){
			aclRes.delete(id);
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/acl.html?hostid="+hostid));
    }
}
