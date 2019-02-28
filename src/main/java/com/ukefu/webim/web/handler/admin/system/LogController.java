package com.ukefu.webim.web.handler.admin.system;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.LogRepository;
import com.ukefu.webim.web.handler.Handler;

@Controller
@RequestMapping("/admin/log")
public class LogController extends Handler{
	
	
	@Autowired
	private LogRepository logRes;

    @RequestMapping("/index")
    @Menu(type = "admin" , subtype = "syslog")
    public ModelAndView index(ModelMap map , HttpServletRequest request) {
    	map.addAttribute("logList", logRes.findByOrgi(UKDataContext.SYSTEM_ORGI , new PageRequest(super.getP(request), super.getPs(request) , Direction.DESC , "createdate")));
        return request(super.createAdminTempletResponse("/admin/system/log/index"));
    }
    
    @RequestMapping("/levels")
    @Menu(type = "admin" , subtype = "levels")
    public ModelAndView levels(ModelMap map , HttpServletRequest request , @Valid String levels) {
    	map.addAttribute("logList", logRes.findByOrgiAndLevels(UKDataContext.SYSTEM_ORGI , levels , new PageRequest(super.getP(request), super.getPs(request) , Direction.DESC , "createdate")));
    	map.addAttribute("levels" , levels) ;
        return request(super.createAdminTempletResponse("/admin/system/log/levels"));
    }
    
    @RequestMapping("/detail")
    @Menu(type = "admin" , subtype = "detail")
    public ModelAndView detail(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("log", logRes.findOne(id));
        return request(super.createRequestPageTempletResponse("/admin/system/log/detail"));
    }
}