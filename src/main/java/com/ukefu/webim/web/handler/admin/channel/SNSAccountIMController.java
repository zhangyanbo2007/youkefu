package com.ukefu.webim.web.handler.admin.channel;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Base62;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.service.repository.SNSAccountRepository;
import com.ukefu.webim.service.repository.SecretRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.CousultInvite;
import com.ukefu.webim.web.model.SNSAccount;
import com.ukefu.webim.web.model.Secret;

/**
 *
 *
 */
@Controller
@RequestMapping("/admin/im")
public class SNSAccountIMController extends Handler{
	
	@Autowired
	private SNSAccountRepository snsAccountRes;
	
	@Autowired
	private ConsultInviteRepository invite;
	
	@Autowired
	private SecretRepository secRes ;

    @RequestMapping("/index")
    @Menu(type = "admin" , subtype = "im" , access = false ,admin = true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String execute) {
    	map.addAttribute("snsAccountList", snsAccountRes.findBySnstypeAndOrgi( UKDataContext.ChannelTypeEnum.WEBIM.toString() , super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request)))) ;
    	List<Secret> secretConfig = secRes.findByOrgi(super.getOrgi(request)) ;
    	if(secretConfig!=null && secretConfig.size() > 0){
    		map.addAttribute("secret", secretConfig.get(0)) ;
    	}
    	if(!StringUtils.isBlank(execute) && execute.equals("false")){
    		map.addAttribute("execute", execute) ;
    	}
    	return request(super.createAdminTempletResponse("/admin/channel/im/index"));
    }
    
    @RequestMapping("/add")
    @Menu(type = "admin" , subtype = "im" , access = false ,admin = true)
    public ModelAndView add(ModelMap map , HttpServletRequest request) {
        return request(super.createRequestPageTempletResponse("/admin/channel/im/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "admin" , subtype = "weixin")
    public ModelAndView save(HttpServletRequest request ,@Valid SNSAccount snsAccount) throws NoSuchAlgorithmException {
    	if(!StringUtils.isBlank(snsAccount.getBaseURL())){
	    	snsAccount.setSnsid(Base62.encode(snsAccount.getBaseURL() + super.getOrgi(request)));
	    	int count = snsAccountRes.countBySnsidAndOrgi(snsAccount.getSnsid() , super.getOrgi(request)) ;
	    	if(count == 0){
	    		snsAccount.setOrgi(super.getOrgi(request));
	    		snsAccount.setSnstype(UKDataContext.ChannelTypeEnum.WEBIM.toString());
	    		snsAccount.setCreatetime(new Date());
	    		snsAccountRes.save(snsAccount) ;
	    		
	    		/**
	    		 * 同时创建CousultInvite 记录
	    		 */
	    		CousultInvite coultInvite = invite.findBySnsaccountidAndOrgi(snsAccount.getSnsid(), super.getOrgi(request)) ;
    			if(coultInvite ==null){
    				coultInvite = new CousultInvite() ;
    				coultInvite.setSnsaccountid(snsAccount.getSnsid());
    				coultInvite.setCreate_time(new Date());
    				coultInvite.setOrgi(super.getOrgi(request));
    				coultInvite.setName(snsAccount.getName());
    				invite.save(coultInvite) ;
    			}
			}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/im/index.html"));
    }
    
    @RequestMapping("/delete")
    @Menu(type = "weixin" , subtype = "delete")
    public ModelAndView delete(ModelMap map , HttpServletRequest request , @Valid String id  , @Valid String confirm) {
    	boolean execute = false ;
    	if(execute = UKTools.secConfirm(secRes, super.getOrgi(request), confirm)){
	    	SNSAccount snsAccount = snsAccountRes.findByIdAndOrgi(id , super.getOrgi(request)) ;
	    	if(snsAccountRes!=null){
	    		snsAccountRes.delete(snsAccount);
	    		CousultInvite coultInvite = invite.findBySnsaccountidAndOrgi(snsAccount.getSnsid(), super.getOrgi(request)) ;
    			if(coultInvite != null){
    				invite.delete(coultInvite);
    			}
	    	}
    	}
    	
        return request(super.createRequestPageTempletResponse("redirect:/admin/im/index.html?execute="+execute));
    }
    
    @RequestMapping("/edit")
    @Menu(type = "admin" , subtype = "im" , access = false ,admin = true)
    public ModelAndView edit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("snsAccount", snsAccountRes.findByIdAndOrgi(id , super.getOrgi(request))) ;
        return request(super.createRequestPageTempletResponse("/admin/channel/im/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "admin" , subtype = "im" , access = false ,admin = true)
    public ModelAndView update(HttpServletRequest request ,@Valid SNSAccount snsAccount) throws NoSuchAlgorithmException {
    	SNSAccount oldSnsAccount = snsAccountRes.findByIdAndOrgi(snsAccount.getId() , super.getOrgi(request));
    	if(oldSnsAccount!=null){
    		oldSnsAccount.setName(snsAccount.getName());
    		oldSnsAccount.setBaseURL(snsAccount.getBaseURL());
    		oldSnsAccount.setUpdatetime(new Date());
    		/**
    		 * SNSID如果有变更，需要同时变更 CoultInvite 表的 记录
    		 */
    		if(!StringUtils.isBlank(oldSnsAccount.getSnsid())){
    			CousultInvite coultInvite = invite.findBySnsaccountidAndOrgi(oldSnsAccount.getSnsid(), super.getOrgi(request)) ;
    			if(coultInvite ==null){
    				/**
    	    		 * 同时创建CousultInvite 记录
    	    		 */
    				coultInvite = new CousultInvite() ;
    				coultInvite.setSnsaccountid(oldSnsAccount.getSnsid());
    				coultInvite.setCreate_time(new Date());
    				coultInvite.setOrgi(super.getOrgi(request));
    				coultInvite.setName(snsAccount.getName());
    				invite.save(coultInvite) ;
    			}
    		}
    		
    		oldSnsAccount.setSnstype(UKDataContext.ChannelTypeEnum.WEBIM.toString());
    		snsAccountRes.save(oldSnsAccount) ;
		}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/im/index.html"));
    }
}