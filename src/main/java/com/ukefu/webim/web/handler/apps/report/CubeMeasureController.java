package com.ukefu.webim.web.handler.apps.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.CubeMeasureRepository;
import com.ukefu.webim.service.repository.CubeMetadataRepository;
import com.ukefu.webim.service.repository.TablePropertiesRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.CubeMeasure;
import com.ukefu.webim.web.model.CubeMetadata;

@Controller
@RequestMapping("/apps/report/cubemeasure")
public class CubeMeasureController extends Handler{
	
	@Autowired
	private CubeMeasureRepository cubeMeasureRes;
	
	@Autowired
	private TablePropertiesRepository tablePropertiesRes;
	
	@Autowired
	private CubeMetadataRepository cubeMetadataRes;
	
	@RequestMapping("/add")
    @Menu(type = "report" , subtype = "cubemeasure")
    public ModelAndView cubeMeasureadd(ModelMap map , HttpServletRequest request , @Valid String cubeid) {
    	map.addAttribute("cubeid", cubeid);
    	List<CubeMetadata> cmList = cubeMetadataRes.findByCubeidAndMtype(cubeid,"0");
		if(!cmList.isEmpty() && cmList.get(0)!=null) {
			map.put("fktableidList", tablePropertiesRes.findByDbtableid(cmList.get(0).getTb().getId()));
			map.put("table", cmList.get(0).getTb());
		}
        return request(super.createRequestPageTempletResponse("/apps/business/report/cube/cubemeasure/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "report" , subtype = "cubemeasure" )
    public ModelAndView cubeMeasuresave(ModelMap map , HttpServletRequest request , @Valid CubeMeasure cubeMeasure) {
    	if(!StringUtils.isBlank(cubeMeasure.getName())){
    		cubeMeasure.setOrgi(super.getOrgi(request));
    		cubeMeasure.setCreater(super.getUser(request).getId());
    		cubeMeasure.setCode(cubeMeasure.getColumname());
			cubeMeasureRes.save(cubeMeasure) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?dimensionId=cubemeasure&id="+cubeMeasure.getCubeid()));
    }
    
    @RequestMapping("/delete")
    @Menu(type = "report" , subtype = "cubemeasure" )
    public ModelAndView quickreplydelete(ModelMap map , HttpServletRequest request , @Valid String id) {
    	CubeMeasure cubeMeasure = cubeMeasureRes.findOne(id) ;
    	if(cubeMeasure!=null){
    		cubeMeasureRes.delete(cubeMeasure);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?dimensionId=cubemeasure&id="+cubeMeasure.getCubeid()));
    }
    @RequestMapping("/edit")
    @Menu(type = "report" , subtype = "cubemeasure" , admin= true)
    public ModelAndView quickreplyedit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	CubeMeasure cubeMeasure = cubeMeasureRes.findOne(id) ; 
    	map.put("cubemeasure", cubeMeasure) ;
    	if(cubeMeasure!=null) {
    		List<CubeMetadata> cmList = cubeMetadataRes.findByCubeidAndMtype(cubeMeasure.getCubeid(),"0");
    		if(!cmList.isEmpty() && cmList.get(0)!=null) {
    			map.put("fktableidList", tablePropertiesRes.findByDbtableid(cmList.get(0).getTb().getId()));
    			map.put("table", cmList.get(0).getTb());
    		}
    	}
        return request(super.createRequestPageTempletResponse("/apps/business/report/cube/cubemeasure/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "report" , subtype = "cubemeasure" , admin= true)
    public ModelAndView quickreplyupdate(ModelMap map , HttpServletRequest request , @Valid CubeMeasure cubeMeasure) {
    	if(!StringUtils.isBlank(cubeMeasure.getId())){
    		CubeMeasure temp = cubeMeasureRes.findOne(cubeMeasure.getId()) ;
    		cubeMeasure.setOrgi(super.getOrgi(request));
    		cubeMeasure.setCreater(super.getUser(request).getId());
    		if(temp!=null){
    			cubeMeasure.setCreatetime(temp.getCreatetime());
    		}
    		cubeMeasure.setCode(cubeMeasure.getColumname());
    		cubeMeasureRes.save(cubeMeasure) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?dimensionId=cubemeasure&id="+cubeMeasure.getCubeid()));
    }
    @RequestMapping("/fktableid")
    @Menu(type = "report" , subtype = "cubemeasure" , admin= true)
    public ModelAndView fktableid(ModelMap map , HttpServletRequest request , @Valid String tableid) {
    	if(!StringUtils.isBlank(tableid)){
    		map.put("fktableidList", tablePropertiesRes.findByDbtableid(tableid));
    	}
    	return request(super.createRequestPageTempletResponse("/apps/business/report/cube/cubemeasure/fktableiddiv"));
    }
}