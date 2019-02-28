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
import com.ukefu.webim.service.repository.AclRepository;
import com.ukefu.webim.service.repository.CallCenterSkillRepository;
import com.ukefu.webim.service.repository.ExtentionRepository;
import com.ukefu.webim.service.repository.MediaRepository;
import com.ukefu.webim.service.repository.PbxHostRepository;
import com.ukefu.webim.service.repository.RouterRulesRepository;
import com.ukefu.webim.service.repository.SipTrunkRepository;
import com.ukefu.webim.service.repository.SkillExtentionRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.PbxHost;

@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterController extends Handler{
	
	@Autowired
	private PbxHostRepository pbxHostRes ;
	
	@Autowired
	private ExtentionRepository extentionRes;
	
	@Autowired
	private CallCenterSkillRepository skillRes ;
	
	@Autowired
	private SkillExtentionRepository skillExtentionRes;
	
	@Autowired
	private RouterRulesRepository routerRulesRes ;
	
	@Autowired
	private AclRepository aclRes ;
	
	@Autowired
	private SipTrunkRepository sipTrunkRes ;
	
	@Autowired
	private MediaRepository mediaRes ;
	
	@RequestMapping(value = "/index")
    @Menu(type = "callcenter" , subtype = "callcenter" , access = false , admin = true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String msg) {
		List<PbxHost> pbxHostList = pbxHostRes.findByOrgi(super.getOrgi(request)) ;
		if(UKDataContext.model.get("callcenter")!=null){
			CallCenterInterface callCenterImpl = (CallCenterInterface) UKDataContext.getContext().getBean("callcenter") ;
			
			for(PbxHost pbxHost : pbxHostList){
				if(callCenterImpl!=null){
					pbxHost.setConnected(callCenterImpl.connected(pbxHost.getId()));
				}
			}
		}
		map.addAttribute("pbxHostList" , pbxHostList);
    	return request(super.createAdminTempletResponse("/admin/callcenter/index"));
    }
	
	@RequestMapping(value = "/pbxhost")
    @Menu(type = "callcenter" , subtype = "pbxhost" , access = false , admin = true)
    public ModelAndView pbxhost(ModelMap map , HttpServletRequest request) {
		List<PbxHost> pbxHostList = pbxHostRes.findByOrgi(super.getOrgi(request)) ;
		if(UKDataContext.model.get("callcenter")!=null){
			CallCenterInterface callCenterImpl = (CallCenterInterface) UKDataContext.getContext().getBean("callcenter") ;
			
			for(PbxHost pbxHost : pbxHostList){
				if(callCenterImpl!=null){
					pbxHost.setConnected(callCenterImpl.connected(pbxHost.getId()));
				}
			}
		}
		map.addAttribute("pbxHostList" , pbxHostList);
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/pbxhost/index"));
    }
	
	@RequestMapping(value = "/pbxhost/add")
    @Menu(type = "callcenter" , subtype = "pbxhost" , access = false , admin = true)
    public ModelAndView pbxhostadd(ModelMap map , HttpServletRequest request) {
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/pbxhost/add"));
    }
	
	@RequestMapping(value = "/pbxhost/save")
    @Menu(type = "callcenter" , subtype = "pbxhost" , access = false , admin = true)
    public ModelAndView pbxhostsave(ModelMap map , HttpServletRequest request , @Valid PbxHost pbxHost) {
		ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/pbxhost.html"));
		String msg = null ;
		if(!StringUtils.isBlank(pbxHost.getName())){
			int count = pbxHostRes.countByHostnameAndOrgi(pbxHost.getHostname(), super.getOrgi(request)) ;
			if(count == 0){	
				pbxHost.setOrgi(super.getOrgi(request));
				pbxHost.setCreater(super.getUser(request).getId());
				pbxHostRes.save(pbxHost) ;
				if(UKDataContext.model.get("callcenter")!=null){
					CallCenterInterface callCenterImpl = (CallCenterInterface) UKDataContext.getContext().getBean("callcenter") ;
					if(callCenterImpl!=null){
                        try{
                            callCenterImpl.init(pbxHost);
                        }catch(Exception ex){
                            msg = ex.getMessage() ;
                            ex.printStackTrace();
                        }
					}
				}
			}
		}
		if(!StringUtils.isBlank(msg)){
			view = request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/pbxhost.html?msg="+msg));
		}
		return view;
    }
	
	@RequestMapping(value = "/pbxhost/edit")
    @Menu(type = "callcenter" , subtype = "pbxhost" , access = false , admin = true)
    public ModelAndView pbxhostedit(ModelMap map , HttpServletRequest request , @Valid String id) {
		map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(id, super.getOrgi(request)));
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/pbxhost/edit"));
    }
	
	@RequestMapping(value = "/pbxhost/update")
    @Menu(type = "callcenter" , subtype = "pbxhost" , access = false , admin = true)
    public ModelAndView pbxhostupdate(ModelMap map , HttpServletRequest request , @Valid PbxHost pbxHost) {
		ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/pbxhost.html"));
		String msg = null ;
		if(!StringUtils.isBlank(pbxHost.getId())){
			PbxHost destHost = pbxHostRes.findByIdAndOrgi(pbxHost.getId(), super.getOrgi(request)) ;
			destHost.setHostname(pbxHost.getHostname());
			destHost.setIpaddr(pbxHost.getIpaddr());
			destHost.setName(pbxHost.getName());
			destHost.setPort(pbxHost.getPort());
			if(!StringUtils.isBlank(pbxHost.getPassword())){
				destHost.setPassword(pbxHost.getPassword());
			}
			pbxHostRes.save(destHost) ;
			if(UKDataContext.model.get("callcenter")!=null){
				CallCenterInterface callCenterImpl = (CallCenterInterface) UKDataContext.getContext().getBean("callcenter") ;
				if(callCenterImpl!=null){
					try{
						callCenterImpl.init(destHost);
					}catch(Exception ex){
						msg = ex.getMessage() ;
						ex.printStackTrace();
					}
				}
			}
			CacheHelper.getSystemCacheBean().put(pbxHost.getIpaddr() ,pbxHost , pbxHost.getOrgi()) ;
		}
		if(!StringUtils.isBlank(msg)){
			view = request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/pbxhost.html?msg="+msg));
		}
		return view;
    }
	
	@RequestMapping(value = "/pbxhost/delete")
    @Menu(type = "callcenter" , subtype = "pbxhost" , access = false , admin = true)
    public ModelAndView mediadelete(ModelMap map , HttpServletRequest request , @Valid String id) {
		if(!StringUtils.isBlank(id)){
			PbxHost pbxHost = pbxHostRes.findById(id) ;
			pbxHostRes.delete(id);
			if(UKDataContext.model.get("callcenter")!=null){
				CallCenterInterface callCenterImpl = (CallCenterInterface) UKDataContext.getContext().getBean("callcenter") ;
				if(callCenterImpl!=null){
					callCenterImpl.remove(id);
				}
			}
			CacheHelper.getSystemCacheBean().delete(pbxHost.getIpaddr() ,pbxHost.getOrgi()) ;
			//删除关联数据
			//删除分机配置
			extentionRes.delete(extentionRes.findByHostidAndOrgi(pbxHost.getId() , super.getOrgi(request))	);
			//删除队列信息
			skillRes.delete(skillRes.findByHostidAndOrgi(pbxHost.getId() , super.getOrgi(request)));
			skillExtentionRes.delete(skillExtentionRes.findByHostidAndOrgi(pbxHost.getId(), super.getOrgi(request)));
			//删除路由规则
			routerRulesRes.delete(routerRulesRes.findByHostidAndOrgi(pbxHost.getId(), super.getOrgi(request)));
			//删除acl
			aclRes.delete(aclRes.findByHostidAndOrgi(pbxHost.getId(), super.getOrgi(request)));
			//删除sip网关
			sipTrunkRes.delete(sipTrunkRes.findByHostidAndOrgi(pbxHost.getId(), super.getOrgi(request)));
			//删除 媒体资源
			mediaRes.delete(mediaRes.findByHostidAndOrgi(pbxHost.getId(), super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/pbxhost.html"));
    }
}
