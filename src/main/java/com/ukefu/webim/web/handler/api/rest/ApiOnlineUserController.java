package com.ukefu.webim.web.handler.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.OnlineUserRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.OnlineUser;

@RestController
@RequestMapping("/api/online/user")
@Api(value = "在线访客" , description = "获取在线访客功能")
public class ApiOnlineUserController extends Handler{

	@Autowired
	private OnlineUserRepository onlineUserRepository;
	
	/**
	 * 返回所有部门
	 * @param request
	 * @param username	搜索用户名，精确搜索
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "sysdic" , access = true)
	@ApiOperation("获取在线客服")
    public ResponseEntity<RestResult> list(HttpServletRequest request , @Valid String userid) {
		Page<OnlineUser> onlineUserList = null ;
		if(!StringUtils.isBlank(userid)){
			onlineUserList = onlineUserRepository.findByUseridAndOrgi(userid, super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" })) ;
		}else{
			onlineUserList = onlineUserRepository.findByOrgiAndStatus(super.getOrgi(request), UKDataContext.OnlineUserOperatorStatus.ONLINE.toString(), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" })) ;
		}
        return new ResponseEntity<>(new RestResult(RestResultType.OK, onlineUserList), HttpStatus.OK);
    }
}