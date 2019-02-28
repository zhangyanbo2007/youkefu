package com.ukefu.webim.web.handler.apps.setting;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.CallOutRoleRepository;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.RoleRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.CallOutRole;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.UKeFuDic;

@Controller
@RequestMapping("/setting")
public class SettingRoleController extends Handler{
	
	@Autowired
	private OrganRepository organRes ;	//查询部门
	
	@Autowired
	private RoleRepository roleRes ;	//查询角色
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CallOutRoleRepository callOutRoleRes;//角色授权部门
	
	/**
	 * 角色授权首页
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping({"/role/index"})
	@Menu(type="apps", subtype="role")
	public ModelAndView index(ModelMap map , HttpServletRequest request){
		
		map.addAttribute("userList", userRepository.findByDatastatusAndOrgiAndOrgid(false, super.getOrgiByTenantshare(request),super.getOrgid(request), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.ASC, "createtime")));
		map.addAttribute("organList",organRes.findByOrgi(super.getOrgi(request)));
		map.addAttribute("roleList",roleRes.findByOrgi(super.getOrgi(request)));
		map.addAttribute("calloutroleList",callOutRoleRes.findByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, "createtime")));
		map.addAttribute("bustypeList",UKeFuDic.getInstance().getDic("com.dic.callout.type"));
		map.addAttribute("statusList",UKeFuDic.getInstance().getDic("com.dic.callout.activity"));
		return request(super.createAppsTempletResponse("/apps/setting/role/index"));
	}
	
	/**
	 * 引入角色,跳转引入角色页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping({"/role/add"})
	@Menu(type="apps", subtype="sales")
	public ModelAndView roleadd(ModelMap map , HttpServletRequest request){
		
		map.addAttribute("oragnList",organRes.findByOrgi(super.getOrgi(request)));
		map.addAttribute("roleList",roleRes.findByOrgi(super.getOrgi(request)));
		map.addAttribute("calloutroleList",callOutRoleRes.findByOrgi(super.getOrgi(request)));
		
		return request(super.createRequestPageTempletResponse("/apps/setting/role/addrole"));
	}
	/**
	 * 引入角色，保存
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/role/save")
	@Menu(type="apps", subtype="sales")
	public ModelAndView rolesave(ModelMap map , HttpServletRequest request,@Valid String[] role){
		
		List<CallOutRole> calloutroleList = new ArrayList<CallOutRole>();
		
		CallOutRole roleid = null;
		CallOutRole callOutRole = null;
		
		if(role != null && role.length > 0){
			for(int i = 0, length = role.length; i < length ; i++){
				roleid = callOutRoleRes.findByOrgiAndRoleid(super.getOrgi(request), role[i]);
				if(roleid != null){
					continue;
				}
				
				callOutRole = new CallOutRole();
				callOutRole.setOrgi(super.getOrgi(request));
				callOutRole.setCreater(super.getUser(request).getId());
				callOutRole.setCreatetime(new Date());
				callOutRole.setRoleid(role[i]);
				calloutroleList.add(callOutRole);
			}
		}
		if(calloutroleList.size() > 0){
			callOutRoleRes.save(calloutroleList);
		}
		map.addAttribute("oragnList",organRes.findByOrgi(super.getOrgi(request)));
		
		return request(super.createRequestPageTempletResponse("redirect:/setting/role/index.html"));
	}
	
	/**
	 * 删除角色
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping({"/role/delete"})
	@Menu(type="apps", subtype="sales")
	public ModelAndView delete(ModelMap map , HttpServletRequest request, @Valid String roleid){
		
		CallOutRole callOutRole = callOutRoleRes.findByIdAndOrgi(roleid, super.getOrgi(request));
		
		if(callOutRole != null){
			callOutRoleRes.delete(callOutRole);
		}
		
		return request(super.createRequestPageTempletResponse("redirect:/setting/role/index.html"));
	}
	/**
     * 部门分配- 跳转部门列表
     * @param map
     * @param request
     * @param parent
     * @param area
     * @return
     */
    @RequestMapping("/role/organ")
    @Menu(type = "callout" , subtype = "productcon")
    public ModelAndView callagentorganlist(ModelMap map , HttpServletRequest request , @Valid String organ) {
    	
    	map.addAttribute("organList",organRes.findByOrgiAndParent(super.getOrgi(request), organ));
    	map.addAttribute("currentorgan",organ);
    	
    	List<Organ> organList = organRes.findByOrgiAndParent(super.getOrgi(request), organ);
    	map.addAttribute("organList", organList);
    	
    	return request(super.createRequestPageTempletResponse("/apps/setting/role/organlist")) ; 
    }
	/**
	 * 为角色添加部门，跳转添加页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping({"/role/organ/add"})
	@Menu(type="apps", subtype="sales")
	public ModelAndView addskill(ModelMap map , HttpServletRequest request,@Valid String organ,@Valid String roleid){
		
    	map.put("roleList", callOutRoleRes.findByIdAndOrgi(roleid, super.getOrgi(request)));
    	map.put("roleid", roleid);
    	List<Organ> organList = organRes.findByOrgiAndOrgid(super.getOrgiByTenantshare(request),super.getOrgid(request)) ;
    	map.addAttribute("organList", organList);
    	if(organList.size() > 0){
    		Organ organData = null ;
    		if(!StringUtils.isBlank(organ)){
    			for(Organ data : organList){
    				if(data.getId().equals(organ)){
    					organData = data;
    				}
    			}
    		}else{
    			organData = organList.get(0) ;
    		}
    		map.addAttribute("organData", organData);
    	}
		return request(super.createRequestPageTempletResponse("/apps/setting/role/addorgan"));
	}
	/**
	 * 为角色添加部门，保存
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/role/organ/add/save")
	@Menu(type="apps", subtype="sales")
	public ModelAndView saveskill(ModelMap map , HttpServletRequest request,@Valid String agentno[],@Valid String roleid,@Valid String bustype){
		
		CallOutRole callOutRole = callOutRoleRes.findByIdAndOrgi(roleid, super.getOrgi(request));
		
		if(callOutRole != null){
			StringBuffer strb = new StringBuffer();
			if(agentno != null && agentno.length > 0 ){
				
				for(int i = 0 ,length = agentno.length; i < length ; i++){
					if(callOutRole.getOrganid() == null){
						strb.append(agentno[i]) ;
						strb.append(",") ;
					}else{
						if(callOutRole.getOrganid().indexOf(agentno[i]) < 0){
							if(strb.length() > 0){
								strb.append(",") ;
							}
							strb.append(agentno[i]) ;
						}else{
							callOutRole.setOrganid(removeRole(callOutRole.getOrganid(), agentno[i]));
						}
					}
				}
				if(!StringUtils.isBlank(callOutRole.getOrganid())){
					strb.insert(0, ",").insert(0, callOutRole.getOrganid()) ;
				}
				callOutRole.setOrganid(strb.toString());
			}
			if(bustype != null){
				callOutRole.setBustype(bustype);
			}
			callOutRoleRes.save(callOutRole);
		}
		map.put("roleid", roleid);
		map.addAttribute("oragnList",organRes.findByOrgi(super.getOrgi(request)));
		
		return request(super.createRequestPageTempletResponse("redirect:/setting/role/index.html"));
	}
	/**
	 * 
	 * @param organid
	 * @param id
	 * @return
	 */
	private String removeRole(String organid ,String id){
		StringBuffer strb = new StringBuffer();
		String[] ids = organid.split(",");
		for(String organ : ids){
			if(!organ.equals(id)){
				if(strb.length() > 0){
					strb.append(",") ;
				}
				strb.append(organ) ;
			}
		}
		return strb.toString() ;
	}
}
