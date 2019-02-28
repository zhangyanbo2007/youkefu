package com.ukefu.webim.web.handler.api.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AgentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/quality")
@Api(value = "质量检查" , description = "在线客服质检功能")
public class ApiQualityController extends Handler{

	@Autowired
	private AgentServiceRepository agentServiceRepository;
	
	/**
	 * 返回所有部门
	 * @param request
	 * @param username	搜索用户名，精确搜索
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "webim" , access = true)
	@ApiOperation("获取质检列表")
    public ResponseEntity<RestResult> list(HttpServletRequest request , @Valid AgentService agentService ,@Valid String begin ,@Valid String end) {
		Page<AgentService> page = agentServiceRepository.findAll(new Specification<AgentService>(){
			@Override
			public Predicate toPredicate(Root<AgentService> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				list.add((cb.equal(root.get("qualitystatus").as(String.class), UKDataContext.QualityStatus.NODIS.toString()))) ;
				
				Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));   
			}}, new PageRequest(super.getP(request), super.getPs(request) , Sort.Direction.DESC, "createtime")) ;
        return new ResponseEntity<>(new RestResult(RestResultType.OK,page), HttpStatus.OK);
    }
}