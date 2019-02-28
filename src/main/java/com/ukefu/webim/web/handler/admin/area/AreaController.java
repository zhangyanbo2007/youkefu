package com.ukefu.webim.web.handler.admin.area;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.AreaTypeRepository;
import com.ukefu.webim.service.repository.SysDicRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AreaType;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.UKeFuDic;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@Controller
@RequestMapping("/admin/area")
public class AreaController extends Handler{
	
	@Autowired
	private AreaTypeRepository areaRepository;
	
	@Autowired
	private SysDicRepository sysDicRepository;

    @RequestMapping("/index")
    @Menu(type = "admin" , subtype = "area")
    public ModelAndView index(ModelMap map , HttpServletRequest request) throws FileNotFoundException, IOException {
    	map.addAttribute("areaList", areaRepository.findByOrgi(super.getOrgi(request)));
    	return request(super.createAdminTempletResponse("/admin/area/index"));
    }
    
    @RequestMapping("/add")
    @Menu(type = "admin" , subtype = "area")
    public ModelAndView add(ModelMap map , HttpServletRequest request) {
    	SysDic sysDic = sysDicRepository.findByCode(UKDataContext.UKEFU_SYSTEM_AREA_DIC) ;
    	if(sysDic!=null){
	    	map.addAttribute("sysarea", sysDic) ;
	    	map.addAttribute("areaList", sysDicRepository.findByDicid(sysDic.getId())) ;
    	}
    	map.addAttribute("cacheList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_AREA_DIC)) ;
        return request(super.createRequestPageTempletResponse("/admin/area/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "admin" , subtype = "area")
    public ModelAndView save(HttpServletRequest request ,@Valid AreaType area) {
    	int areas = areaRepository.countByNameAndOrgi(area.getName(), super.getOrgi(request)) ;
    	if(areas == 0){
    		area.setOrgi(super.getOrgi(request));
    		area.setCreatetime(new Date());
    		area.setCreater(super.getUser(request).getId());
    		areaRepository.save(area) ;
    		UKTools.initSystemArea();
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/area/index.html"));
    }
    
    @RequestMapping("/edit")
    @Menu(type = "admin" , subtype = "area")
    public ModelAndView edit(ModelMap map ,HttpServletRequest request , @Valid String id) {
    	map.addAttribute("area", areaRepository.findByIdAndOrgi(id, super.getOrgi(request))) ;
    	
    	SysDic sysDic = sysDicRepository.findByCode(UKDataContext.UKEFU_SYSTEM_AREA_DIC) ;
    	if(sysDic!=null){
	    	map.addAttribute("sysarea", sysDic) ;
	    	map.addAttribute("areaList", sysDicRepository.findByDicid(sysDic.getId())) ;
    	}
    	map.addAttribute("cacheList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_AREA_DIC)) ;
        return request(super.createRequestPageTempletResponse("/admin/area/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "admin" , subtype = "area" , admin = true)
    public ModelAndView update(HttpServletRequest request ,@Valid AreaType area) {
    	AreaType areaType = areaRepository.findByIdAndOrgi(area.getId(), super.getOrgi(request)) ;
    	if(areaType != null){
    		area.setCreatetime(areaType.getCreatetime());
    		area.setOrgi(super.getOrgi(request));
    		area.setCreater(areaType.getCreater());
    		areaRepository.save(area) ;
    		UKTools.initSystemArea();
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/area/index.html"));
    }
    
    @RequestMapping("/delete")
    @Menu(type = "admin" , subtype = "area")
    public ModelAndView delete(HttpServletRequest request ,@Valid AreaType area) {
    	AreaType areaType = areaRepository.findByIdAndOrgi(area.getId(), super.getOrgi(request)) ;
    	if(areaType!=null){
    		areaRepository.delete(areaType);
    		UKTools.initSystemArea();
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/area/index.html"));
    }
}