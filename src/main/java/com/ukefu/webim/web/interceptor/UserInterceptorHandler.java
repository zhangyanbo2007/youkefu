package com.ukefu.webim.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.web.model.SystemConfig;
import com.ukefu.webim.web.model.UKeFuDic;
import com.ukefu.webim.web.model.User;

public class UserInterceptorHandler extends HandlerInterceptorAdapter {
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	boolean filter = false; 
        User user = (User) request.getSession(true).getAttribute(UKDataContext.USER_SESSION_NAME) ;
        if(handler instanceof HandlerMethod) {
	        HandlerMethod  handlerMethod = (HandlerMethod ) handler ;
	        Menu menu = handlerMethod.getMethod().getAnnotation(Menu.class) ;
	        if(user != null || (menu!=null && menu.access()) || handlerMethod.getBean() instanceof BasicErrorController){
	        	filter = true;
	        }
	        
	        if(!filter){
	        	response.sendRedirect("/login.html");
	        }
        }else {
        	filter =true ;
        }
        return filter ; 
    }

    @SuppressWarnings("unchecked")
	public void postHandle(HttpServletRequest arg0, HttpServletResponse response, Object arg2,
            ModelAndView view) throws Exception {
    	User user = (User) arg0.getSession().getAttribute(UKDataContext.USER_SESSION_NAME) ;
    	String infoace = (String) arg0.getSession().getAttribute(UKDataContext.UKEFU_SYSTEM_INFOACQ) ;		//进入信息采集模式
    	SystemConfig systemConfig = UKTools.getSystemConfig();
    	if( view!=null){
	    	if(user!=null){
				view.addObject("user", user) ;
				
				if(systemConfig!=null && systemConfig.isEnablessl()) {
					view.addObject("schema","https") ;
					if(arg0.getServerPort() == 80) {
						view.addObject("port", 443) ;
					}else {
						view.addObject("port", arg0.getServerPort()) ;
					}
				}else {
					view.addObject("schema",arg0.getScheme()) ;
					view.addObject("port",arg0.getServerPort()) ;
				}
				view.addObject("hostname",arg0.getServerName()) ;
				
				HandlerMethod  handlerMethod = (HandlerMethod ) arg2 ;
				Menu menu = handlerMethod.getMethod().getAnnotation(Menu.class) ;
				if(menu!=null){
					view.addObject("subtype", menu.subtype()) ;
					view.addObject("maintype", menu.type()) ;
					view.addObject("typename", menu.name()) ;
				}
				view.addObject("orgi", user.getOrgi()) ;
			}
	    	if(!StringUtils.isBlank(infoace)){
	    		view.addObject("infoace", infoace) ;		//进入信息采集模式
	    	}
	    	view.addObject("webimport",UKDataContext.getWebIMPort()) ;
	    	view.addObject("sessionid", UKTools.getContextID(arg0.getSession().getId())) ;
	    	
	    	view.addObject("models", UKDataContext.model) ;
	    	
	    	if(user!=null){
	    		view.addObject("agentStatusReport",ServiceQuene.getAgentReport(user.getOrgi())) ;
	    	}
			/**
			 * WebIM共享用户
			 */
			User imUser = (User) arg0.getSession().getAttribute(UKDataContext.IM_USER_SESSION_NAME) ;
			if(imUser == null && view!=null){
				imUser = new User();
				imUser.setUsername(UKDataContext.GUEST_USER) ;
				imUser.setId(UKTools.getContextID(arg0.getSession(true).getId())) ;
				imUser.setSessionid(imUser.getId()) ;
				view.addObject("imuser", imUser) ;
			}
			
			if(arg0.getParameter("msg") != null){
				view.addObject("msg", arg0.getParameter("msg")) ;
			}
			view.addObject("uKeFuDic", UKeFuDic.getInstance()) ;	//处理系统 字典数据 ， 通过 字典code 获取
			
			view.addObject("uKeFuSecField", CacheHelper.getSystemCacheBean().getCacheObject(UKDataContext.UKEFU_SYSTEM_SECFIELD, UKDataContext.SYSTEM_ORGI)) ;	//处理系统 需要隐藏号码的字段， 启动的时候加载
			
			if(systemConfig != null){
				view.addObject("systemConfig", systemConfig)  ;
			}else{
				view.addObject("systemConfig", new SystemConfig())  ;
			}
			view.addObject("tagTypeList", UKeFuDic.getInstance().getDic("com.dic.tag.type")) ;
	    	
			view.addObject("advTypeList", UKeFuDic.getInstance().getDic("com.dic.adv.type")) ;
			view.addObject("searchList",UKeFuDic.getInstance().getDic("com.callout.name.search"));
			view.addObject("sessionType",UKeFuDic.getInstance().getDic("com.dic.session.type"));
			view.addObject("smsTempletTypeList",UKeFuDic.getInstance().getDic("com.dic.sms.templetype"));
			
			Logger logger = LogManager.getLogger("com.ukefu.webim.web.handler.apps.internet.UCKeFuWeiXinController") ;
 			if(logger!=null && logger.getLevel() != null){
				systemConfig.setLoglevel(logger.getLevel().toString());
			}
 			view.addObject("ip", arg0.getRemoteAddr()) ;
 			
 			view.addObject("statusList",UKeFuDic.getInstance().getDic("com.dic.callout.activity"));
 			
 			/**
 			 * CRM参数
 			 */
 			NativeWebRequest webRequest = new ServletWebRequest(arg0);
 			Map<String, String> map = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
 		    if(map.get("crm_code")!=null) {
 		    	view.addObject("crm_code", map.get("crm_code")) ;
 		    }
    	}
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}