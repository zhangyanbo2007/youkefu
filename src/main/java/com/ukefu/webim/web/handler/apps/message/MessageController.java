package com.ukefu.webim.web.handler.apps.message;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.web.handler.Handler;

@Controller
@RequestMapping("/message")
public class MessageController extends Handler{
	
    @RequestMapping("/ping")
    @Menu(type = "message" , subtype = "ping" , admin= true)
    public ModelAndView ping(ModelMap map , HttpServletRequest request) {
    	map.put("datetime", UKTools.dateFormate.format(new Date())) ;
    	map.put("session", UKTools.getContextID(request.getSession().getId())) ;
        return request(super.createRequestPageTempletResponse("/apps/message/ping"));
    }
}