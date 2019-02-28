package com.ukefu.webim.web.handler.admin.callcenter;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.PbxHostRepository;
import com.ukefu.webim.service.repository.RouteItemRepository;
import com.ukefu.webim.service.repository.RouterRulesRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.PbxHost;
import com.ukefu.webim.web.model.RouteItem;
import com.ukefu.webim.web.model.RouterRules;

@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterRouterController extends Handler{
	
	@Autowired
	private PbxHostRepository pbxHostRes ;
	
	@Autowired
	private RouteItemRepository routeItemRes ;
	
	@Autowired
	private RouterRulesRepository routerRulesRes ;
	
	
	@RequestMapping(value = "/router")
    @Menu(type = "callcenter" , subtype = "callcenterresource" , access = false , admin = true)
    public ModelAndView skill(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		List<PbxHost> pbxHostList = pbxHostRes.findByOrgi(super.getOrgi(request)) ;
		map.addAttribute("pbxHostList" , pbxHostList);
		if(pbxHostList.size() > 0){
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
			map.addAttribute("routerRulesList" , routerRulesRes.findByHostidAndOrgi(hostid, super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/router/index"));
    }
	
	@RequestMapping(value = "/router/add")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView extentionadd(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/router/add"));
    }
	
	@RequestMapping(value = "/router/save")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView extentionsave(ModelMap map , HttpServletRequest request , @Valid RouterRules router) {
		if(!StringUtils.isBlank(router.getName())){
			int count = routerRulesRes.countByNameAndOrgi(router.getName(), super.getOrgi(request)) ;
			if(count == 0){
				router.setOrgi(super.getOrgi(request));
				router.setCreater(super.getUser(request).getId());
				routerRulesRes.save(router) ;
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/router.html?hostid="+router.getHostid()));
    }
	
	@RequestMapping(value = "/router/edit")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView routeredit(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		map.addAttribute("routerRules" , routerRulesRes.findByIdAndOrgi(id, super.getOrgi(request)));
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/router/edit"));
    }
	
	@RequestMapping(value = "/router/update")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView pbxhostupdate(ModelMap map , HttpServletRequest request , @Valid RouterRules router) {
		if(!StringUtils.isBlank(router.getId())){
			RouterRules oldRouter = routerRulesRes.findByIdAndOrgi(router.getId(), super.getOrgi(request)) ;
			if(oldRouter!=null){
				oldRouter.setName(router.getName());
				oldRouter.setField(router.getField());
				oldRouter.setRegex(router.getRegex());
				oldRouter.setRouterinx(router.getRouterinx());
				oldRouter.setFalsebreak(router.isFalsebreak());
				routerRulesRes.save(oldRouter);
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/router.html?hostid="+router.getHostid()));
    }
	
	@RequestMapping(value = "/router/code")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView routercode(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		map.addAttribute("routerRules" , routerRulesRes.findByIdAndOrgi(id, super.getOrgi(request)));
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/router/code"));
    }
	
	@RequestMapping(value = "/router/code/update")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView routercodeupdate(ModelMap map , HttpServletRequest request , @Valid RouterRules router) throws DocumentException {
		if(!StringUtils.isBlank(router.getId()) && !StringUtils.isBlank(router.getRoutercontent())){
			List<RouteItem> tempList = routeItemRes.findByRouteidAndHostidAndOrgi(router.getId(), router.getHostid(), super.getOrgi(request) , new Sort(Sort.Direction.DESC,"sortindex")) ;
			if(tempList.size() > 0) {
				routeItemRes.delete(tempList);
			}
			RouterRules oldRouter = routerRulesRes.findByIdAndOrgi(router.getId(), super.getOrgi(request)) ;
			if(!StringUtils.isBlank(router.getRoutercontent())){
				Pattern r = Pattern.compile("(\"[\\S\\s]*?\")");
				Matcher m = r.matcher(router.getRoutercontent());
				StringBuffer sb = new StringBuffer();
				while (m.find()) {
					String text = m.group() ;
					if(!StringUtils.isBlank(text)) {
						m.appendReplacement(sb, java.util.regex.Matcher.quoteReplacement(text.replaceAll("&", "amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")));
					}
				}
				m.appendTail(sb);
				
				oldRouter.setRoutercontent(router.getRoutercontent());
				routerRulesRes.save(oldRouter);
			}
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"utf-8\"?>"+router.getRoutercontent()).getBytes())) ;
			Element faultStore = document.getRootElement();
			Iterator<?> iterator = faultStore.elementIterator() ;
			List<RouteItem> routeItemList = new ArrayList<RouteItem>();
			int cindex = 0 ;
			while (iterator.hasNext()) {
				Element fault = (Element) iterator.next();
				if(!StringUtils.isBlank(fault.getName()) && fault.getName().equalsIgnoreCase("condition")) {
					RouteItem item = null ;
					if(!StringUtils.isBlank(fault.attributeValue("field"))) {
						item = new RouteItem("condition" , fault.attributeValue("field") , fault.attributeValue("expression") , fault.attributeValue("break"));
					}else {
						item = new RouteItem("condition" , fault.attributeValue("field") , fault.attributeValue("expression") , fault.attributeValue("break"));
					}
					routeItemList.add(item);
					item.setHostid(router.getHostid());
					item.setRouteid(router.getId());
					item.setUpdatetime(new Date());
					item.setCreater(super.getUser(request).getId());
					item.setOrgi(super.getOrgi(request));
					item.setSortindex(cindex++);
					Iterator<?> actionIterator = fault.elementIterator() ;
					if(actionIterator.hasNext()) {
						item.setChild(true);
						int inx = 0 ;
						while(actionIterator.hasNext()) {
							Element action = (Element) actionIterator.next();
							RouteItem actionItem = new  RouteItem("action" , action.attributeValue("application") , action.attributeValue("data"));
							actionItem.setParentid(item.getId());
							actionItem.setHostid(router.getHostid());
							actionItem.setSortindex(inx++);
							actionItem.setRouteid(router.getId());
							actionItem.setUpdatetime(new Date());
							actionItem.setCreater(super.getUser(request).getId());
							actionItem.setOrgi(super.getOrgi(request));
							
							routeItemList.add(actionItem) ;
						}
					}
				}
				if(routeItemList.size() > 0) {
					routeItemRes.save(routeItemList) ;
				}
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/router/design.html?id="+router.getId()+"&hostid="+router.getHostid()));
    }
	
	@RequestMapping(value = "/router/delete")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView extentiondelete(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		if(!StringUtils.isBlank(id)){
			List<RouteItem> routeItemList = routeItemRes.findByRouteidAndHostidAndOrgi(id , hostid , super.getOrgi(request) , new Sort(Sort.Direction.ASC ,"sortindex")) ;
			routeItemRes.delete(routeItemList);
			routerRulesRes.delete(id);
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/router.html?hostid="+hostid));
    }
	
	@RequestMapping(value = "/router/design")
    @Menu(type = "callcenter" , subtype = "callcenterivr" , access = false , admin = true)
    public ModelAndView design(ModelMap map , HttpServletRequest request , @Valid String hostid , @Valid String id) {
		if(!StringUtils.isBlank(hostid)){
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
		}
		map.addAttribute("routerRules" , routerRulesRes.findByIdAndOrgi(id, super.getOrgi(request)));
		map.addAttribute("routerItems" , routeItemRes.findByRouteidAndHostidAndOrgi(id , hostid , super.getOrgi(request) , new Sort(Sort.Direction.ASC ,"sortindex")));
		return request(super.createRequestPageTempletResponse("/admin/callcenter/router/design"));
    }
	
	@RequestMapping(value = "/router/design/conditionadd")
    @Menu(type = "callcenter" , subtype = "callcenterivr" , access = false , admin = true)
    public ModelAndView conditionadd(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid , @Valid String parentid) {
		if(!StringUtils.isBlank(hostid)){
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/router/conditionadd"));
    }
	
	@RequestMapping(value = "/design/condition/save")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView conditionsave(ModelMap map , HttpServletRequest request , @Valid RouteItem item) {
		if(item != null){
			
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter//router/design.html?hostid="+item.getHostid()+"&id="+item.getRouteid()));
    }
	
	@RequestMapping(value = "/router/design/actionadd")
    @Menu(type = "callcenter" , subtype = "callcenterivr" , access = false , admin = true)
    public ModelAndView actionadd(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid , @Valid String parentid) {
		if(!StringUtils.isBlank(hostid)){
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/router/actionadd"));
    }
	
	@RequestMapping(value = "/design/action/save")
    @Menu(type = "callcenter" , subtype = "extention" , access = false , admin = true)
    public ModelAndView actionsave(ModelMap map , HttpServletRequest request , @Valid RouteItem item) {
		if(item != null){
			
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter//router/design.html?hostid="+item.getHostid()+"&id="+item.getRouteid()));
    }
}
