package com.ukefu.webim.web.handler.admin.callcenter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.extra.CallCenterInterface;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.ExtentionRepository;
import com.ukefu.webim.service.repository.MediaRepository;
import com.ukefu.webim.service.repository.PbxHostRepository;
import com.ukefu.webim.service.repository.ServiceAiRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.PbxHost;

@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterResourceController extends Handler{
	
	@Autowired
	private PbxHostRepository pbxHostRes ;
	
	@Autowired
	private ExtentionRepository extentionRes;
	
	@Autowired
	private MediaRepository mediaRes ;
	
	@Autowired
	private ServiceAiRepository serviceAiRes ;
	
	@RequestMapping(value = "/resource")
    @Menu(type = "callcenter" , subtype = "callcenter" , access = false , admin = true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		List<PbxHost> pbxHostList = pbxHostRes.findByOrgi(super.getOrgi(request)) ;
		map.addAttribute("pbxHostList" , pbxHostList);
		map.addAttribute("mediaList" , mediaRes.findByHostidAndOrgi(hostid, super.getOrgi(request)));
		map.put("serviceAiList",serviceAiRes.findByOrgi(super.getOrgi(request)) ) ;
		
		PbxHost pbxHost = null ;
		if(pbxHostList.size() > 0){
			map.addAttribute("pbxHost" , pbxHost = getPbxHost(pbxHostList, hostid));
			map.addAttribute("extentionList" , extentionRes.findByHostidAndOrgi(pbxHost.getId() , super.getOrgi(request)));
		}
		return request(super.createAdminTempletResponse("/admin/callcenter/resource/index"));
    }
	
	@RequestMapping(value = "/resource/config")
    @Menu(type = "callcenter" , subtype = "callcenter" , access = false , admin = true)
    public ModelAndView config(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		List<PbxHost> pbxHostList = pbxHostRes.findByOrgi(super.getOrgi(request)) ;
		map.addAttribute("pbxHostList" , pbxHostList);
		map.addAttribute("mediaList" , mediaRes.findByHostidAndOrgi(hostid, super.getOrgi(request)));
		PbxHost pbxHost = null ;
		if(pbxHostList.size() > 0){
			map.addAttribute("pbxHost" , pbxHost = getPbxHost(pbxHostList, hostid));
			map.addAttribute("extentionList" , extentionRes.findByHostidAndOrgi(pbxHost.getId() , super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/resource/config"));
    }
	
	@RequestMapping(value = "/resource/save")
    @Menu(type = "callcenter" , subtype = "callcenter" , access = false , admin = true)
    public ModelAndView save(ModelMap map , HttpServletRequest request , @Valid PbxHost pbxHost) throws Exception {
		PbxHost tempPbxHost = pbxHostRes.findByIdAndOrgi(pbxHost.getId(), super.getOrgi(request)) ;
		if(tempPbxHost != null){
			pbxHost.setCreater(tempPbxHost.getCreater());
			pbxHost.setCreatetime(tempPbxHost.getCreatetime());
			if(StringUtils.isBlank(pbxHost.getPassword())){
				pbxHost.setPassword(tempPbxHost.getPassword());
			}
			pbxHost.setOrgi(super.getOrgi(request));
			pbxHostRes.save(pbxHost) ;
			
			if(UKDataContext.model.get("callcenter")!=null){
				CallCenterInterface callCenterImpl = (CallCenterInterface) UKDataContext.getContext().getBean("callcenter") ;
				callCenterImpl.init(pbxHost);
			}
			CacheHelper.getSystemCacheBean().put(pbxHost.getIpaddr() ,pbxHost , pbxHost.getOrgi()) ;
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/resource.html?hostid="+pbxHost.getId()));
    }
	
	@RequestMapping(value = "/resource/pbxhost")
    @Menu(type = "callcenter" , subtype = "callcenter" , access = false , admin = true)
    public ModelAndView resourcepbx(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		List<PbxHost> pbxHostList = pbxHostRes.findByOrgi(super.getOrgi(request)) ;
		map.addAttribute("pbxHostList" , pbxHostList);
		PbxHost pbxHost = null ;
		if(pbxHostList.size() > 0){
			map.addAttribute("pbxHost" , pbxHost = getPbxHost(pbxHostList, hostid));
			map.addAttribute("extentionList" , extentionRes.findByHostidAndOrgi(pbxHost.getId() , super.getOrgi(request)));
		}
		return request(super.createAdminTempletResponse("/admin/callcenter/resource/pbxhost"));
    }
	
	private PbxHost getPbxHost(List<PbxHost> pbxHostList ,String hostid){
		PbxHost pbxHost = pbxHostList.get(0) ;
		if(!StringUtils.isBlank(hostid)){
			for(PbxHost pbx : pbxHostList){
				if(pbx.getId().equals(hostid)){
					pbxHost = pbx; break ;
				}
			}
		}
		return pbxHost ;
	}
	
}
