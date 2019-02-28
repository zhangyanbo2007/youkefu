package com.ukefu.webim.web.handler.apps.callcenter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.es.SearchTools;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.impl.CallOutQuene;
import com.ukefu.webim.service.repository.ExtentionRepository;
import com.ukefu.webim.service.repository.SipTrunkRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.util.CallCenterUtils;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.SipTrunk;
import com.ukefu.webim.web.model.User;

import freemarker.template.TemplateException;

@Controller
@RequestMapping("/apps/callcenter")
public class SipTrunkController extends Handler{
	
	@Autowired
	private ExtentionRepository extentionRes;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SipTrunkRepository sipTrunkRes ;
	
	@RequestMapping(value = "/siptrunk")
    @Menu(type = "callcenter" , subtype = "extention" , access = true)
    public ModelAndView detail(ModelMap map , HttpServletRequest request , HttpServletResponse response ,@Valid String extno) throws IOException, TemplateException {
		SipTrunk sipTrunk = CallCenterUtils.siptrunk(extno,super.getOrgi(request), sipTrunkRes, extentionRes) ;
		map.addAttribute("siptrunk" , sipTrunk);
		response.setContentType("Content-type: text/plain; charset=utf-8"); 
    	return request(super.createRequestPageTempletResponse("/apps/business/callcenter/extention/siptrunk"));
    }
	
	@RequestMapping(value = "/agent")
    @Menu(type = "callcenter" , subtype = "agent" , access = true)
    public ModelAndView agent(ModelMap map , HttpServletRequest request , HttpServletResponse response ,@Valid String ani ,@Valid String dest,@Valid String sip) throws IOException, TemplateException {
		SipTrunk sipTrunk = CallCenterUtils.siptrunk(sip,super.getOrgi(request), sipTrunkRes) ;
		map.addAttribute("siptrunk" , sipTrunk);
		String agent  = null ;
		response.setContentType("Content-type: text/plain; charset=utf-8"); 
		if(sipTrunk!=null) {
			PageImpl<UKDataBean> dataBeanList = SearchTools.namesearch(sipTrunk.getOrgi(), ani) ;
			if(dataBeanList!=null && dataBeanList.getContent().size() > 0) {
				UKDataBean dataBean = dataBeanList.getContent().get(0) ;
				if(dataBean.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT)!=null) {
					String disagent = (String) dataBean.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) ;
					/**
					 * 找到了 坐席
					 */
					CallCenterAgent callCenterAgent = (CallCenterAgent) CacheHelper.getCallCenterAgentCacheBean().getCacheObject(disagent, sipTrunk.getOrgi()) ;
					if(callCenterAgent!=null) {
						/**
						 * 坐席在线
						 */
						agent = callCenterAgent.getExtno() ;
					}else if(sipTrunk.isEnablecallagent()){//坐席不在线
						User user = userRepository.findById(disagent) ;
						if(!StringUtils.isBlank(user.getMobile())) {
							agent = user.getMobile() ;
						}
					}
				}else {
					/**
					 * 名单未分配 ， 转给网关进来的任何一个坐席 ， 从当前登录系统的 坐席中选取一个
					 */
					List<CallCenterAgent> agentList = CallOutQuene.service(sipTrunk.getId()) ;
					if(agentList.size() > 0) {
						CallCenterAgent callCenterAgent = agentList.get(0) ;
						agent = callCenterAgent.getExtno() ;
					}else if(!StringUtils.isBlank(sipTrunk.getNotready())){
						agent = sipTrunk.getNotready() ;
					}
				}
			}
			if(StringUtils.isBlank(agent)){
				/**
				 * 名单未分配 ， 转给网关进来的任何一个坐席 ， 从当前登录系统的 坐席中选取一个
				 */
				List<CallCenterAgent> agentList = CallOutQuene.service(sipTrunk.getId()) ;
				/**
				 * 未找到名单，从 SIPTrunk里选取一个 转移号码
				 */
				if(agentList.size() == 0 && !StringUtils.isBlank(sipTrunk.getNotready())) {
					agent = sipTrunk.getNotready() ;
				}else if( !StringUtils.isBlank(sipTrunk.getNoname())){
					agent = sipTrunk.getNoname() ;
				}
			}
		}
		map.addAttribute("agent" , agent);
		
    	return request(super.createRequestPageTempletResponse("/apps/business/callcenter/extention/agent"));
    }
}
