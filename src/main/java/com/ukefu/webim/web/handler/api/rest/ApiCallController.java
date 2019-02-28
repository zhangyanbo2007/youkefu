package com.ukefu.webim.web.handler.api.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.util.Menu;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/call")
@Api(value = "呼叫状态" , description = "呼叫状态")
public class ApiCallController extends Handler{

	/**
	 * 返回在线网站配置
	 * @param request
	 * @param id	snsaccountid
	 * @return
	 */
	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "webim" , access = true)
	@ApiOperation("监控语音网关状态")
    public ResponseEntity<RestResult> monitor(HttpServletRequest request,@RequestBody String body) {
        return new ResponseEntity<>(new RestResult(RestResultType.OK, ""), HttpStatus.OK);
    }
	
}