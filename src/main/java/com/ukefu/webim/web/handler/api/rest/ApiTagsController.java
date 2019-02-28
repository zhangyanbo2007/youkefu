package com.ukefu.webim.web.handler.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.TagRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;

@RestController
@RequestMapping("/api/tags")
@Api(value = "标签功能" , description = "获取分类标签")
public class ApiTagsController extends Handler{

	@Autowired
	private TagRepository tagRes;
	
	/**
	 * 按照分类获取标签列表
	 * @param request
	 * @param type 类型
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "tags" , access = true)
	@ApiOperation("按照分类获取标签列表，Type 参数类型来自于 枚举，可选值目前有三个 ： user  workorders summary")
    public ResponseEntity<RestResult> list(HttpServletRequest request , @Valid String type) {
        return new ResponseEntity<>(new RestResult(RestResultType.OK, tagRes.findByOrgiAndTagtype(super.getOrgi(request) , !StringUtils.isBlank(type) ? type : UKDataContext.ModelType.USER.toString())), HttpStatus.OK);
    }
}