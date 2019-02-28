package com.ukefu.webim.web.handler.apps.notice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.NoticeMsgRepository;
import com.ukefu.webim.service.repository.NoticeRepository;
import com.ukefu.webim.service.repository.NoticeTargetRepository;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.SystemMessageRepository;
import com.ukefu.webim.service.repository.TemplateRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Notice;
import com.ukefu.webim.web.model.NoticeMsg;
import com.ukefu.webim.web.model.NoticeTarget;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.Template;
import com.ukefu.webim.web.model.UKeFuDic;
import com.ukefu.webim.web.model.User;

/**
 * 公告信息-公告管理
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends Handler{

	@Autowired
	private NoticeRepository noticeRes ;
	
	@Autowired
	private NoticeTargetRepository noticeTargetRes ;
	
	@Autowired
	private UserRepository userRes ;
	
	@Autowired
	private OrganRepository organRes;// 部门
	
	@Autowired
	private NoticeMsgRepository noticeMsgRes;//消息
	
	@Autowired
	private SystemMessageRepository systemMessageRes;//短信、邮件网关
	
	@Autowired
	private TemplateRepository templateRes ;
	
	
	
	@RequestMapping(value = "/add")
    @Menu(type = "notice" , subtype = "noticebus")
    public ModelAndView add(ModelMap map , HttpServletRequest request,@Valid String type) {
		map.addAttribute("systemMessageList",systemMessageRes.findByOrgi(super.getOrgi(request))) ;
		SysDic smsDic = null ;
		SysDic emailDic = null ;
		List<SysDic> dicList = UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_DIC) ;
		if (dicList != null && dicList.size() > 0) {
			for(SysDic dic : dicList){
	    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_NOTICEMAIL)){
	    			emailDic = dic ;
	    		}
	    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_NOTICESMS)){
	    			smsDic = dic ;
	    		}
	    	}
		}
    	if(emailDic!=null){
    		map.addAttribute("emailtemplateList", templateRes.findByTemplettypeAndOrgi(emailDic.getId(), super.getOrgi(request))) ;
    	}
    	if(smsDic!=null){
    		map.addAttribute("smstemplateList", templateRes.findByTemplettypeAndOrgi(smsDic.getId(), super.getOrgi(request))) ;
    	}
    	map.addAttribute("type",type) ;
		return request(super.createAppsTempletResponse("/apps/notice/add"));
    }
	
	@RequestMapping(value = "/add/save")
    @Menu(type = "notice" , subtype = "noticebus")
    public ModelAndView addsave(ModelMap map , HttpServletRequest request ,@Valid Notice notice , @RequestParam(value = "files", required = false) MultipartFile[] files,@Valid String type)  throws Exception {
		
		final User user = super.getUser(request) ;
		String msg="add_failure";
		if (notice != null) {
			notice.setCreater(user.getId());
			notice.setCreatetime(new Date());
			notice.setOrgi(super.getOrgi(request));
			noticeRes.save(notice) ;
			msg = "add_success" ;
		}
		ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/notice/business/index.html?msg="+msg)) ;
		if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(type)) {
			view = request(super.createRequestPageTempletResponse("redirect:/notice/system/index.html?msg="+msg)) ;
		}
		return view;
    }
	
	@RequestMapping(value = "/edit")
    @Menu(type = "notice" , subtype = "noticebus")
    public ModelAndView edit(ModelMap map , HttpServletRequest request ,@Valid String id) {
		if (!StringUtils.isBlank(id)) {
			Notice notice = noticeRes.findByIdAndOrgi(id, super.getOrgi(request));
			if (notice != null) {
				map.addAttribute("notice",notice) ;
			}
		}
		map.addAttribute("systemMessageList",systemMessageRes.findByOrgi(super.getOrgi(request))) ;
		SysDic smsDic = null ;
		SysDic emailDic = null ;
		List<SysDic> dicList = UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_DIC) ;
		if (dicList != null && dicList.size() > 0) {
			for(SysDic dic : dicList){
	    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_NOTICEMAIL)){
	    			emailDic = dic ;
	    		}
	    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_NOTICESMS)){
	    			smsDic = dic ;
	    		}
	    	}
		}
    	if(emailDic!=null){
    		map.addAttribute("emailtemplateList", templateRes.findByTemplettypeAndOrgi(emailDic.getId(), super.getOrgi(request))) ;
    	}
    	if(smsDic!=null){
    		map.addAttribute("smstemplateList", templateRes.findByTemplettypeAndOrgi(smsDic.getId(), super.getOrgi(request))) ;
    	}
    	return request(super.createAppsTempletResponse("/apps/notice/edit"));
    }
	
	@RequestMapping(value = "/edit/save")
    @Menu(type = "notice" , subtype = "noticebus")
    public ModelAndView editsave(ModelMap map , HttpServletRequest request , @Valid String id ,@Valid Notice notice) {
		String msg="edit_failure";
		ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/notice/business/detail.html?id="+id+"&msg="+msg));
		if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(notice.getType())) {
			request(super.createRequestPageTempletResponse("redirect:/notice/system/detail.html?id="+id+"&msg="+msg));
		}
		if (!StringUtils.isBlank(id)) {
			Notice notic = noticeRes.findByIdAndOrgi(id, super.getOrgi(request));
			if (notic != null) {
				
				notic.setContent(notice.getContent());
				notic.setKeyword(notice.getKeyword());
				notic.setSummary(notice.getSummary());
				notic.setTags(notice.getTags());
				notic.setTitle(notice.getTitle());
				notic.setUpdatetime(new Date());
				notic.setSmsserver(notice.getSmsserver());
				notic.setSmstemplate(notice.getSmstemplate());
				notic.setEmailserver(notice.getEmailserver());
				notic.setEmailtemplate(notice.getEmailtemplate());
				noticeRes.save(notic) ;
				msg = "edit_success" ;
				view = request(super.createRequestPageTempletResponse("redirect:/notice/business/detail.html?id="+id+"&msg="+msg));
				if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(notic.getType())) {
					request(super.createRequestPageTempletResponse("redirect:/notice/system/detail.html?id="+id+"&msg="+msg));
				}
			}
		}
		return view ;
    }
	
	@RequestMapping("/delete")
    @Menu(type = "notice" , subtype = "noticebus" )
    public ModelAndView delete(ModelMap map , HttpServletRequest request , @Valid String id ,@Valid String type) throws SQLException {
		String msg="del_failure";
		ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/notice/business/index.html?msg="+msg)) ;
		if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(type)) {
			view = request(super.createRequestPageTempletResponse("redirect:/notice/system/index.html?msg="+msg)) ;
		}
		if (!StringUtils.isBlank(id)) {
			Notice notice = noticeRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
			if (notice != null) {
				
				List<NoticeTarget> noticeTargetList = noticeTargetRes.findByNoticeidAndOrgi(id, super.getOrgi(request)) ;
				if (noticeTargetList != null && noticeTargetList.size() > 0) {
					noticeTargetRes.delete(noticeTargetList);
				}
				noticeRes.delete(notice);
				msg="del_success";
				view = request(super.createRequestPageTempletResponse("redirect:/notice/business/index.html?msg="+msg)) ;
				if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(notice.getType())) {
					view = request(super.createRequestPageTempletResponse("redirect:/notice/system/index.html?msg="+msg)) ;
				}
			}
		}
		return view;
    }
	
	@RequestMapping("/batdelete")
    @Menu(type = "notice" , subtype = "noticebus" )
    public ModelAndView batdelete(ModelMap map , HttpServletRequest request , @Valid String[] ids ,@Valid String type) throws SQLException {
    	String msg = "bat_delete_success";
    	ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/notice/business/index.html?msg="+msg)) ;
		if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(type)) {
			view = request(super.createRequestPageTempletResponse("redirect:/notice/system/index.html?msg="+msg)) ;
		}
    	if(ids!=null && ids.length>0){
    		List<NoticeTarget> noticeTargetList = new ArrayList<NoticeTarget>() ;
    		for(String id : ids) {
    			List<NoticeTarget> nTargetList = noticeTargetRes.findByNoticeidAndOrgi(id, super.getOrgi(request)) ;
    			if (nTargetList != null && nTargetList.size()> 0) {
    				noticeTargetList.addAll(nTargetList) ;
				}
    		}
			if (noticeTargetList != null && noticeTargetList.size() > 0) {
				noticeTargetRes.delete(noticeTargetList);
			}
    		noticeRes.delete(noticeRes.findAll(Arrays.asList(ids)) );
    	}else {
    		msg = "bat_delete_faild";
    	}
    	view = request(super.createRequestPageTempletResponse("redirect:/notice/business/index.html?msg="+msg)) ;
		if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(type)) {
			view = request(super.createRequestPageTempletResponse("redirect:/notice/system/index.html?msg="+msg)) ;
		}
		return view;
    }
	
	
	/**
	 * 	发送弹窗
	 */
	@RequestMapping("/sendlist")
    @Menu(type = "notice" , subtype = "noticebus" )
    public ModelAndView sendlist(ModelMap map , HttpServletRequest request , @Valid String id,  @Valid String organ ,@Valid String noticeid,@Valid String type) throws SQLException {
		map.addAttribute("userList",userRes.findByOrgi(super.getOrgi(request))) ;
		map.addAttribute("organList", organRes.findAll());
		map.addAttribute("userid", super.getUser(request).getId());
		map.addAttribute("noticeid", noticeid);
		if (!StringUtils.isBlank(noticeid)) {
			map.addAttribute("notice",noticeRes.findByIdAndOrgi(noticeid, super.getOrgi(request)));
			map.addAttribute("noticeTargetList", noticeTargetRes.findByNoticeidAndCheckedAndOrgi(noticeid,true, super.getOrgi(request)));
		}
		map.addAttribute("type",type) ;
    	return request(super.createRequestPageTempletResponse("/apps/notice/sendlist"));
    }
	
	/**
	 * 	  发送弹窗-搜索
	 */
	@RequestMapping("/sendlist/search")
    @Menu(type = "notice" , subtype = "noticebus" )
    public ModelAndView search(ModelMap map , HttpServletRequest request , @Valid String stype,@Valid final String q , @Valid final String organ, @Valid final String noticeid) throws SQLException {
		final String orgi = super.getOrgi(request) ;
		ModelAndView view = request(super.createRequestPageTempletResponse("/apps/notice/include/userlist")) ; ;
		if ("agent".equals(stype)) {
			List<User> userList = userRes.findAll(new Specification<User>(){
				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();  

					list.add(cb.equal(root.get("orgi").as(String.class), orgi));
					if (!StringUtils.isBlank(organ)) {
						if ("0".equals(organ)) {
							list.add(cb.isNotNull(root.get("organ").as(String.class)));
						}else {
							list.add(cb.equal(root.get("organ").as(String.class), organ));
						}
					}
					if (!StringUtils.isBlank(q)) {
						list.add(cb.or(cb.like(root.get("uname").as(String.class), "%"+q+"%"), cb.like(root.get("username").as(String.class), "%"+q+"%")));

					}
					Predicate[] p = new Predicate[list.size()];  
					return cb.and(list.toArray(p));   
				}});
			map.addAttribute("userList",userList) ;
		}else if ("organ".equals(stype)) {
			List<Organ> organList = organRes.findAll(new Specification<Organ>(){
				@Override
				public Predicate toPredicate(Root<Organ> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();  

					list.add(cb.equal(root.get("orgi").as(String.class), orgi));
					if (!StringUtils.isBlank(q)) {
						list.add(cb.like(root.get("name").as(String.class), "%"+q+"%"));

					}
					Predicate[] p = new Predicate[list.size()];  
					return cb.and(list.toArray(p));   
				}});
			map.addAttribute("organlist",organList) ;
			view = request(super.createRequestPageTempletResponse("/apps/notice/include/organlist")) ;
		}
		if (!StringUtils.isBlank(organ) && !"0".equals(organ)) {
			Organ currentorgan = organRes.findByIdAndOrgi(organ, super.getOrgi(request)) ;
			if (currentorgan != null) {
				map.addAttribute("currentorgan",currentorgan) ;
			}
		}
		map.addAttribute("q",q) ;
		if (!StringUtils.isBlank(noticeid)) {
			map.addAttribute("noticeTargetList", noticeTargetRes.findByNoticeidAndCheckedAndOrgi(noticeid,true, super.getOrgi(request)));
		}
		
    	return view;
    }
	
	/**
	 * 	发送弹窗-选中事件
	 */
	@RequestMapping("/sendlist/checkitem")
    @Menu(type = "notice" , subtype = "noticebus" )
    public ModelAndView checkitem(ModelMap map , HttpServletRequest request , @Valid String target,  @Valid boolean ischecked ,@Valid String noticeid , @Valid String[] targets) throws SQLException {
		final String orgi = super.getOrgi(request) ;
		if (!StringUtils.isBlank(noticeid)) {
			if (!StringUtils.isBlank(target)) {
				NoticeTarget noticeTarget = noticeTargetRes.findByNoticeidAndTargetAndOrgi(noticeid,target, orgi) ;
				if (noticeTarget != null) {
					noticeTarget.setChecked(ischecked);
					noticeTargetRes.save(noticeTarget) ;
				}else {
					noticeTarget = new NoticeTarget();
					noticeTarget.setChecked(ischecked);
					noticeTarget.setCreater(super.getUser(request).getId());
					noticeTarget.setCreatetime(new Date());
					noticeTarget.setNoticeid(noticeid);
					noticeTarget.setOrgi(orgi);
					noticeTarget.setTarget(target);
					noticeTargetRes.save(noticeTarget) ;
				}
			}else if (targets != null && targets.length > 0) {
				List<NoticeTarget> ntList = new ArrayList<NoticeTarget>() ;
		    	for(String tid : targets) {
		    		NoticeTarget nt = noticeTargetRes.findByNoticeidAndTargetAndOrgi(noticeid, tid, orgi) ;
		    		if (nt != null) {
		    			nt.setChecked(ischecked);
					}else {
						nt = new NoticeTarget();
						nt.setChecked(ischecked);
						nt.setCreater(super.getUser(request).getId());
						nt.setCreatetime(new Date());
						nt.setNoticeid(noticeid);
						nt.setOrgi(orgi);
						nt.setTarget(tid);
					}
		    		ntList.add(nt) ;
		    	}
		    	if (ntList != null && ntList.size() > 0) {
		    		noticeTargetRes.save(ntList) ;
				}
			}
			
		}
		return null ;
    }
	
	/**
	 * 	发送弹窗-发送
	 * @throws Exception 
	 */
	@RequestMapping("/sendlist/save")
    @Menu(type = "notice" , subtype = "noticebus" )
    public ModelAndView sendlistsave(ModelMap map , HttpServletRequest request ,@Valid String noticeid,@Valid boolean sysms ,@Valid boolean sms ,@Valid boolean mail,@Valid String type) throws Exception {
		User user = super.getUser(request) ;
		String orgi = super.getOrgi(request) ;
		String msg = "" ;
		String sysmsmsg = "" ;
		String smsmsg = "" ;
		String mailmsg = "" ;
		Notice notice = null ;
		List<User> usertargetList = new ArrayList<User>();
		if (!StringUtils.isBlank(noticeid)) {
			notice = noticeRes.findByIdAndOrgi(noticeid, orgi) ;
			if (notice != null) {
				List<NoticeTarget> noticeTargetList = noticeTargetRes.findByNoticeidAndCheckedAndOrgi(noticeid, true, orgi) ;
				if (noticeTargetList != null && noticeTargetList.size() > 0) {
					for(NoticeTarget nt : noticeTargetList) {
						if (nt != null) {
							User usertarget = userRes.findByIdAndOrgi(nt.getTarget(), orgi) ;
							usertargetList.add(usertarget) ;
						}
					}
				}else {
					msg = "send_targetnull" ;
				}
			}
		}
		if (usertargetList != null && usertargetList.size()>0) {
			if (sysms) {//系统消息
				sysmsmsg = "send_faild" ;
				List<NoticeMsg> noticeMsgList = new ArrayList<NoticeMsg>() ;
				for(User ut : usertargetList) {
					if (ut != null) {
						NoticeMsg mmsg = new NoticeMsg() ;
						mmsg.setContent(notice.getContent());
						mmsg.setCreater(user.getId());
						mmsg.setCreaterusername(user.getUname());
						mmsg.setCreatetime(new Date());
						mmsg.setDatastatus(false);
						mmsg.setTitle(notice.getTitle());
						mmsg.setOrgi(orgi);
						mmsg.setStatus(false);
						mmsg.setTarget(ut.getId());
						mmsg.setType(notice.getType());
						noticeMsgList.add(mmsg) ;
					}
				}
				if (noticeMsgList != null && noticeMsgList.size() > 0) {
					noticeMsgRes.save(noticeMsgList) ;
					sysmsmsg = "send_success" ;
				}
			}
			if (sms) {//短信
				smsmsg = "send_faild" ;
				String phones = "";
				for(int i=0 ;i<usertargetList.size();i++) {
					User ut = usertargetList.get(i);
					if (ut != null) {
						if (!StringUtils.isBlank(ut.getMobile())) {
							if (i==0) {
								phones = ut.getMobile();
							}else {
								phones += ","+ut.getMobile();
							}
						}
					}
				}
				if (!StringUtils.isBlank(phones)) {
					Map<String, Object> values = new HashMap<String, Object>();
					values.put("content", notice.getTitle().toString()) ;
					if (UKTools.sendSmsByTemplate(phones,notice.getSmsserver(),notice.getSmstemplate(), values,  null)) {
						smsmsg = "send_success" ;
					}
				}
			}
			if (mail) {//邮件
				mailmsg = "send_faild" ;
				List<String> emailsList = new ArrayList<String>();
				for(User ut : usertargetList) {
					if (ut != null) {
						if (!StringUtils.isBlank(ut.getEmail())) {
							emailsList.add(ut.getEmail()) ;
						}
					}
				}
				if (emailsList != null && emailsList.size()>0) {
					Template template = templateRes.findByIdAndOrgi(notice.getEmailtemplate(), orgi) ;
					if (template!=null) {
						Map<String, Object> values = new HashMap<String, Object>();
						values.put("content", notice.getContent()) ;
						String content = UKTools.getTemplet(template.getTemplettext(), values) ;
						UKTools.sendMassMail(emailsList, notice.getTitle(), content, notice.getEmailserver(), orgi);
						mailmsg = "send_success" ;
					}
				}
			}
		}
		ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/notice/business/detail.html?id="+noticeid+"&msg="+msg+"&sysmsmsg="+sysmsmsg+"&smsmsg="+smsmsg+"&mailmsg="+mailmsg));
		if (UKDataContext.NoticeType.SYSTEMUPGRADE.toString().equals(type)) {
			view = request(super.createRequestPageTempletResponse("redirect:/notice/system/detail.html?id="+noticeid+"&msg="+msg+"&sysmsmsg="+sysmsmsg+"&smsmsg="+smsmsg+"&mailmsg="+mailmsg));
		}
    	return view ;
    }
	
}
