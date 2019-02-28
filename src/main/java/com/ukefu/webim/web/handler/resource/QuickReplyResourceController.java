package com.ukefu.webim.web.handler.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.web.handler.Handler;

import freemarker.template.TemplateException;

@Controller
public class QuickReplyResourceController extends Handler{
	
	@RequestMapping("/res/quickreply")
    @Menu(type = "res" , subtype = "quickreply")
    public ModelAndView add(ModelMap map , HttpServletRequest request , @Valid String q,@Valid String appid) throws IOException, TemplateException {
		if(q==null){
			q = "" ;
		}
    	map.addAttribute("quickReplyList", OnlineUserUtils.search(q, super.getOrgi(request), super.getUser(request) , appid)) ;
        return request(super.createRequestPageTempletResponse("/public/quickreply"));
    }
}