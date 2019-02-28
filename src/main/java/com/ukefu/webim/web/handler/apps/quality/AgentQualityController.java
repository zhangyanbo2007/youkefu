package com.ukefu.webim.web.handler.apps.quality;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.QualityRepository;
import com.ukefu.webim.service.repository.SessionConfigRepository;
import com.ukefu.webim.service.repository.TagRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Quality;
import com.ukefu.webim.web.model.QualityRequest;
import com.ukefu.webim.web.model.SessionConfig;
import com.ukefu.webim.web.model.Tag;

@Controller
@RequestMapping("/apps/quality")
public class AgentQualityController extends Handler{
	
	
	@Autowired
	private QualityRepository qualityRes ;
	
	@Autowired
	private SessionConfigRepository sessionConfigRes ;
	
	@Autowired
	private TagRepository tagRes ;
	
	
	@RequestMapping(value = "/index")
    @Menu(type = "agent" , subtype = "quality" , access = false)
    public ModelAndView index(ModelMap map , HttpServletRequest request ) {
		map.addAttribute("sessionConfig", ServiceQuene.initSessionConfig(super.getOrgi(request))) ;
		map.addAttribute("qualityList", qualityRes.findByQualitytypeAndOrgi(UKDataContext.QualityType.CHAT.toString(), super.getOrgi(request))) ;
		map.addAttribute("tagList", tagRes.findByOrgiAndTagtype(super.getOrgi(request), UKDataContext.TagTypeEnum.QUALITY.toString())) ;
    	return request(super.createAppsTempletResponse("/apps/quality/index"));
    }
	
	
	@RequestMapping(value = "/save")
    @Menu(type = "agent" , subtype = "quality" , access = false)
    public ModelAndView save(ModelMap map , HttpServletRequest request ,@Valid QualityRequest qualityArray) {
		if(qualityArray!=null && qualityArray.getTitle()!=null) {
			List<Quality> qualityList = qualityRes.findByQualitytypeAndOrgi(UKDataContext.QualityType.CHAT.toString(), super.getOrgi(request)) ;
			qualityRes.delete(qualityList);
			List<Quality> tempList = new ArrayList<Quality>();
			for(int i=0 ; i<qualityArray.getTitle().length ; i++) {
				Quality temp = new Quality();
				temp.setName(qualityArray.getTitle()[i]);
				if(qualityArray.getDescription().length == qualityArray.getTitle().length) {
					temp.setDescription(qualityArray.getDescription()[i]);
				}
				if(qualityArray.getScore().length == qualityArray.getTitle().length) {
					temp.setScore(qualityArray.getScore()[i]);
				}
				temp.setOrgi(super.getOrgi(request));
				temp.setQualitytype(UKDataContext.QualityType.CHAT.toString());
				tempList.add(temp) ;
			}
			if(tempList.size() > 0) {
				qualityRes.save(tempList) ;
			}
			SessionConfig config = ServiceQuene.initSessionConfig(super.getOrgi(request)) ;
			if(config!=null) {
				if("points".equals(request.getParameter("qualityscore"))) {
					config.setQualityscore("points");
				}else {
					config.setQualityscore("score");
				}
				
				sessionConfigRes.save(config) ;
		    	
		    	CacheHelper.getSystemCacheBean().put(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG+"_"+super.getOrgi(request),config, super.getOrgi(request)) ;
		    	
		    	CacheHelper.getSystemCacheBean().delete(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG_LIST , UKDataContext.SYSTEM_ORGI) ;
			}
			if(qualityArray!=null && qualityArray.getTag()!=null && qualityArray.getTag().length > 0) {
				List<Tag> tagList = tagRes.findByOrgiAndTagtype(super.getOrgi(request), UKDataContext.TagTypeEnum.QUALITY.toString()) ;
				if(tagList.size() > 0) {
					tagRes.delete(tagList);
				}
				List<Tag> tagTempList = new ArrayList<Tag>() ;
				for(String tag : qualityArray.getTag()) {
					Tag temp = new Tag();
					temp.setOrgi(super.getOrgi(request));
					temp.setCreater(super.getUser(request).getId());
					temp.setTag(tag);
					temp.setCreater(super.getOrgi(request));
					temp.setTagtype(UKDataContext.TagTypeEnum.QUALITY.toString());
					tagTempList.add(temp) ;
				}
				if(tagTempList.size() > 0) {
					tagRes.save(tagTempList) ;
				}
			}
		}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/quality/index.html"));
    }
}
