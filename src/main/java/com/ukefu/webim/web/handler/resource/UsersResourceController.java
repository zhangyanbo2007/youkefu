package com.ukefu.webim.web.handler.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.OrgiSkillRelRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.OrgiSkillRel;
import com.ukefu.webim.web.model.User;

@Controller
@RequestMapping("/res")
public class UsersResourceController extends Handler {
	@Autowired
	private UserRepository userRes ;
	
	@Autowired
	private OrgiSkillRelRepository orgiSkillRelService;
	
	@Autowired
	private OrganRepository organRes ;
	
	@RequestMapping("/users")
    @Menu(type = "res" , subtype = "users")
    public ModelAndView add(ModelMap map , HttpServletRequest request , @Valid String q , @Valid String id) {
		if(q==null){
			q = "" ;
		}
    	map.addAttribute("usersList",getUsers(request, q)) ;
        return request(super.createRequestPageTempletResponse("/public/users"));
    }
	
	@RequestMapping("/bpm/users")
    @Menu(type = "res" , subtype = "users")
    public ModelAndView bpmusers(ModelMap map , HttpServletRequest request , @Valid String q , @Valid String id) {
		if(q==null){
			q = "" ;
		}
		map.addAttribute("usersList", getUsers(request, q)) ;
        return request(super.createRequestPageTempletResponse("/public/bpmusers"));
    }
	
	@RequestMapping("/bpm/organ")
    @Menu(type = "res" , subtype = "users")
    public ModelAndView organ(ModelMap map , HttpServletRequest request , @Valid String q , @Valid String ids) {
    	map.addAttribute("organList", getOrgans(request)) ;
    	map.addAttribute("usersList", getUsers(request)) ;
    	map.addAttribute("ids", ids) ;
        return request(super.createRequestPageTempletResponse("/public/organ"));
    }
	private List<User> getUsers(HttpServletRequest request){
		List<User> list = null;
    	if(super.isTenantshare()) {
			List<String> organIdList = new ArrayList<>();
			List<OrgiSkillRel> orgiSkillRelList = orgiSkillRelService.findByOrgi(super.getOrgi(request)) ;
			if(!orgiSkillRelList.isEmpty()) {
				for(OrgiSkillRel rel:orgiSkillRelList) {
					organIdList.add(rel.getSkillid());
				}
			}
			list = userRes.findByOrganInAndDatastatus(organIdList,false);
		}else {
			list = userRes.findByOrgiAndDatastatus(super.getOrgi(request),false) ;
		}
    	return list;
    }
	/**
	 * 获取当前产品下人员信息
	 * @param request
	 * @param q
	 * @return
	 */
	private Page<User> getUsers(HttpServletRequest request,String q){
		if(q==null){
			q = "" ;
		}
		Page<User> list = null;
    	if(super.isTenantshare()) {
			List<String> organIdList = new ArrayList<>();
			List<OrgiSkillRel> orgiSkillRelList = orgiSkillRelService.findByOrgi(super.getOrgi(request)) ;
			if(!orgiSkillRelList.isEmpty()) {
				for(OrgiSkillRel rel:orgiSkillRelList) {
					organIdList.add(rel.getSkillid());
				}
			}
			list = userRes.findByOrganInAndDatastatusAndUsernameLike(organIdList,false, "%"+q+"%", new PageRequest(0, 10) );
		}else {
			list = userRes.findByDatastatusAndOrgiAndOrgidAndUsernameLike(false,super.getOrgi(request),super.getOrgid(request), "%"+q+"%" , new PageRequest(0, 10)) ;
		}
    	return list;
    }
	/**
	 * 获取当前产品下 技能组 组织信息
	 * @param request
	 * @return
	 */
	private List<Organ> getOrgans(HttpServletRequest request){
    	List<Organ> list = null;
    	if(super.isTenantshare()) {
			List<String> organIdList = new ArrayList<>();
			List<OrgiSkillRel> orgiSkillRelList = orgiSkillRelService.findByOrgi(super.getOrgi(request)) ;
			if(!orgiSkillRelList.isEmpty()) {
				for(OrgiSkillRel rel:orgiSkillRelList) {
					organIdList.add(rel.getSkillid());
				}
			}
			list = organRes.findByIdInAndSkill(organIdList,true);
		}else {
			list = organRes.findByOrgiAndSkillAndOrgid(super.getOrgi(request),true,super.getOrgid(request)) ;
		}
    	return list;
    }
}
