package com.ukefu.webim.web.handler.apps.setting;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.SessionTypeRepository;
import com.ukefu.webim.service.repository.SysDicRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.SessionType;

@Controller
@RequestMapping("/setting/sessiontype")
public class SessionTypeController extends Handler{
	
	
	@Autowired
	private SysDicRepository sysDicRes;
	
	@Autowired
	private SessionTypeRepository sessionTypeRes;

    @RequestMapping("/index")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView index(ModelMap map , HttpServletRequest request, @Valid String typeCode) {
    	
    	Page<SessionType> sessionTypeList = sessionTypeRes.findByOrgiAndParentidAndCtype(super.getOrgi(request), "0", typeCode, new PageRequest(super.getP(request), super.getPs(request) , Direction.DESC , "createtime"));
    	map.addAttribute("sessionTypeList", sessionTypeList);
    	map.addAttribute("typeCode", typeCode);
    	map.addAttribute("sysDicList", sysDicRes.findByParentid( "0" , new PageRequest(super.getP(request), super.getPs(request) , Direction.DESC , "createtime")));
        return request(super.createAppsTempletResponse("/apps/setting/sessiontype/index"));
    }
    
    @RequestMapping("/add")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView add(ModelMap map , HttpServletRequest request, @Valid String typeCode) {
    	map.addAttribute("typeCode", typeCode);
        return request(super.createRequestPageTempletResponse("/apps/setting/sessiontype/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView save(ModelMap map ,HttpServletRequest request ,@Valid SessionType ses, @Valid String typeCode) {
    	List<SessionType> sesList = sessionTypeRes.findByCodeOrName(ses.getCode() , ses.getName()) ;
    	String msg = null ;
    	if(sesList.size() == 0){
    		ses.setCtype(typeCode);
    		ses.setParentid("0");
    		ses.setCreater(super.getUser(request).getId());
    		ses.setCreatetime(new Date());
    		ses.setOrgi(super.getOrgi(request));
    		sessionTypeRes.save(ses) ;
    	}else{
    		msg = "exist" ;
    	}
    	map.addAttribute("typeCode", typeCode);
    	return request(super.createRequestPageTempletResponse("redirect:/setting/sessiontype/index.html?typeCode="+typeCode+(msg!=null ? "&msg="+msg : "")));
    }
    
    @RequestMapping("/edit")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView edit(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String p, @Valid String typeCode) {
    	map.addAttribute("sessiontype", sessionTypeRes.findById(id)) ;
    	map.addAttribute("p", p) ;
    	map.addAttribute("typeCode", typeCode);
        return request(super.createRequestPageTempletResponse("/apps/setting/sessiontype/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView update(ModelMap map , HttpServletRequest request ,@Valid SessionType ses, @Valid String p, @Valid String typeCode) {
    	List<SessionType> sesList = sessionTypeRes.findByCodeOrName(ses.getCode() , ses.getName()) ;
    	if(sesList.size() == 0 || (sesList.size() ==1 && sesList.get(0).getId().equals(ses.getId()))){
    		SessionType session = sessionTypeRes.findById(ses.getId()) ;
    		session.setName(ses.getName());
    		session.setCode(ses.getCode());
    		session.setCtype(typeCode);
    		session.setDescription(ses.getDescription());
    		sessionTypeRes.save(session) ;
    		
    	}
    	map.addAttribute("typeCode", typeCode);
    	return request(super.createRequestPageTempletResponse("redirect:/setting/sessiontype/index.html?p="+p+"&typeCode="+typeCode));
    }
    
    @RequestMapping("/delete")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView delete(ModelMap map , HttpServletRequest request , @Valid String id, @Valid String p, @Valid String typeCode) {
    	SessionType sessionType = sessionTypeRes.findById(id);
    	List<SessionType> sesList = sessionTypeRes.findByOrgiAndDicid(super.getOrgi(request), id);
    	if(sesList.size() > 0){
    		sessionTypeRes.delete(sesList);
    	}
    	if(sessionTypeRes != null){
    		sessionTypeRes.delete(sessionType);
    	}
    	
    	map.addAttribute("typeCode", typeCode);
        return request(super.createRequestPageTempletResponse("redirect:/setting/sessiontype/index.html?p="+p+"&typeCode="+typeCode));
    }
    
    @RequestMapping("/sessionitem")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitem(ModelMap map , HttpServletRequest request , @Valid String id, @Valid String typeCode) {
    	map.addAttribute("sessiontype", sessionTypeRes.findById(id)) ;
    	//map.addAttribute("sysDicList", sysDicRes.findByParentid( id , new PageRequest(super.getP(request), super.getPs(request) , Direction.DESC , "createtime")));
    	map.addAttribute("sessionTypeList", sessionTypeRes.findByOrgiAndParentid( super.getOrgi(request),id , new PageRequest(super.getP(request), super.getPs(request) , Direction.DESC , "createtime")));
    	map.addAttribute("typeCode", typeCode);
        return request(super.createAppsTempletResponse("/apps/setting/sessiontype/sessionitem"));
    }
    
    @RequestMapping("/sessionitem/add")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitemadd(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String p, @Valid String typeCode) {
    	map.addAttribute("sessiontype", sessionTypeRes.findById(id)) ;
    	map.addAttribute("p", p) ;
    	map.addAttribute("typeCode", typeCode);
        return request(super.createRequestPageTempletResponse("/apps/setting/sessiontype/sessionitemadd"));
    }
    
    @RequestMapping("/sessionitem/save")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitemsave(ModelMap map ,HttpServletRequest request ,@Valid SessionType ses , @Valid String p, @Valid String typeCode) {
    	List<SessionType> sesList = sessionTypeRes.findByDicidAndName(ses.getDicid() ,  ses.getName()) ;
    	String msg = null ;
    	if(sesList.size() == 0){
    		ses.setOrgi(super.getOrgi(request));
    		ses.setCreater(super.getUser(request).getId());
    		ses.setCreatetime(new Date());
    		ses.setOrgi(super.getOrgi(request));
    		ses.setCtype(typeCode);
    		sessionTypeRes.save(ses) ;
    	}else{
    		msg = "exist" ;
    	}
    	map.addAttribute("typeCode", typeCode);
    	return request(super.createRequestPageTempletResponse("redirect:/setting/sessiontype/sessionitem.html?id="+ses.getParentid()+"&typeCode="+typeCode+(msg!=null ? "&p="+p+"&msg="+msg : "")));
    }
    
    @RequestMapping("/sessionitem/batadd")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitembatadd(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String p, @Valid String typeCode) {
    	map.addAttribute("sessiontype", sessionTypeRes.findById(id)) ;
    	map.addAttribute("p", p) ;
    	map.addAttribute("typeCode", typeCode);
        return request(super.createRequestPageTempletResponse("/apps/setting/sessiontype/batadd"));
    }
    
    @RequestMapping("/sessionitem/batsave")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitembatsave(ModelMap map , HttpServletRequest request ,@Valid SessionType ses , @Valid String content , @Valid String p, @Valid String typeCode) {
    	String[] dicitems = content.split("[\n\r\n]") ;
		for(String dicitem : dicitems){
			String[] dicValues = dicitem.split("[\t, ;]{1,}") ;
			if(dicValues.length == 2 && dicValues[0].length()>0 && dicValues[1].length() >0){
				SessionType session = new SessionType() ;
				session.setOrgi(super.getOrgi(request));
				session.setName(dicValues[0]);
				session.setCode(dicValues[1]);
				session.setCreater(super.getUser(request).getId());
				session.setParentid(ses.getParentid());
				session.setDicid(ses.getDicid());
				session.setCreatetime(new Date());
				session.setUpdatetime(new Date());
				session.setCtype(typeCode);
				if(sessionTypeRes.findByDicidAndName(ses.getDicid(), ses.getName()).size() == 0){
					sessionTypeRes.save(session) ;
				}
			}
		}
		map.addAttribute("typeCode", typeCode);
    	return request(super.createRequestPageTempletResponse("redirect:/setting/sessiontype/sessionitem.html?id="+ses.getParentid()+"&p="+p+"&typeCode="+typeCode));
    }
    
    @RequestMapping("/sessionitem/edit")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitemedit(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String p, @Valid String typeCode) {
    	map.addAttribute("sessiontype", sessionTypeRes.findById(id)) ;
    	map.addAttribute("p", p) ;
    	map.addAttribute("typeCode", typeCode);
        return request(super.createRequestPageTempletResponse("/apps/setting/sessiontype/sessionitemedit"));
    }
    
    @RequestMapping("/sessionitem/update")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitemupdate(ModelMap map , HttpServletRequest request ,@Valid SessionType ses, @Valid String p, @Valid String typeCode) {
    	List<SessionType> sesList = sessionTypeRes.findByCodeOrName(ses.getCode() , ses.getName()) ;
    	if(sesList.size() == 0 || (sesList.size() ==1 && sesList.get(0).getId().equals(ses.getId()))){
    		SessionType session = sessionTypeRes.findById(ses.getId()) ;
    		session.setName(ses.getName());
    		session.setCode(ses.getCode());
    		session.setCtype(ses.getCtype());
    		session.setDescription(ses.getDescription());
    		sessionTypeRes.save(session) ;
    		
    	}
    	map.addAttribute("typeCode", typeCode);
    	return request(super.createRequestPageTempletResponse("redirect:/setting/sessiontype/sessionitem.html?id="+ses.getParentid()+"&p="+p+"&typeCode="+typeCode));
    }
    
    @RequestMapping("/sessionitem/delete")
    @Menu(type = "apps" , subtype = "sessiontype")
    public ModelAndView dicitemdelete(ModelMap map , HttpServletRequest request , @Valid String id, @Valid String p, @Valid String typeCode) {
    	
    	List<SessionType> sesList = sessionTypeRes.findByOrgiAndDicid(super.getOrgi(request), id);
    	List<SessionType> sesParList = sessionTypeRes.findByOrgiAndParentid(super.getOrgi(request), id);
    	
    	if(sesList.size() > 0){
    		sessionTypeRes.delete(sesList);
    	}
    	if(sesParList.size() > 0){
    		sessionTypeRes.delete(sesParList);
    	}
    	SessionType sessionType = sessionTypeRes.findById(id);
    	if(sessionType != null){
    		sessionTypeRes.delete(sessionType);
    	}
        return request(super.createRequestPageTempletResponse("redirect:/setting/sessiontype/sessionitem.html?id="+sessionType.getParentid()+"&p="+p+"&typeCode="+typeCode));
    }
    
}