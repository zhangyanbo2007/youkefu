package com.ukefu.webim.web.handler.apps.notice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.NoticeRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Notice;

/**
 * 公告信息-公告管理
 *
 */
@Controller
@RequestMapping("/notice/business")
public class NoticeBusinessController extends Handler{

	@Autowired
	private NoticeRepository noticeRes ;
	
	
	@Autowired
	private UserRepository userRes ;
	
	
	@RequestMapping("/index")
	@Menu(type = "notice", subtype = "noticebus")
	public ModelAndView index(ModelMap map , HttpServletRequest request ,HttpServletResponse response ,@Valid String msg) {
		final String orgi = super.getOrgi(request);
		Page<Notice> noticeList = noticeRes.findAll(new Specification<Notice>(){
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  

				list.add(cb.equal(root.get("orgi").as(String.class), orgi));
				list.add(cb.equal(root.get("type").as(String.class), UKDataContext.NoticeType.BUSINESS.toString()));

				Predicate[] p = new Predicate[list.size()];  
				return cb.and(list.toArray(p));   
			}}, new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" }));
		
		
		map.addAttribute("noticeList",noticeList) ;
		map.addAttribute("msg",msg) ;
		map.addAttribute("userList",userRes.findByOrgi(orgi)) ;
		map.addAttribute("type",UKDataContext.NoticeType.BUSINESS.toString());
		return request(super.createAppsTempletResponse("/apps/notice/index")) ;
	}
	
	@RequestMapping("/detail")
    @Menu(type = "notice" , subtype = "noticebus" )
    public ModelAndView detail(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String msg, @Valid String sysmsmsg, @Valid String smsmsg, @Valid String mailmsg) throws SQLException {
		if (!StringUtils.isBlank(id)) {
			Notice notice = noticeRes.findByIdAndOrgi(id, super.getOrgi(request));
			if (notice != null) {
				map.addAttribute("notice",notice) ;
			}
		}
		map.addAttribute("msg",msg) ;
		map.addAttribute("sysmsmsg",sysmsmsg) ;
		map.addAttribute("smsmsg",smsmsg) ;
		map.addAttribute("mailmsg",mailmsg) ;
    	return request(super.createAppsTempletResponse("/apps/notice/detail"));
    }
	
}
