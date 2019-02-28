package com.ukefu.webim.web.handler.apps.setting;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.SMSTemplateRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.SmsTemplate;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.UKeFuDic;

@Controller
@RequestMapping("/setting/sms")
public class SMSController extends Handler{
	
	@Autowired
	private SMSTemplateRepository smsTempletRes ;	//查询模板
	
	/**
	 * 短信模板列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping({"/index"})
	@Menu(type="apps", subtype="sms")
	public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String tptype){
		map.addAttribute("sysDic", UKeFuDic.getInstance().getDicItem(tptype)) ;
		map.addAttribute("templateList", smsTempletRes.findByTemplettypeAndOrgi(tptype, super.getOrgi(request))) ;
		return request(super.createAppsTempletResponse("/apps/setting/smstemplet/index"));
	}
	
	@RequestMapping("/add")
    @Menu(type="apps", subtype="sms")
    public ModelAndView add(ModelMap map , HttpServletRequest request ,@Valid String tptype) {
		map.addAttribute("sysDic", UKeFuDic.getInstance().getDicItem(tptype)) ;
        return request(super.createRequestPageTempletResponse("/apps/setting/smstemplet/add"));
    }
    
    @RequestMapping(  "/save")
    @Menu(type="apps", subtype="sms")
    public ModelAndView save(HttpServletRequest request  , @Valid SmsTemplate template) {
    	template.setOrgi(super.getOrgi(request));
    	template.setCreatetime(new Date());
    	SysDic dic = UKeFuDic.getInstance().getDicItem(template.getTemplettype());
		if(dic!=null) {
			template.setCode(dic.getCode());
		}
		smsTempletRes.save(template) ;
		return request(super.createRequestPageTempletResponse("redirect:/setting/sms/index.html?tptype="+template.getTemplettype()));
    }
    
    @RequestMapping("/edit")
    @Menu(type="apps", subtype="sms")
    public ModelAndView edit(ModelMap map , HttpServletRequest request , @Valid String id, @Valid String tptype) {
    	SmsTemplate template = smsTempletRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
    	map.addAttribute("template", template) ;
    	if(!StringUtils.isBlank(template.getTemplettype())) {
    		map.addAttribute("sysDic", UKeFuDic.getInstance().getDicItem(template.getTemplettype())) ;
    	}
        return request(super.createRequestPageTempletResponse("/apps/setting/smstemplet/edit"));
    }
    
    @RequestMapping(  "/update")
    @Menu(type="apps", subtype="sms")
    public ModelAndView update(HttpServletRequest request  , @Valid SmsTemplate template) {
    	SmsTemplate oldTemplate = smsTempletRes.findByIdAndOrgi(template.getId(), super.getOrgi(request)) ;
    	if(oldTemplate!=null){
    		SysDic dic = UKeFuDic.getInstance().getDicItem(template.getTemplettype());
    		if(dic!=null) {
    			oldTemplate.setCode(dic.getCode());
    		}
    		if(!StringUtils.isBlank(template.getCode())) {
    			oldTemplate.setCode(template.getCode());
    		}
    		oldTemplate.setTemplettext(template.getTemplettext());
    		oldTemplate.setName(template.getName());
    		oldTemplate.setLayoutcols(template.getLayoutcols());
    		oldTemplate.setIconstr(template.getIconstr());
    		oldTemplate.setDatatype(template.getDatatype());
    		oldTemplate.setCharttype(template.getCharttype());
    		smsTempletRes.save(oldTemplate) ;
    		
    		CacheHelper.getSystemCacheBean().delete(template.getId(), super.getOrgi(request)) ;
    	}
		return request(super.createRequestPageTempletResponse("redirect:/setting/sms/index.html?tptype="+template.getTemplettype()));
    }
    

    @RequestMapping("/delete")
    @Menu(type="apps", subtype="sms")
    public ModelAndView delete(HttpServletRequest request ,@Valid SmsTemplate template, @Valid String tptype) {
    	if(template!=null){
    		smsTempletRes.delete(template) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/setting/sms/index.html?tptype="+tptype));
    }
}
