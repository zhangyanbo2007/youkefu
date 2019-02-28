package com.ukefu.webim.web.handler.apps.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.web.handler.Handler;

@Controller
public class TestController extends Handler{
	

	@RequestMapping({"/test/demo"})
	@Menu(type="apps", subtype="test" , access=false , admin = true)
	public ModelAndView content(ModelMap map , HttpServletRequest request){
		for(int i=0 ; i<500; i++){
			String user = UKTools.getUUID();
			try {
				OnlineUserUtils.newRequestMessage(user, "ukewo", "user", "system", "localhost" , "win10", "test" , UKDataContext.ChannelTypeEnum.WEBIM.toString() , null , null , "admin" , "标题" , "http://www.ukewo.cn" , "12434" , UKDataContext.ChatInitiatorType.USER.toString()) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return request(super.createAppsTempletResponse("/public/success"));
	}
}
