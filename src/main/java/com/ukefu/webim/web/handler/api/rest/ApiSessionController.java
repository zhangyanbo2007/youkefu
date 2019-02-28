package com.ukefu.webim.web.handler.api.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.web.handler.Handler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "登录服务", description = "访客端登陆获取Token")
public class ApiSessionController extends Handler{

	@RequestMapping(value = "/session", method = RequestMethod.POST)
    @Menu(type = "apps" , subtype = "session" , access = true)
    @ApiOperation("登录服务，传入登录账号和密码")
    public ResponseEntity<Object> session(HttpServletRequest request , HttpServletResponse response , @Valid String userid ) {
    	ResponseEntity<Object> entity = null ;
        if(!StringUtils.isBlank(userid)){
        	String auth = UKTools.getUUID();
        	CacheHelper.getApiUserCacheBean().put(auth, super.getIMUser(request, userid, null), UKDataContext.SYSTEM_ORGI);
        	entity = new ResponseEntity<Object>(auth, HttpStatus.OK) ;
        	response.addCookie(new Cookie("authorization",auth));
        }else{
        	entity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED) ;
        }
        return entity;
    }
}