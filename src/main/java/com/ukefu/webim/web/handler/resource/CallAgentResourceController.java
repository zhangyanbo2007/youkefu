package com.ukefu.webim.web.handler.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.util.CallCenterUtils;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.User;

@Controller
public class CallAgentResourceController extends Handler{
	
	@Autowired
	private UserRepository userRes ;
	
	@RequestMapping("/res/agent")
    @Menu(type = "res" , subtype = "agent")
    public ModelAndView add(ModelMap map , HttpServletRequest request , @Valid String q) {
		if(q==null){
			q = "" ;
		}
		final String search = q;
		final String orgi = super.getOrgi(request);
		final List<String> organList = CallCenterUtils.getExistOrgan(super.getUser(request));
		map.put("owneruserList", userRes.findAll(new Specification<User>(){
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				In<Object> in = cb.in(root.get("organ"));
				
				list.add(cb.equal(root.get("orgi").as(String.class),orgi ));
				
				list.add(cb.or(cb.like(root.get("username").as(String.class),"%"+search+"%" ),cb.like(root.get("uname").as(String.class),"%"+search+"%" )));
				
				if(organList.size() > 0){
					
					for(String id : organList){
						in.value(id) ;
					}
				}else{
					in.value(UKDataContext.UKEFU_SYSTEM_NO_DAT) ;
				}
				list.add(in) ;
				
				Predicate[] p = new Predicate[list.size()];  
				return cb.and(list.toArray(p));   
			}}));
		return request(super.createRequestPageTempletResponse("/public/agent"));
    }
}