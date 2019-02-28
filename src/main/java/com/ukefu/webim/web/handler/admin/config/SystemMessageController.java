package com.ukefu.webim.web.handler.admin.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.SystemMessageRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.SystemMessage;
import com.ukefu.webim.web.model.UKeFuDic;

@Controller
@RequestMapping("/admin")
public class SystemMessageController extends Handler{
	
	@Autowired
	private SystemMessageRepository systemMessageRepository;
	
    @RequestMapping("/email/index")
    @Menu(type = "setting" , subtype = "email")
    public ModelAndView index(ModelMap map , HttpServletRequest request) throws FileNotFoundException, IOException {
    	map.addAttribute("emailList", systemMessageRepository.findByMsgtypeAndOrgi("email" , super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request))));
    	return request(super.createAdminTempletResponse("/admin/email/index"));
    }
    
    @RequestMapping("/email/add")
    @Menu(type = "admin" , subtype = "email")
    public ModelAndView add(ModelMap map , HttpServletRequest request) {
        return request(super.createRequestPageTempletResponse("/admin/email/add"));
    }
    
    @RequestMapping("/email/save")
    @Menu(type = "admin" , subtype = "user")
    public ModelAndView save(HttpServletRequest request ,@Valid SystemMessage email) throws NoSuchAlgorithmException {
    	email.setOrgi(super.getOrgi(request));
    	email.setMsgtype(UKDataContext.SystemMessageType.EMAIL.toString());
    	if(!StringUtils.isBlank(email.getSmtppassword())) {
			email.setSmtppassword(UKTools.encryption(email.getSmtppassword()));
		}
    	systemMessageRepository.save(email) ;
    	return request(super.createRequestPageTempletResponse("redirect:/admin/email/index.html"));
    }
    
    @RequestMapping("/email/edit")
    @Menu(type = "admin" , subtype = "email")
    public ModelAndView edit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("email", systemMessageRepository.findByIdAndOrgi(id, super.getOrgi(request))) ;
        return request(super.createRequestPageTempletResponse("/admin/email/edit"));
    }
    
    @RequestMapping("/email/update")
    @Menu(type = "admin" , subtype = "user" , admin = true)
    public ModelAndView update(HttpServletRequest request ,@Valid SystemMessage email) throws NoSuchAlgorithmException {
    	SystemMessage temp = systemMessageRepository.findByIdAndOrgi(email.getId(), super.getOrgi(request)) ;
    	if(email!=null) {
    		email.setCreatetime(temp.getCreatetime());
    		email.setOrgi(temp.getOrgi());
    		email.setMsgtype(UKDataContext.SystemMessageType.EMAIL.toString());
    		if(!StringUtils.isBlank(email.getSmtppassword())) {
    			email.setSmtppassword(UKTools.encryption(email.getSmtppassword()));
    		}else {
    			email.setSmtppassword(temp.getSmtppassword());
    		}
    		systemMessageRepository.save(email) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/email/index.html"));
    }
    
    @RequestMapping("/email/delete")
    @Menu(type = "admin" , subtype = "user")
    public ModelAndView delete(HttpServletRequest request ,@Valid SystemMessage email) {
    	SystemMessage temp = systemMessageRepository.findByIdAndOrgi(email.getId(), super.getOrgi(request)) ;
    	if(email!=null) {
    		systemMessageRepository.delete(temp);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/email/index.html"));
    }
    
    
    @RequestMapping("/sms/index")
    @Menu(type = "setting" , subtype = "sms")
    public ModelAndView smsindex(ModelMap map , HttpServletRequest request) throws FileNotFoundException, IOException {
    	map.addAttribute("smsList", systemMessageRepository.findByMsgtypeAndOrgi("sms" , super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request))));
    	return request(super.createAdminTempletResponse("/admin/sms/index"));
    }
    
    @RequestMapping("/sms/add")
    @Menu(type = "admin" , subtype = "sms")
    public ModelAndView smsadd(ModelMap map , HttpServletRequest request) {
    	
    	map.addAttribute("smsType", UKeFuDic.getInstance().getDic("com.dic.sms.type")) ;
        return request(super.createRequestPageTempletResponse("/admin/sms/add"));
    }
    
    @RequestMapping("/sms/save")
    @Menu(type = "admin" , subtype = "sms")
    public ModelAndView smssave(HttpServletRequest request ,@Valid SystemMessage sms) throws NoSuchAlgorithmException {
    	sms.setOrgi(super.getOrgi(request));
    	sms.setMsgtype(UKDataContext.SystemMessageType.SMS.toString());
    	if(!StringUtils.isBlank(sms.getSmtppassword())) {
    		sms.setSmtppassword(UKTools.encryption(sms.getSmtppassword()));
		}
    	systemMessageRepository.save(sms) ;
    	return request(super.createRequestPageTempletResponse("redirect:/admin/sms/index.html"));
    }
    
    @RequestMapping("/sms/edit")
    @Menu(type = "admin" , subtype = "sms")
    public ModelAndView smsedit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("smsType", UKeFuDic.getInstance().getDic("com.dic.sms.type")) ;
    	map.addAttribute("sms", systemMessageRepository.findByIdAndOrgi(id, super.getOrgi(request))) ;
        return request(super.createRequestPageTempletResponse("/admin/sms/edit"));
    }
    
    @RequestMapping("/sms/update")
    @Menu(type = "admin" , subtype = "sms" , admin = true)
    public ModelAndView smsupdate(HttpServletRequest request ,@Valid SystemMessage sms) throws NoSuchAlgorithmException {
    	SystemMessage temp = systemMessageRepository.findByIdAndOrgi(sms.getId(), super.getOrgi(request)) ;
    	if(sms!=null) {
    		sms.setCreatetime(temp.getCreatetime());
    		sms.setOrgi(temp.getOrgi());
    		sms.setMsgtype(UKDataContext.SystemMessageType.SMS.toString());
    		if(!StringUtils.isBlank(sms.getSmtppassword())) {
    			sms.setSmtppassword(UKTools.encryption(sms.getSmtppassword()));
    		}else {
    			sms.setSmtppassword(temp.getSmtppassword());
    		}
    		systemMessageRepository.save(sms) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/sms/index.html"));
    }
    
    @RequestMapping("/sms/delete")
    @Menu(type = "admin" , subtype = "sms")
    public ModelAndView smsdelete(HttpServletRequest request ,@Valid SystemMessage sms) {
    	SystemMessage temp = systemMessageRepository.findByIdAndOrgi(sms.getId(), super.getOrgi(request)) ;
    	if(sms!=null) {
    		systemMessageRepository.delete(temp);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/sms/index.html"));
    }
}