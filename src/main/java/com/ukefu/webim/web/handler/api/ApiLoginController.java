package com.ukefu.webim.web.handler.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.service.repository.UserRoleRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.User;
import com.ukefu.webim.web.model.UserRole;

@RestController
@RequestMapping("/tokens")
@Api(value = "登录服务", description = "账号密码登录")
public class ApiLoginController extends Handler{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRes ;


    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
    @Menu(type = "apps" , subtype = "token" , access = true)
    @ApiOperation("登录服务，传入登录账号和密码")
    public ResponseEntity login(HttpServletRequest request , HttpServletResponse response , @Valid String username, @Valid String password) {
    	User loginUser = userRepository.findByUsernameAndPassword(username , UKTools.md5(password)) ;
    	ResponseEntity entity = null ;
        if(loginUser!=null && !StringUtils.isBlank(loginUser.getId())){
        	loginUser.setLogin(true);
        	List<UserRole> userRoleList = userRoleRes.findByOrgiAndUser(loginUser.getOrgi(), loginUser);
        	if(userRoleList!=null && userRoleList.size()>0){
        		for(UserRole userRole : userRoleList){
        			loginUser.getRoleList().add(userRole.getRole()) ;
        		}
        	}
        	loginUser.setLastlogintime(new Date());
        	if(!StringUtils.isBlank(loginUser.getId())){
        		userRepository.save(loginUser) ;
        	}
        	String auth = UKTools.getUUID();
        	CacheHelper.getApiUserCacheBean().put(auth, loginUser, UKDataContext.SYSTEM_ORGI);
        	entity = new ResponseEntity<>(auth, HttpStatus.OK) ;
        	response.addCookie(new Cookie("authorization",auth));
        }else{
        	entity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED) ;
        }
        return entity;
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET)
    @Menu(type = "apps" , subtype = "token" , access = true)
    public ResponseEntity error(HttpServletRequest request) {
    	User data = super.getUser(request);
        return new ResponseEntity<>(data , data!=null ?  HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity logout(HttpServletRequest request , @RequestHeader(value="authorization") String authorization) {
    	CacheHelper.getApiUserCacheBean().delete(authorization, UKDataContext.SYSTEM_ORGI);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}