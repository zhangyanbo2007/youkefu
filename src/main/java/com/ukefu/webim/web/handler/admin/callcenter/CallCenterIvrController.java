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
import com.ukefu.webim.service.repository.ExtentionRepository;
import com.ukefu.webim.service.repository.IvrMenuRepository;
import com.ukefu.webim.service.repository.PbxHostRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Extention;

@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterIvrController extends Handler{
	
	@Autowired
	private PbxHostRepository pbxHostRes ;
	
	@Autowired
	private ExtentionRepository extentionRes ;
	
	@Autowired
	private IvrMenuRepository ivrMenuRes;
	
	@RequestMapping(value = "/ivr")
    @Menu(type = "callcenter" , subtype = "callcenterivr" , access = false , admin = true)
    public ModelAndView ivr(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		if(!StringUtils.isBlank(hostid)){
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
			map.addAttribute("ivrList" , extentionRes.findByExtypeAndOrgi("ivr", super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/ivr/index"));
    }
	
	@RequestMapping(value = "/ivr/edit")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView extentionedit(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		map.addAttribute("extention" , extentionRes.findByIdAndOrgi(id, super.getOrgi(request)));
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/ivr/edit"));
    }
	
	@RequestMapping(value = "/ivr/update")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView extentionupdate(ModelMap map , HttpServletRequest request , @Valid Extention extention) {
		if(!StringUtils.isBlank(extention.getId())){
			Extention ext = extentionRes.findByIdAndOrgi(extention.getId(), super.getOrgi(request)) ;
			ext.setExtention(extention.getExtention());
			ext.setDescription(extention.getDescription());
			extentionRes.save(ext) ;
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/ivr.html?hostid="+extention.getHostid()));
    }
	

	@RequestMapping(value = "/ivr/delete")
    @Menu(type = "callcenter" , subtype = "ivr" , access = false , admin = true)
    public ModelAndView extentiondelete(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		if(!StringUtils.isBlank(id)){
			extentionRes.delete(id);
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/ivr.html?hostid="+hostid));
    }
	
	@RequestMapping(value = "/ivr/design")
    @Menu(type = "callcenter" , subtype = "callcenterivr" , access = false , admin = true)
    public ModelAndView design(ModelMap map , HttpServletRequest request , @Valid String hostid , @Valid String id) {
		if(!StringUtils.isBlank(hostid)){
			map.addAttribute("extention" , extentionRes.findByIdAndOrgi(id, super.getOrgi(request)));
			map.addAttribute("ivrMenuList" , ivrMenuRes.findByExtentionidAndHostidAndOrgi(id, hostid, super.getOrgi(request)));
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/ivr/design"));
    }
	
	@RequestMapping(value = "/ivr/menu/add")
    @Menu(type = "callcenter" , subtype = "callcenterivr" , access = false , admin = true)
    public ModelAndView ivrmenuadd(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid , @Valid String parentid) {
		map.addAttribute("extention" , extentionRes.findByIdAndOrgi(id, super.getOrgi(request)));
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/ivr/menuadd"));
    }
}
