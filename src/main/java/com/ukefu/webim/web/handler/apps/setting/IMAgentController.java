package com.ukefu.webim.web.handler.apps.setting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.AdTypeRepository;
import com.ukefu.webim.service.repository.BlackListRepository;
import com.ukefu.webim.service.repository.SessionConfigRepository;
import com.ukefu.webim.service.repository.TagRepository;
import com.ukefu.webim.service.repository.TemplateRepository;
import com.ukefu.webim.service.repository.WorkserviceTimeRepository;
import com.ukefu.webim.util.server.message.SessionConfigItem;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AdType;
import com.ukefu.webim.web.model.BlackEntity;
import com.ukefu.webim.web.model.SessionConfig;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.Tag;
import com.ukefu.webim.web.model.UKeFuDic;
import com.ukefu.webim.web.model.WorkserviceTime;

@Controller
@RequestMapping("/setting")
public class IMAgentController extends Handler{
	
	@Autowired
	private SessionConfigRepository sessionConfigRes ;
	
	@Autowired
	private TagRepository tagRes ;
	
	@Autowired
	private BlackListRepository blackListRes;
	
	@Autowired
	private AdTypeRepository adTypeRes;
	
	@Autowired
	private TemplateRepository templateRes ;
	
	@Value("${web.upload-path}")
    private String path;
	
	@Autowired
	private WorkserviceTimeRepository workserviceTimeRes ;
	
