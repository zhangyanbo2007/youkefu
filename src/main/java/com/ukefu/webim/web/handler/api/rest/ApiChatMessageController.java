package com.ukefu.webim.web.handler.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/chatmessage")
@Api(value = "获取对话内容", description = "获取访客对话的内容")
public class ApiChatMessageController extends Handler{
	
	@Autowired
	private ChatMessageRepository chatMessageRes ;

	/**
	 * 
	 * @param request
	 * @param serviceid		AgentServiceID
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "agentuser" , access = true)
	@ApiOperation("获取访客对话内容")
    public ResponseEntity<RestResult> list(HttpServletRequest request , @Valid String serviceid) {
		ResponseEntity<RestResult> result = null ;
		if(!StringUtils.isBlank(serviceid)) {
			result = new ResponseEntity<>(new RestResult(RestResultType.OK , chatMessageRes.findByAgentserviceidAndOrgi(serviceid , super.getUser(request).getOrgi(),new PageRequest(super.getP(request), super.getPs(request) , Sort.Direction.DESC, "createtime"))), HttpStatus.OK) ;
		}else {
			result = new ResponseEntity<>(new RestResult(RestResultType.LACKDATA , RestResultType.LACKDATA.getMessage()), HttpStatus.OK) ;
		}
        return result ;
    }
}