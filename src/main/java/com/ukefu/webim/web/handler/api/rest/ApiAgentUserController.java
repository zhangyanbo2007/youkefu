package com.ukefu.webim.web.handler.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.AgentUserRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;

@RestController
@RequestMapping("/api/agentuser")
@Api(value = "ACD服务", description = "获取当前对话中的访客")
public class ApiAgentUserController extends Handler{
	
	@Autowired
	private AgentUserRepository agentUserRepository ;

	/**
	 * 获取当前对话中的访客
	 * @param request
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "agentuser" , access = true)
	@ApiOperation("获取当前正在对话的访客信息，包含多种渠道来源的访客")
    public ResponseEntity<RestResult> list(HttpServletRequest request , @Valid String q) {
        return new ResponseEntity<>(new RestResult(RestResultType.OK , agentUserRepository.findByAgentnoAndOrgi(super.getUser(request).getId() , super.getOrgi(request) , new Sort(Direction.ASC,"status"))), HttpStatus.OK);
    }
}