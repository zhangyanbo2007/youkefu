package com.ukefu.webim.web.handler.apps.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.util.task.export.ExcelExporterProcess;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.service.repository.SmsResultRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.SmsResult;
import com.ukefu.webim.web.model.UKeFuDic;

@Controller
@RequestMapping("/service/sms")
public class SmsHistoryController extends Handler{
	
	@Autowired
	private SmsResultRepository smsRecorRes ;
	
	@Autowired
	private MetadataRepository metadataRes ;
	
	
	@RequestMapping("/index")
    @Menu(type = "service" , subtype = "sms")
    public ModelAndView index(ModelMap map , HttpServletRequest request ,final String tptype,final String name,final String phonenumber ,  @Valid final String begin , @Valid final String end) {
		final String orgi = super.getOrgi(request);
		Page<SmsResult> smsResultList = smsRecorRes.findAll(new Specification<SmsResult>(){
			@Override
			public Predicate toPredicate(Root<SmsResult> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				
				list.add(cb.equal(root.get("orgi").as(String.class), orgi)) ;
				
				if(!StringUtils.isBlank(tptype)) {
					list.add(cb.equal(root.get("templettype").as(String.class), tptype)) ;
				}
				if(!StringUtils.isBlank(name)) {
					list.add(cb.equal(root.get("name").as(String.class), name)) ;
				}
				if(!StringUtils.isBlank(phonenumber)) {
					list.add(cb.equal(root.get("phonenumber").as(String.class), phonenumber)) ;
				}
				try {
					if(!StringUtils.isBlank(begin) && begin.matches("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}")){
						list.add(cb.greaterThanOrEqualTo(root.get("createtime").as(Date.class), UKTools.dateFormate.parse(begin))) ;
					}
					if(!StringUtils.isBlank(end) && end.matches("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}")){
						list.add(cb.lessThanOrEqualTo(root.get("createtime").as(Date.class), UKTools.dateFormate.parse(end))) ;
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));   
			}
		},new PageRequest(super.getP(request), super.getPs(request), Direction.DESC , "createtime")) ;
		map.addAttribute("smsResultList", smsResultList) ;
		
		map.addAttribute("begin", begin) ;
		map.addAttribute("end", end) ;
		map.addAttribute("phonenumber", phonenumber) ;
		map.addAttribute("name", name) ;
		
		
		if(!StringUtils.isBlank(tptype)) {
			map.addAttribute("sysDic", UKeFuDic.getInstance().getDicItem(tptype)) ;
		}
        return request(super.createAppsTempletResponse("/apps/service/sms/index"));
    }
	
	@RequestMapping("/expids")
	@Menu(type = "callcenter", subtype = "sms")
	public void expids(ModelMap map, HttpServletRequest request, HttpServletResponse response, @Valid String[] ids)
			throws IOException {
		if (ids != null && ids.length > 0) {
			Iterable<SmsResult> agentServiceList = smsRecorRes.findAll(Arrays.asList(ids));
			MetadataTable table = metadataRes.findByTablename("uk_sms_record");
			List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
			for (SmsResult smsRecord: agentServiceList) {
				values.add(UKTools.transBean2Map(smsRecord));
			}

			response.setHeader("content-disposition", "attachment;filename=UCKeFu-AgentService-History-"
					+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls");

			ExcelExporterProcess excelProcess = new ExcelExporterProcess(values, table, response.getOutputStream());
			excelProcess.process();
		}

		return;
	}

	@RequestMapping("/expall")
	@Menu(type = "callcenter", subtype = "sms")
	public void expall(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Iterable<SmsResult> smsResultList = smsRecorRes.findAll(new PageRequest(0, 10000));

		MetadataTable table = metadataRes.findByTablename("uk_sms_record");
		List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
		for (SmsResult smsResult : smsResultList) {
			values.add(UKTools.transBean2Map(smsResult));
		}

		response.setHeader("content-disposition", "attachment;filename=UCKeFu-SMS-History-"
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls");

		ExcelExporterProcess excelProcess = new ExcelExporterProcess(values, table, response.getOutputStream());
		excelProcess.process();
		return;
	}

	@RequestMapping("/expsearch")
	@Menu(type = "callcenter", subtype = "sms")
	public void expall(ModelMap map , HttpServletResponse response , HttpServletRequest request ,final String tptype,final String name,final String phonenumber ,  @Valid final String begin , @Valid final String end) throws IOException {
		final String orgi = super.getOrgi(request);
		Page<SmsResult> smsResultList = smsRecorRes.findAll(new Specification<SmsResult>(){
			@Override
			public Predicate toPredicate(Root<SmsResult> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				
				list.add(cb.equal(root.get("orgi").as(String.class), orgi)) ;
				
				if(!StringUtils.isBlank(tptype)) {
					list.add(cb.equal(root.get("templettype").as(String.class), tptype)) ;
				}
				if(!StringUtils.isBlank(name)) {
					list.add(cb.equal(root.get("name").as(String.class), name)) ;
				}
				if(!StringUtils.isBlank(phonenumber)) {
					list.add(cb.equal(root.get("phonenumber").as(String.class), phonenumber)) ;
				}
				try {
					if(!StringUtils.isBlank(begin) && begin.matches("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}")){
						list.add(cb.greaterThanOrEqualTo(root.get("createtime").as(Date.class), UKTools.dateFormate.parse(begin))) ;
					}
					if(!StringUtils.isBlank(end) && end.matches("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}")){
						list.add(cb.lessThanOrEqualTo(root.get("createtime").as(Date.class), UKTools.dateFormate.parse(end))) ;
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));   
			}
		},new PageRequest(super.getP(request),10000, Direction.DESC , "createtime")) ;

		MetadataTable table = metadataRes.findByTablename("uk_sms_record");
		List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
		for (SmsResult smsResult : smsResultList.getContent()) {
			values.add(UKTools.transBean2Map(smsResult));
		}

		response.setHeader("content-disposition", "attachment;filename=UCKeFu-SMS-History-"
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls");

		ExcelExporterProcess excelProcess = new ExcelExporterProcess(values, table, response.getOutputStream());
		excelProcess.process();

		return;
	}
}