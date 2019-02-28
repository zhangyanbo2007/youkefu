package com.ukefu.webim.web.handler.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.web.handler.Handler;

@Controller
public class CssResourceController extends Handler{
	
    @RequestMapping("/res/css")
    @Menu(type = "resouce" , subtype = "css" , access = true)
    public ModelAndView index(HttpServletResponse response, @Valid String id) throws IOException {
    	response.setContentType("text/css ; charset=UTF-8");
    	return request(super.createRequestPageTempletResponse("/resource/css/ukefu"));
    }
    
    @RequestMapping("/res/css/system")
    @Menu(type = "resouce" , subtype = "css" , access = true)
    public ModelAndView system(HttpServletResponse response, @Valid String id) throws IOException {
    	response.setContentType("text/css ; charset=UTF-8");
    	return request(super.createRequestPageTempletResponse("/resource/css/system"));
    }
}