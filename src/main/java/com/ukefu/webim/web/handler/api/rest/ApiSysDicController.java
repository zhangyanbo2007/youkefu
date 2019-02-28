package com.ukefu.webim.web.handler.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.util.Menu;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.UKeFuDic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/sysdic")
@Api(value = "数据字典" , description = "数据字典功能")
public class ApiSysDicController extends Handler{
	
	/**
	 * 返回所有部门
	 * @param request
	 * @param username	搜索用户名，精确搜索
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "sysdic" , access = true)
	@ApiOperation("获取在线客服")
    public ResponseEntity<RestResult> list(HttpServletRequest request , @Valid String code) {
        return new ResponseEntity<>(new RestResult(RestResultType.OK, UKeFuDic.getInstance().getDic(code)), HttpStatus.OK);
    }
}