	private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/agent/index")
    @Menu(type = "setting" , subtype = "sessionconfig" , admin= false)
    public ModelAndView index(ModelMap map , HttpServletRequest request){
    	SessionConfig sessionConfig = sessionConfigRes.findByOrgi(super.getOrgi(request)) ;
    	if(sessionConfig == null){
    		sessionConfig = new SessionConfig() ;
    	}
    	map.put("sessionConfig", sessionConfig) ;
    	
    	
    	List<SysDic> dicList = UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_DIC) ;
    	SysDic inputDic = null , outputDic = null ;
    	for(SysDic dic : dicList){
    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_AI_INPUT)){
    			inputDic = dic ;
    		}
    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_AI_OUTPUT)){
    			outputDic = dic ;
    		}
    	}
    	if(inputDic!=null){
    		map.addAttribute("innputtemlet", templateRes.findByTemplettypeAndOrgi(inputDic.getId(), super.getOrgi(request))) ;
    	}
    	if(outputDic!=null){
    		map.addAttribute("outputtemlet", templateRes.findByTemplettypeAndOrgi(outputDic.getId(), super.getOrgi(request))) ;
    	}
    	map.addAttribute("workDateList",UKeFuDic.getInstance().getDic("com.dic.workservice.time"));
    	map.addAttribute("workTypeList",UKeFuDic.getInstance().getDic("com.dic.workservice.worktype"));
    	
        return request(super.createAppsTempletResponse("/apps/setting/agent/index"));
    }
    
    @RequestMapping("/agent/sessionconfig/save")
    @Menu(type = "setting" , subtype = "sessionconfig" , admin= false)
    public ModelAndView sessionconfig(ModelMap map , HttpServletRequest request , @Valid SessionConfig sessionConfig,BindingResult result,  @RequestParam(value = "tipagenticon", required = false) MultipartFile tipagenticon) throws IOException{
    	SessionConfig tempSessionConfig = sessionConfigRes.findByOrgi(super.getOrgi(request)) ;
    	if(tempSessionConfig == null){
    		tempSessionConfig = sessionConfig;
    		tempSessionConfig.setCreater(super.getUser(request).getId());
    	}else{
    		UKTools.copyProperties(sessionConfig, tempSessionConfig);
    	}
    	if(sessionConfig.getWorkinghours() != null){
    		List<SessionConfigItem> sessionConfigList = new ArrayList<SessionConfigItem>();
    		String[] wk = sessionConfig.getWorkinghours().split(",");
    		for(String worktime : wk){
    			SessionConfigItem session = new SessionConfigItem();
    			String[] items = worktime.split(":", 3) ;
    			session.setType(items[0]);
    			session.setWorktype(items[1]);
    			session.setWorkinghours(items[2]);
    			sessionConfigList.add(session);
    		}
    		tempSessionConfig.setWorkinghours(objectMapper.writeValueAsString(sessionConfigList));
    	}else{
    		tempSessionConfig.setWorkinghours(null);
    	}
    	
    	tempSessionConfig.setOrgi(super.getOrgi(request));
    	
    	if(tipagenticon!=null && !StringUtils.isBlank(tipagenticon.getName()) && tipagenticon.getBytes()!=null && tipagenticon.getBytes().length >0){
			String fileName = "logo/"+UKTools.md5("tipagenticon_"+tempSessionConfig.getOrgi())+tipagenticon.getOriginalFilename().substring(tipagenticon.getOriginalFilename().lastIndexOf(".")) ;
			File file = new File(path , fileName) ;
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
    		FileCopyUtils.copy(tipagenticon.getBytes(), file);
    		tempSessionConfig.setTipagenticon(fileName);
		}
    	sessionConfigRes.save(tempSessionConfig) ;
    	
    	CacheHelper.getSystemCacheBean().put(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG+"_"+super.getOrgi(request),tempSessionConfig, super.getOrgi(request)) ;
    	
    	CacheHelper.getSystemCacheBean().delete(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG_LIST , UKDataContext.SYSTEM_ORGI) ;
    	
    	ServiceQuene.initSessionConfigList() ;
    	map.put("sessionConfig", tempSessionConfig) ;
    	
    	
        return request(super.createRequestPageTempletResponse("redirect:/setting/agent/index.html"));
    }
    
    @RequestMapping("/blacklist")
    @Menu(type = "setting" , subtype = "blacklist" , admin= false)
    public ModelAndView blacklist(ModelMap map , HttpServletRequest request) {
    	map.put("blackList", blackListRes.findByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, "endtime"))) ;
    	map.put("tagTypeList", UKeFuDic.getInstance().getDic("com.dic.tag.type")) ;
    	return request(super.createAppsTempletResponse("/apps/setting/agent/blacklist"));
    }
    
    @RequestMapping("/blacklist/delete")
    @Menu(type = "setting" , subtype = "tag" , admin= false)
    public ModelAndView blacklistdelete(ModelMap map , HttpServletRequest request , @Valid String id) {
    	if(!StringUtils.isBlank(id)){
    		BlackEntity tempBlackEntity = blackListRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
    		if(tempBlackEntity!=null){
		    	blackListRes.delete(tempBlackEntity);
		    	if (!StringUtils.isBlank(tempBlackEntity.getUserid())) {
		    		CacheHelper.getSystemCacheBean().delete(tempBlackEntity.getUserid(), UKDataContext.SYSTEM_ORGI) ;
				}
    		}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/setting/blacklist.html"));
    }
    
    @RequestMapping("/tag")
    @Menu(type = "setting" , subtype = "tag" , admin= false)
    public ModelAndView tag(ModelMap map , HttpServletRequest request , @Valid String code) {
    	SysDic tagType = null ;
    	List<SysDic> tagList = UKeFuDic.getInstance().getDic("com.dic.tag.type") ;
    	if(tagList.size() > 0){
    		
    		if(!StringUtils.isBlank(code)){
    			for(SysDic dic : tagList){
    				if(code.equals(dic.getCode())){
    					tagType = dic ;
    				}
    			}
    		}else{
    			tagType = tagList.get(0) ;
    		}
    		map.put("tagType", tagType) ;
    	}
    	if(tagType!=null){
    		map.put("tagList", tagRes.findByOrgiAndTagtype(super.getOrgi(request) , tagType.getCode() , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	}
    	map.put("tagTypeList", tagList) ;
    	return request(super.createAppsTempletResponse("/apps/setting/agent/tag"));
    }
    
    @RequestMapping("/tag/add")
    @Menu(type = "setting" , subtype = "tag" , admin= false)
    public ModelAndView tagadd(ModelMap map , HttpServletRequest request , @Valid String tagtype) {
    	map.addAttribute("tagtype", tagtype) ;
        return request(super.createRequestPageTempletResponse("/apps/setting/agent/tagadd"));
    }
    
    @RequestMapping("/tag/edit")
    @Menu(type = "setting" , subtype = "tag" , admin= false)
    public ModelAndView tagedit(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String tagtype) {
    	map.put("tag", tagRes.findOne(id)) ;
    	map.addAttribute("tagtype", tagtype) ;
        return request(super.createRequestPageTempletResponse("/apps/setting/agent/tagedit"));
    }
    
    @RequestMapping("/tag/update")
    @Menu(type = "setting" , subtype = "tag" , admin= false)
    public ModelAndView tagupdate(ModelMap map , HttpServletRequest request , @Valid Tag tag , @Valid String tagtype) {
    	List<Tag> tagList = tagRes.findByOrgiAndTagAndTagtype(super.getOrgi(request), tag.getTag(), tagtype) ;
    	if(tagList.size() == 1 && tagList.get(0).getId().equals(tag.getId())){
    		tag.setOrgi(super.getOrgi(request));
	    	tag.setCreater(super.getUser(request).getId());
	    	tagRes.save(tag) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/setting/tag.html?code="+tagtype));
    }
    
    @RequestMapping("/tag/save")
    @Menu(type = "setting" , subtype = "tag" , admin= false)
    public ModelAndView tagsave(ModelMap map , HttpServletRequest request , @Valid Tag tag , @Valid String tagtype) {
    	if(tagRes.countByOrgiAndTagAndTagtype(super.getOrgi(request), tag.getTag() , tagtype) == 0){
	    	tag.setOrgi(super.getOrgi(request));
	    	tag.setCreater(super.getUser(request).getId());
	    	tagRes.save(tag) ;
    	}
        return request(super.createRequestPageTempletResponse("redirect:/setting/tag.html?code="+tagtype));
    }
    
    @RequestMapping("/tag/delete")
    @Menu(type = "setting" , subtype = "tag" , admin= false)
    public ModelAndView tagdelete(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String tagtype) {
    	tagRes.delete(id);
    	return request(super.createRequestPageTempletResponse("redirect:/setting/tag.html?code="+tagtype));
    }
    
    
    
    @RequestMapping("/acd")
    @Menu(type = "setting" , subtype = "acd" , admin= false)
    public ModelAndView acd(ModelMap map , HttpServletRequest request) {
    	map.put("tagTypeList", UKeFuDic.getInstance().getDic("com.dic.tag.type")) ;
    	return request(super.createAppsTempletResponse("/apps/setting/agent/acd"));
    }
    
    
    @RequestMapping("/adv")
    @Menu(type = "setting" , subtype = "adv" , admin= false)
    public ModelAndView adv(ModelMap map , HttpServletRequest request , @Valid String adpos) {
    	SysDic advType = null ;
    	List<SysDic> tagList = UKeFuDic.getInstance().getDic("com.dic.adv.type") ;
    	if(tagList.size() > 0){
    		if(!StringUtils.isBlank(adpos)){
    			for(SysDic dic : tagList){
    				if(adpos.equals(dic.getId())){
    					advType = dic ;
    				}
    			}
    		}else{
    			advType = tagList.get(0) ;
    		}
    		map.put("advType", advType) ;
    	}
    	if(advType!=null){
    		map.put("adTypeList", adTypeRes.findByAdposAndOrgi(advType.getId() , super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" }))) ;
    	}
    	
    	map.put("tagTypeList", UKeFuDic.getInstance().getDic("com.dic.tag.type")) ;
    	
    	map.put("advTypeList", UKeFuDic.getInstance().getDic("com.dic.adv.type")) ;
    	
    	return request(super.createAppsTempletResponse("/apps/setting/agent/adv"));
    }
    
    @RequestMapping("/adv/add")
    @Menu(type = "setting" , subtype = "adv" , admin= false)
    public ModelAndView advadd(ModelMap map , HttpServletRequest request , @Valid String adpos) {
    	map.addAttribute("adpos", adpos) ;
        return request(super.createRequestPageTempletResponse("/apps/setting/agent/adadd"));
    }
    
    @RequestMapping("/adv/save")
    @Menu(type = "setting" , subtype = "adv" , admin= false)
    public ModelAndView advsave(ModelMap map , HttpServletRequest request , @Valid AdType adv , @Valid String advtype , @RequestParam(value = "imgfile", required = false) MultipartFile imgfile) throws IOException {
    	adv.setOrgi(super.getOrgi(request));
		adv.setCreater(super.getUser(request).getId());
		if(!StringUtils.isBlank(adv.getContent())){
			adv.setContent(adv.getContent().replaceAll("\"", "'"));
		}
		adv.setCreatetime(new Date());
		if(imgfile!=null && imgfile.getSize() > 0){
			File adDir = new File(path , "adv");
    		if(!adDir.exists()){
    			adDir.mkdirs() ;
    		}
    		String fileName = "adv/"+UKTools.getUUID()+imgfile.getOriginalFilename().substring(imgfile.getOriginalFilename().lastIndexOf(".")) ;
    		FileCopyUtils.copy(imgfile.getBytes(), new File(path , fileName));
    		adv.setImgurl("/res/image.html?id="+java.net.URLEncoder.encode(fileName , "UTF-8"));
		}
		adTypeRes.save(adv) ;
		
		UKTools.initAdv(super.getOrgi(request));
		
        return request(super.createRequestPageTempletResponse("redirect:/setting/adv.html?adpos="+adv.getAdpos()));
    }
    
    @RequestMapping("/adv/edit")
    @Menu(type = "setting" , subtype = "adv" , admin= false)
    public ModelAndView advedit(ModelMap map , HttpServletRequest request , @Valid String adpos, @Valid String id) {
    	map.addAttribute("adpos", adpos) ;
    	map.put("ad", adTypeRes.findByIdAndOrgi(id , super.getOrgi(request))) ;
        return request(super.createRequestPageTempletResponse("/apps/setting/agent/adedit"));
    }
    
    @RequestMapping("/adv/update")
    @Menu(type = "setting" , subtype = "adv" , admin= false)
    public ModelAndView advupdate(ModelMap map , HttpServletRequest request , @Valid AdType ad, @Valid String adpos, @RequestParam(value = "imgfile", required = false) MultipartFile imgfile) throws IOException {
    	AdType tempad = adTypeRes.findByIdAndOrgi(ad.getId(),super.getOrgi(request)) ;
    	if(tempad != null){
    		ad.setOrgi(super.getOrgi(request));
    		ad.setCreater(tempad.getCreater());
    		ad.setCreatetime(tempad.getCreatetime());
    		if(!StringUtils.isBlank(ad.getContent())){
    			ad.setContent(ad.getContent().replaceAll("\"", "'"));
    		}
    		if(imgfile!=null && imgfile.getSize() > 0){
    			File adDir = new File(path , "adv");
        		if(!adDir.exists()){
        			adDir.mkdirs() ;
        		}
        		String fileName = "adv/"+UKTools.getUUID()+imgfile.getOriginalFilename().substring(imgfile.getOriginalFilename().lastIndexOf(".")) ;
        		FileCopyUtils.copy(imgfile.getBytes(), new File(path , fileName));
        		ad.setImgurl("/res/image.html?id="+java.net.URLEncoder.encode(fileName , "UTF-8"));
    		}else{
    			ad.setImgurl(tempad.getImgurl());
    		}
    		adTypeRes.save(ad) ;
    		UKTools.initAdv(super.getOrgi(request));
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/setting/adv.html?adpos="+adpos));
    }
    
    @RequestMapping("/adv/delete")
    @Menu(type = "setting" , subtype = "adv" , admin= false)
    public ModelAndView advdelete(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String adpos) {
    	adTypeRes.delete(id);
    	UKTools.initAdv(super.getOrgi(request));
    	return request(super.createRequestPageTempletResponse("redirect:/setting/adv.html?adpos="+adpos));
    }
    /**
     * 坐席工作日、节日、假日的服务时间段设置
     */
    @RequestMapping("/workservice/index")
    @Menu(type = "setting" , subtype = "workservice" , admin= false)
    public ModelAndView workServiceTime(ModelMap map , HttpServletRequest request) {
    	
    	Page<WorkserviceTime> workServiceList = workserviceTimeRes.findByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request),Sort.Direction.DESC, new String[] { "createtime" }));
    	map.addAttribute("workServiceList", workServiceList);
    	
    	
        return request(super.createAppsTempletResponse("/apps/setting/agent/workservice"));
    }
    @RequestMapping("/workservice/add")
    @Menu(type = "setting" , subtype = "workservice" , admin= false)
    public ModelAndView addWorkservice(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String adpos) {
    	map.addAttribute("workDateList",UKeFuDic.getInstance().getDic("com.dic.workservice.time"));
    	return request(super.createRequestPageTempletResponse("/apps/setting/agent/addworkservice"));
    }
    @RequestMapping("/workservice/add/save")
    @Menu(type = "setting" , subtype = "workservice" , admin= false)
    public ModelAndView addSaveWorkservice(ModelMap map , HttpServletRequest request , @Valid WorkserviceTime workserviceTime) throws Exception {
    	
    	if(!StringUtils.isBlank(workserviceTime.getScope())){
    		if("more".equals(workserviceTime.getScope()) && !StringUtils.isBlank(request.getParameter("mbegin"))&& !StringUtils.isBlank(request.getParameter("mend"))){
    			workserviceTime.setBegin(request.getParameter("mbegin"));
    			workserviceTime.setEnd(request.getParameter("mend"));
    		}
    	}
    	workserviceTime.setCreater(super.getUser(request).getId());
    	workserviceTime.setCreatetime(new Date());
    	workserviceTime.setOrgi(super.getOrgi(request));
    	workserviceTimeRes.save(workserviceTime);
    	
    	return request(super.createRequestPageTempletResponse("redirect:/setting/workservice/index"));
    }
    @RequestMapping("/workservice/edit")
    @Menu(type = "setting" , subtype = "workservice" , admin= false)
    public ModelAndView editWorkservice(ModelMap map , HttpServletRequest request , @Valid String id ) {
    	WorkserviceTime workservice = workserviceTimeRes.findByIdAndOrgi(id, super.getOrgi(request));
    	map.addAttribute("workservice", workservice);
    	map.addAttribute("workDateList",UKeFuDic.getInstance().getDic("com.dic.workservice.time"));
    	map.addAttribute("id", id);
    	return request(super.createRequestPageTempletResponse("/apps/setting/agent/editworkservice"));
    }
    @RequestMapping("/workservice/edit/save")
    @Menu(type = "setting" , subtype = "workservice" , admin= false)
    public ModelAndView editSaveWorkservice(ModelMap map , HttpServletRequest request , @Valid String id , @Valid WorkserviceTime workserviceTime) {
    	WorkserviceTime workservice = workserviceTimeRes.findByIdAndOrgi(id, super.getOrgi(request));
    	if(workservice != null){
    		workservice.setTimetype(workserviceTime.getTimetype());
    		workservice.setScope(workserviceTime.getScope());
    		if(!StringUtils.isBlank(workserviceTime.getScope())){
        		if("more".equals(workserviceTime.getScope()) && !StringUtils.isBlank(request.getParameter("mbegin"))&& !StringUtils.isBlank(request.getParameter("mend"))){
        			workservice.setBegin(request.getParameter("mbegin"));
        			workservice.setEnd(request.getParameter("mend"));
        		}else{
        			workservice.setBegin(workserviceTime.getBegin());
        		}
        	}
    		workservice.setUpdatetime(new Date());
    		workservice.setApply(workserviceTime.getApply());
    		workservice.setWeek(workserviceTime.getWeek());
    		workserviceTimeRes.save(workservice);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/setting/workservice/index"));
    }
    @RequestMapping("/workservice/delete")
    @Menu(type = "setting" , subtype = "workservice" , admin= false)
    public ModelAndView deleteWorkservice(ModelMap map , HttpServletRequest request , @Valid String id ) {
    	WorkserviceTime workservice = workserviceTimeRes.findByIdAndOrgi(id, super.getOrgi(request));
    	if(workservice != null){
    		workserviceTimeRes.delete(workservice);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/setting/workservice/index"));
    }
   

}