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
import com.ukefu.webim.service.repository.CubeLevelRepository;
import com.ukefu.webim.service.repository.CubeMetadataRepository;
import com.ukefu.webim.service.repository.DimensionRepository;
import com.ukefu.webim.service.repository.TablePropertiesRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.CubeLevel;
import com.ukefu.webim.web.model.CubeMetadata;
import com.ukefu.webim.web.model.Dimension;

@Controller
@RequestMapping("/apps/report/dimension")
public class DimensionController extends Handler{
	
	@Autowired
	private DimensionRepository dimensionRes;
	
	@Autowired
	private CubeLevelRepository cubeLevelRes;
	
	@Autowired
	private CubeMetadataRepository cubeMetadataRes;
	
	@Autowired
	private TablePropertiesRepository tablePropertiesRes;
	
	@RequestMapping("/add")
    @Menu(type = "report" , subtype = "dimension")
    public ModelAndView dimensionadd(ModelMap map , HttpServletRequest request , @Valid String cubeid) {
    	map.addAttribute("cubeid", cubeid);
    	map.addAttribute("fkfieldList",cubeMetadataRes.findByCubeidAndMtype(cubeid,"0"));
    	map.addAttribute("fktableList",cubeMetadataRes.findByCubeidAndMtypeNot(cubeid,"0"));
        return request(super.createRequestPageTempletResponse("/apps/business/report/cube/dimension/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "report" , subtype = "dimension" )
    public ModelAndView dimensionsave(ModelMap map , HttpServletRequest request , @Valid Dimension dimension) {
    	if(!StringUtils.isBlank(dimension.getName())){
    		dimension.setOrgi(super.getOrgi(request));
    		dimension.setCreater(super.getUser(request).getId());
			dimensionRes.save(dimension) ;
    	}
        return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?id="+dimension.getCubeid()+"&dimensionId="+dimension.getId()));
    }
    
    @RequestMapping("/delete")
    @Menu(type = "report" , subtype = "dimension" )
    public ModelAndView quickreplydelete(ModelMap map , HttpServletRequest request , @Valid String id) {
    	Dimension dimension = dimensionRes.findOne(id) ;
    	if(dimension!=null){
    		dimensionRes.delete(dimension);
    		List<CubeLevel> cubeLevelList = cubeLevelRes.findByOrgiAndDimid(super.getOrgi(request), id);
    		if(!cubeLevelList.isEmpty()) {
    			cubeLevelRes.delete(cubeLevelList);
    		}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?id="+dimension.getCubeid()));
    }
    @RequestMapping("/edit")
    @Menu(type = "report" , subtype = "dimension" , admin= true)
    public ModelAndView quickreplyedit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	Dimension dimension = dimensionRes.findOne(id) ; 
    	map.put("dimension", dimension) ;
    	String cubeid = dimension.getCubeid();
    	map.addAttribute("cubeid", cubeid);
    	map.addAttribute("fkfieldList",cubeMetadataRes.findByCubeidAndMtype(cubeid,"0"));
    	List<CubeMetadata> fktableList = cubeMetadataRes.findByCubeidAndMtypeNot(cubeid,"0");
    	map.addAttribute("fktableList",fktableList);
    	map.put("fktableidList", tablePropertiesRes.findByDbtableid(dimension.getFktable()));
        return request(super.createRequestPageTempletResponse("/apps/business/report/cube/dimension/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "report" , subtype = "dimension" , admin= true)
    public ModelAndView quickreplyupdate(ModelMap map , HttpServletRequest request , @Valid Dimension dimension) {
    	if(!StringUtils.isBlank(dimension.getId())){
    		Dimension temp = dimensionRes.findOne(dimension.getId()) ;
    		dimension.setOrgi(super.getOrgi(request));
    		dimension.setCreater(super.getUser(request).getId());
    		if(temp!=null){
    			dimension.setCreatetime(temp.getCreatetime());
    		}
    		dimensionRes.save(dimension) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?id="+dimension.getCubeid()+"&dimensionId="+dimension.getId()));
    }
    @RequestMapping("/fktableid")
    @Menu(type = "report" , subtype = "dimension" , admin= true)
    public ModelAndView fktableid(ModelMap map , HttpServletRequest request , @Valid String tableid) {
    	if(!StringUtils.isBlank(tableid)){
    		map.put("fktableidList", tablePropertiesRes.findByDbtableid(tableid));
    	}
    	return request(super.createRequestPageTempletResponse("/apps/business/report/cube/dimension/fktableiddiv"));
    }
}