package com.ukefu.webim.web.handler.api;

import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.util.Menu;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;

@RestController
@RequestMapping("/tokens/error")
@Api(value = "登录服务", description = "Token验证失败")
public class ApiTokensErrorController extends Handler{
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET)
    @Menu(type = "apps" , subtype = "token" , access = true)
    public ResponseEntity error(HttpServletRequest request) {
        return new ResponseEntity<>(new RestResult(RestResultType.AUTH_ERROR), HttpStatus.OK);
    }
}